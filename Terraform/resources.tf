# resources are created/managed by terraform

## Budgets and Costs

resource "aws_budgets_budget" "cost" {
  name = "Standard-1USD-Budget"
  account_id = var.account_id
  budget_type       = "COST"
  limit_amount = "1"
  limit_unit = "USD"
  time_period_end   = "2087-06-15_00:00"
  time_period_start = "2026-01-01_00:00"
  time_unit         = "MONTHLY"

  cost_types {
    include_credit             = true
    include_discount           = true
    include_other_subscription = true
    include_recurring          = true
    include_refund             = true
    include_subscription       = true
    include_support            = true
    include_tax                = true
    include_upfront            = true
    use_blended                = false
  }

  notification {
    comparison_operator        = "GREATER_THAN"
    threshold                  = 1
    threshold_type             = "PERCENTAGE"
    notification_type          = "FORECASTED"
    subscriber_email_addresses = var.notification_emails
  }

  tags = {
    ManagedBy = "Terraform"
  }
}

## S3 buckets and default files

resource "aws_s3_bucket" "scrapper_bucket" {
  bucket        = format("tickets-data-%s-%s-an", var.account_id, var.region)
  force_destroy = true # so all files are deleted alongside the bucket

  bucket_namespace = "account-regional"

  tags = {
    ManagedBy = "Terraform"
  }
}

resource "aws_s3_object" "scrapper_config_object" {

  for_each = toset(fileset(local.scrapper_ticket_files_path, "*.json"))

  bucket = aws_s3_bucket.scrapper_bucket.id
  key    = "${local.scrapper_ticket_s3_path}/${each.value}"

  # Ticket configs taken from local /s3/tickets/ folder so it can be independent from the local /DataETL/ folder
  source = "${path.module}/s3/tickets/${each.value}"
  server_side_encryption = "AES256"
  
  tags = {
    ManagedBy = "Terraform"
  }
}


## Lambda and EventBridge Trigger

# IAM policy to manage who can assume the lambda execution role
data "aws_iam_policy_document" "lambda_exec_assume_role_policy" {
  statement {
    effect = "Allow"

    principals {
      type        = "Service"
      identifiers = [
        "scheduler.amazonaws.com",
        "lambda.amazonaws.com"
      ]
    }

    actions = ["sts:AssumeRole"]
  }
}

# Terraform-managed default role for Lambda execution
resource "aws_iam_role" "terraform_lambda_role" {
  name               = "terraform-lambda-role"
  assume_role_policy = data.aws_iam_policy_document.lambda_exec_assume_role_policy.json

  tags = {
    ManagedBy = "Terraform"
  }
}

# IAM policy for permissions for the lambda execution role
resource "aws_iam_role_policy" "lambda_exec_role_policy" {
  name = "LambdaExecPermissions"
  role = aws_iam_role.terraform_lambda_role.id

  # Terraform's "jsonencode" function converts a
  # Terraform expression result to valid JSON syntax.
  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action = [
          "s3:ListBucket",
          "s3:ListObjects",
          "s3:GetObject",
          "s3:PutObject"
        ],
        Effect   = "Allow"
        Resource = [
          "${aws_s3_bucket.scrapper_bucket.arn}",
          "${aws_s3_bucket.scrapper_bucket.arn}/*"
        ]
      },
    ]
  })

}

# Package the Lambda function code
data "archive_file" "lambda_scrapper_code" {
  type        = "zip"
  # Pack the code from somewhere else
  # Taken from the /DataETL/ folder as our source of truth
  source_dir = "${path.module}/../DataETL/Lambda/scrapper"
  output_path = "${path.module}/lambda/function.zip"
}

# Lambda function for data scrapping
resource "aws_lambda_function" "lambda_scrapper" {
  filename      = data.archive_file.lambda_scrapper_code.output_path
  function_name = "scrapper_lambda_function"
  description = "Scrapes websites and stores zip files on the S3 with the raw html files"
  role          = aws_iam_role.terraform_lambda_role.arn
  handler       = "lambda_function.lambda_handler"
  
  # 300 seconds - 5 minutes
  timeout       = 300 

  runtime = "python3.12"

  environment {
    variables = {
      SCRAP_S3_BUCKET = aws_s3_bucket.scrapper_bucket.bucket
      TICKET_LIST_FOLDER_PATH = local.scrapper_ticket_s3_path
      TMP_SIZE_ZIP_THRESHOLD_BYTES = 104857600 # 100 MB
      ENVIRONMENT = "production"
      LOG_LEVEL   = "info"
    }
  }

  tags = {
    ManagedBy = "Terraform"
  }

}

## Event bridge scrapper trigger

resource "aws_scheduler_schedule" "scrapper-event-schedule" {
  name       = "Trigger-Every-Workday"
  group_name = "default"

  description = "Trigger for 1 time every single work day"

  flexible_time_window {
    mode = "OFF"
  }

  # at 21:00 UTC fron Mon to Fri
  schedule_expression = "cron(0 21 ? * MON-FRI *)"

  target {
    arn      = aws_lambda_function.lambda_scrapper.arn
    role_arn = aws_iam_role.terraform_lambda_role.arn
  }

# Terraform is complaining about these for some reason
#  tags = {
#    ManagedBy = "Terraform"
#  }  
}
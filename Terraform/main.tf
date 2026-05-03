provider "aws" {
  # options applied to all resources managed by this provider
  # provider name "aws" must match required_providers on terraform.tf
  region = "us-east-1"

  assume_role {
    role_arn = "arn:aws:iam::${var.account_id}:role/TerraformExecutionRole"
  }
}

# multiple providers can be used
# multiples of the same provider can also be used

# data blocks can be used to query the provider for information on resources
# meaning data is something THAT ALREADY EXISTS
locals {
  # Ticket config files inside the Terraform structure
  scrapper_ticket_files_path = "${path.module}/s3/tickets/"

  # Prefix path for the scrapper ticket files
  # to be stored on the s3 bucket
  scrapper_ticket_s3_path = "tickets"
}
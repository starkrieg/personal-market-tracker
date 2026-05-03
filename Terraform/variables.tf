variable "account_id" {
    type        = string
    default     = ""
    description = "Account ID"
    sensitive   = false
    nullable    = false
    ephemeral   = false
}

variable "notification_emails" {
    type        = list
    default     = []
    description = "Array of emails to be notified"
    sensitive   = false
    nullable    = false
    ephemeral   = false
}

variable "region" {
    type        = string
    default     = "us-east-1"
    description = "Region to deploy structures"
    sensitive   = false
    nullable    = false
    ephemeral   = false
}
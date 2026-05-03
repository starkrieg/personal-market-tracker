terraform {
  required_providers {
    aws = {
      # shortened from registry.terraform.io/hashicorp/aws
      source = "hashicorp/aws"
      # fixed major to 5, minor 92 or greater
      version = "~> 6.42"
    }
  }

  # terraform version
  required_version = ">= 1.2"
}
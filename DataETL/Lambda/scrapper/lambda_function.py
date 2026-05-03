import os
from datetime import date
import json
import boto3
import shutil

import data_scrapper

SCRAP_S3_BUCKET = os.environ['SCRAP_S3_BUCKET'] # S3 Bucket name, where the files are stored

TICKET_LIST_FOLDER_PATH = os.environ['TICKET_LIST_FOLDER_PATH'] # Folder path on S3 where the ticket list is stored

# 104857600 = 100MB
TMP_SIZE_ZIP_THRESHOLD_BYTES = int(os.environ['TMP_SIZE_ZIP_THRESHOLD_BYTES']) # /tmp size threshold to trigger zip file creation

print('Loading function')

s3 = boto3.resource('s3')

counterZipCreated = 1

# Entrypoint of the lambda
def lambda_handler(event, context):	
	global counterZipCreated
	counterZipCreated = 1

	bucket = s3.Bucket(SCRAP_S3_BUCKET)

	totalBytes = 0

	# Make sure the tmp is clean
	cleanTmp()

	for obj in bucket.objects.filter(Prefix=TICKET_LIST_FOLDER_PATH):
		if obj.key.endswith('.json'):
			print(f'Key: {obj.key}')
			objBody = obj.get()['Body'].read().decode('utf-8')

			# Note: json.loads() can directly accept bytes in Python 3.6+
			objBody = json.loads(objBody)

			tick = objBody['ticket']
			sources = objBody['sources']
			# for every source, scrap the website 
			# and store the html on the S3
			print(f'Scraping {tick}...')
			for url in sources:
				fileSizeBytes = data_scrapper.scrape(url, tick)
				totalBytes += fileSizeBytes
				# Will zip when size reaches 10 MB
				if totalBytes > TMP_SIZE_ZIP_THRESHOLD_BYTES:
					# compile the files into a zip
					# store the zip file on S3
					data_scrapper.compileTmpFilesIntoZip(s3, SCRAP_S3_BUCKET, counterZipCreated)
					counterZipCreated += 1
					# clean the tmp, then keep going on the scrap
					cleanTmp()
	
	# compile the remaining files on tmp into a zip
	# store the zip file on S3
	data_scrapper.compileTmpFilesIntoZip(s3, SCRAP_S3_BUCKET, counterZipCreated)
	# tmp will be cleaned on Lambda start

	# push to SQS the zipFilePath for today's scrapped data
	# data_scrapper.getZipFilePath()

	print(f'Log End')

def cleanTmp():
	for filename in os.listdir('/tmp/'):
		file_path = os.path.join('/tmp/', filename)
		try:
			if os.path.isfile(file_path) or os.path.islink(file_path):
				os.unlink(file_path)
			elif os.path.isdir(file_path):
				shutil.rmtree(file_path)
		except Exception as e:
			print(f'Failed to delete {file_path}. Reason: {e}')
	print(f'/tmp is clean')

from urllib.request import Request, urlopen
from datetime import date
import os
import shutil

raw_storage_path = os.path.join( '/tmp' , 'tmpStorage' )

zippedRawsPath = os.path.join('/tmp', 'zippedRaws')

today = date.today().isoformat()

zipFilePath = os.path.join( 'raws', today ) # will push this info to SQS

# The path on the S3 bucket where zipped files for today's scraped data will be stored
def getZipFilePath():
    return zipFilePath

def compileTmpFilesIntoZip(s3, s3_bucket, zipFileCount):
	shutil.make_archive(zippedRawsPath, 'zip', raw_storage_path)
	jobKey = today + '_' + str(zipFileCount)
	zipJobStoragePath = os.path.join( zipFilePath, jobKey )
	fileKey = zipJobStoragePath + '.zip'
	s3.Object(s3_bucket, fileKey).upload_file(zippedRawsPath + '.zip')
	print(f'Archive {fileKey} created')

def scrape(website_url, ticket):
    # check if raw folder exists, if not, create it
    if not os.path.exists(raw_storage_path):
        os.mkdir(raw_storage_path)
    ###

    raw_ticket_path = os.path.join( raw_storage_path , f'{ticket}' )

    # check if a folder exists for this ticket for today
    # if not, create it
    if not os.path.exists(raw_ticket_path):
        os.mkdir(raw_ticket_path)
    ###

    # scraped raw data will be stored in the individual ticket folder

    # check if the data file already exists
    sanitized_website = website_url.replace('https://', '').replace('http://', '').replace('.', '_').split('/')[0]
    raw_ticket_website_file_path = os.path.join(raw_ticket_path , f'{sanitized_website}.html')

    # if file already exists, move on
    if os.path.exists(raw_ticket_website_file_path):
        # file already exists
        print(f'Already exists data for [{today}][{ticket}][{website_url}] in {raw_ticket_website_file_path} - size {os.path.getsize(raw_ticket_website_file_path)} bytes')
        return os.path.getsize(raw_ticket_website_file_path)
    ###

    ## format website path to find the ticket
    website_to_scrape = website_url
    
    req = Request(website_to_scrape, headers={'User-Agent': 'AWS Lambda'})
    raw_scraped_page = urlopen(req)
    #print(f'Raw scrape: {raw_scraped_page}')

    # check if something went wrong
    if raw_scraped_page.status != 200:
        #something went wront, show alert and give up for this website and ticket
        print(f'Error when scraping {website_to_scrape} - {raw_scraped_page.status}')
        return 0
    ###

    contentBody = raw_scraped_page.read().decode('utf-8')

    raw_file = open(raw_ticket_website_file_path, "w", encoding="utf-8")
    raw_file.write(contentBody)
    raw_file.close()

    print(f'Stored scrapped file {raw_ticket_website_file_path} - size {os.path.getsize(raw_ticket_website_file_path)} bytes')

    return os.path.getsize(raw_ticket_website_file_path)
### close def
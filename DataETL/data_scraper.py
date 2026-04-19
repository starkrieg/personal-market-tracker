import requests
from bs4 import BeautifulSoup
from datetime import date
import os
from fake_useragent import UserAgent

base_storage_path = os.path.join( os.path.dirname(os.path.abspath(__file__)) , 'storage' )
raw_storage_path = os.path.join( base_storage_path , 'raw' )

today = date.today().isoformat()

def scrape(website_url, ticket):
    # check if raw folder exists, if not, create it
    if not os.path.exists(raw_storage_path):
        os.mkdir(raw_storage_path)
    ###

    raw_today_storage_path = os.path.join( raw_storage_path , f'{today}' )

    # check if folder exists for today
    # if not, create it
    if not os.path.exists(raw_today_storage_path):
        os.mkdir(raw_today_storage_path)
    ###

    raw_today_ticket_path = os.path.join( raw_today_storage_path , f'{ticket}' )

    # check if a folder exists for this ticket for today
    # if not, create it
    if not os.path.exists(raw_today_ticket_path):
        os.mkdir(raw_today_ticket_path)
    ###

    # scraped raw data will be stored in the individual ticket folder

    # check if the data file already exists
    sanitized_website = website_url.replace('https://', '').replace('http://', '').replace('.', '_').split('/')[0]
    raw_today_ticket_website_file_path = os.path.join(raw_today_ticket_path , f'{sanitized_website}.html')

    # if file already exists, move on
    if os.path.exists(raw_today_ticket_website_file_path):
        # file already exists
        print(f'Already exists data for [{today}][{ticket}][{website_url}] in {raw_today_ticket_website_file_path}')
        return True
    ###

    ## format website path to finde the ticket

    # if valid input, query actual data
    ua = UserAgent()

    website_to_scrape = website_url;
    #print(website_to_scrape)
    raw_scraped_page = requests.get(website_to_scrape, headers={'User-Agent':str(ua.chrome)})

    # check if something went wrong
    if raw_scraped_page.status_code != 200:
        #something went wront, show alert and give up for this website and ticket
        print(f'Error when scraping {website_to_scrape} - {raw_scraped_page.status_code}')
        return False;
    ###

    soup = BeautifulSoup(raw_scraped_page.text, "html.parser")
    contentBody = soup.find('body')

    raw_file = open(raw_today_ticket_website_file_path, "w", encoding="utf-8")
    raw_file.write(contentBody.prettify())
    raw_file.close()

    return True
### close def
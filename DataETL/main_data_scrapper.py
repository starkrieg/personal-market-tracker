from datetime import date
import os
import json

import data_scraper

tickets_path = os.path.join( os.path.dirname(os.path.abspath(__file__)) , 'tickets' )

def run():
    today = date.today().isoformat()

    print("### Start data scraping ###")
    print(f"Today is {today}")

    if not os.path.exists(tickets_path):
        print(f"Could not find ticket folder on {tickets_path}")
        exit()
    ## end if

    ticket_list = os.listdir(tickets_path)

    if len(ticket_list) == 0:
        print(f"No tickets found at folter {tickets_path}")
        exit()
    ## end if

    for ticketPath in ticket_list:
        ticketFile = open(os.path.join(tickets_path , ticketPath), "r", encoding="utf-8")
        try:
            ticketObj = json.load(ticketFile)
            ticketFile.close()
        except Exception as e:
            print(f"Error when loading JSON file {ticketPath}: {e}")
            continue
        #end except
        
        if ticketObj is None:
            print(f"Not a valid JSON object at {ticketPath}")
            continue
        #end if

        if 'ticket' not in ticketObj or ticketObj['ticket'] is None or len(ticketObj['ticket']) == 0:
            print(f"No identification found for ticket on {ticketPath}")
            continue
        # end if

        if 'sources' not in ticketObj or ticketObj['sources'] is None or len(ticketObj['sources']) == 0:
            print(f"No sources found on ticket {ticketPath}")
            continue
        # end if

        for website in ticketObj['sources']:
            if len(website) == 0:
                print(f"Empty website on ticket {ticketPath}")
                continue
            ## end if
            data_scraper.scrape(website, ticketObj['ticket'])
        # end for
    ## end for

    # website = 'https://statusinvest.com.br'
    # ticket = 'bbas3'
    # #isSuccess = data_scraper.get_raw_data('https://statusinvest.com.br', ticket)

    print("### End of data scraping ###")
###
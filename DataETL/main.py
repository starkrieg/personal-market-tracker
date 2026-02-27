import main_data_scrapper
import main_data_processor

if __name__ == "__main__":
    # scrape the daily data into raw files
    # will not check if data already archived
    main_data_scrapper.run()

    # process raw data and archive it
    main_data_processor.run()

    # data load requires database to be online
    # currently no script to run the database and make sure schema is correct
    # so run the data load manually 

    # end of main
###
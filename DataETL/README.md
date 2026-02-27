## An ETL based on Python for E(xtract) and T(ransform), and Postgres to L(oad).

Add stock market tickets to the list inside the `tickets` folder, and add the source websites where you want data to be extracted. Process the raw data, then load it into a database for safekeeping and analysis.

### Data Extraction

Extracted webpages will be stored raw in the folder `/storage/raw`, grouped by folders that identify the day the data was extracted and the ticket, with the format `yyyy-mm-dd/{ticket name}`. Files are named after the source website data was extracted from.

You can run Data Extraction on-demand by running the file `main_data_scrapper.py`

### Data Processing

Data processing will iterate over all html files stored in the `/storage/raw` folder. A specific processing workflow must exist for the source website. Add individual data processors in the folder `/processors`, and add a call to them inside the `data_processor.py` and `main_data_processor.py`.

Raw `yyyy-mm-dd` folders that are processed are zipped and moved to the folder `/archive/raw` to reduce used space, but still preserve source data.

You can run Data Processing on-demand by running the file `main_data_processor.py`

### Scheduled Data Extraction and Processing 

You can run Data Extraction and Processing as a single script by running `main.py`, which allows for easier task scheduling.

### Data Load

Data Load will try to load processed data into a running instance of a Postgres database. 

At this point, no automation was created to start an instance of Postgres if not available. Therefore, Data Load is currently an entirely manual process.

In addition, no automation was created to backup database data after the load process. Therefore, processed data is not zipped or moved, as it has to remain ready to be reloaded into a database instance if necessary.

Database schema files and docker-compose for Postgres and Adminer (for admin panel) are in `/database`.
Main data load files are `main_data_load.py` and `load_processed.py`.

You can run Data Load on-demand by running `main_data_load.py`.

### Pending Features

- Review code abstraction for easier addition of data processors
- Add scripted automation for data load as an option
- TBD

### 
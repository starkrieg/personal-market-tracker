import os
from model.standard_data_model import StandardizedData, DebtData, EfficiencyData, GrowthData, ProfitData, ValuationData
import json

# Library for interacting with Postgres DB
# Kept to maintain existing DB Errors for now
import psycopg2

# global database connection
database_connection = None

#
# Load processed data (json files) into Postgree Database
# Processed files will be zipped and stored in the archive\processed folder
# Original stored processed json files will be deleted
#
# This method is separated from the data processors because the Postgree is not always running
# So processed data will only be loaded and archived when needed
#
def load_processed(databaseConn, processedStoragePath: str):
    global database_connection

    # set the global database connection
    database_connection = databaseConn

    if len(processedStoragePath) == 0:
        print("Error: Cannot load processed data from empty path")
        return
    ###

    if not os.path.exists(processedStoragePath):
        print(f"Error: Processed storage path does not exist at {processedStoragePath}")
    ###

    # assume that all found folders and tickers have not yet been loaded into database
    processedFolders = os.listdir(processedStoragePath)

    for folderDay in processedFolders:
        # every folder is a date in format yyyy-mm-dd
        folderPath = os.path.join(processedStoragePath, folderDay)
        
        tickerList = os.listdir(folderPath)
        for tickerFile in tickerList:
            # ticker json file named after a ticker, like AGRO3.json
            tickerPath = os.path.join( folderPath , tickerFile )
            ticker = tickerFile.removesuffix('.json')
            __load_ticker_data(folderDay, ticker, tickerPath)
            #
        ###
        print("...")
    ###

    print("\n ### Finished loading all available processed data!")
###

def __load_ticker_data(day: str, ticker: str, tickerPath: str):
    # open ticker file as json object
    # StandardizedData is the same model used when data was stored
    
    tickerData: StandardizedData = json.load(open(tickerPath))

    # check if ticket exists
    # otherwise, create it
    isSuccess = __CreateTicker(ticker)
    if not isSuccess:
        print("Error when creating ticker, load process will be stopped")
        return
    ###
    
    ### assume ticket record exists at this point

    # insert DEBT data
    isSuccess = __InsertDebtData(ticker, day, tickerData['debtData'])
    if not isSuccess:
        print("Error when adding debt data, load process will be stopped")
        return
    ###

    # insert EFFICIENCY data
    isSuccess = __InsertEfficiencyData(ticker, day, tickerData['efficiencyData'])
    if not isSuccess:
        print("Error when adding efficiency data, load process will be stopped")
        return
    ###

    # insert GROWTH data
    isSuccess = __InsertGrowthData(ticker, day, tickerData['growthData'])
    if not isSuccess:
        print("Error when adding growth data, load process will be stopped")
        return
    ###

    # insert PROFIT data
    isSuccess = __InsertProfitData(ticker, day, tickerData['profitData'])
    if not isSuccess:
        print("Error when adding profit data, load process will be stopped")
        return
    ###

    # insert VALUATION data
    isSuccess = __InsertValuationData(ticker, day, tickerData['valuationData'])
    if not isSuccess:
        print("Error when adding valuation data, load process will be stopped")
        return
    ###

    # print(f"Finished loading for {day} {ticker}")
###

# Returns boolean to identify success or failure
def __CreateTicker(ticker: str):
    global database_connection
    # get existing or open new DB connection
    connection = database_connection
    try:
        cursor = connection.cursor()
        cursor.execute(f"""
                    SELECT 1 from TICKET
                    where NAME = '{ticker}'
                    """)
        results = cursor.fetchall()
        if len(results) == 0:
            # Not exists, create it
            insertQuery = f"""
                INSERT INTO TICKET (NAME, LOCATION, SINCE, SECTOR, SUBSECTOR, SEGMENT) 
                VALUES ('{ticker}', null, null, null, null, null)
            """
            insertCursor = connection.cursor()
            insertCursor.execute(insertQuery)
            connection.commit()
            # record should exist at this point
        ###
        # else ticker already exists, move on
        return True
    except (psycopg2.DatabaseError, Exception) as error:
        print(f"Error checking/creating ticket to the database: {error}")
        connection.close()
        return False
    ###
###

# Simple method for reusability on database values
# So None gets converted to "null" as needed
def __GetValueOrNull(value):
    if value == None:
        return "null"
    else:
        return value
###

# Returns boolean to identify success or failure
def __InsertDebtData(ticker: str, day: str, debtData: DebtData):
    global database_connection
    # get existing or open new DB connection
    connection = database_connection
    try:
        cursor = connection.cursor()
        cursor.execute(f"""
                    SELECT 1 from INDICATORS_DEBT 
                    where TICKET_NAME = '{ticker}' 
                        and STORAGE_DATE = to_date('{day}', 'yyyy-mm-dd')
                    """)
        results = cursor.fetchall()
        if len(results) == 0:
            # Not exists, create it
            insertQuery = f"""
INSERT INTO INDICATORS_DEBT (TICKET_NAME, STORAGE_DATE, DIV_LIQ_OVER_PL, 
DIV_LIQ_OVER_EBIT, PL_OVER_ATIVOS, PASS_OVER_ATIVOS, 
LIQ_CORRENTE) 
            VALUES ('{ticker}', to_date('{day}', 'yyyy-mm-dd'), {__GetValueOrNull(debtData['div_liq_over_pl'])}, 
            {__GetValueOrNull(debtData['div_liq_over_ebit'])}, {__GetValueOrNull(debtData['pl_over_ativos'])}, {__GetValueOrNull(debtData['pass_over_ativos'])},
            {__GetValueOrNull(debtData['liq_corrente'])}
            )
"""
            insertCursor = connection.cursor()
            insertCursor.execute(insertQuery)
            connection.commit()
            # record should exist at this point
            print(f"Inserted DebtData for {day} | {ticker}")
        ###
        # If already exists, do not load again
        return True
    except (psycopg2.DatabaseError, Exception) as error:
        print(f"Error checking/creating Debt data for {day} and ticket {ticker} to the database: {error}")
        connection.close()
        return False
    ###
###

# Returns boolean to identify success or failure
def __InsertEfficiencyData(ticker: str, day: str, efficiencyData: EfficiencyData):
    global database_connection
    # get existing or open new DB connection
    connection = database_connection
    try:
        cursor = connection.cursor()
        cursor.execute(f"""
                    SELECT 1 from INDICATORS_EFFICIENCY 
                    where TICKET_NAME = '{ticker}' 
                        and STORAGE_DATE = to_date('{day}', 'yyyy-mm-dd')
                    """)
        results = cursor.fetchall()
        if len(results) == 0:
            # Not exists, create it
            insertQuery = f"""
INSERT INTO INDICATORS_EFFICIENCY (TICKET_NAME, STORAGE_DATE, MARGEM_BRUTA, 
MARGEM_EBITDA, MARGEM_EBIT, MARGEM_LIQ) 
            VALUES ('{ticker}', to_date('{day}', 'yyyy-mm-dd'), {__GetValueOrNull(efficiencyData['margem_bruta'])}, 
            {__GetValueOrNull(efficiencyData['margem_ebitda'])}, {__GetValueOrNull(efficiencyData['margem_ebit'])}, {__GetValueOrNull(efficiencyData['margem_liq'])}
            )
"""
            insertCursor = connection.cursor()
            insertCursor.execute(insertQuery)
            connection.commit()
            # record should exist at this point
            print(f"Inserted EfficiencyData for {day} | {ticker}")
        ###
        # If already exists, do not load again
        return True
    except (psycopg2.DatabaseError, Exception) as error:
        print(f"Error checking/creating Efficiency data for {day} and ticket {ticker} to the database: {error}")
        connection.close()
        return False
    ###
###

# Returns boolean to identify success or failure
def __InsertGrowthData(ticker: str, day: str, growthData: GrowthData):
    global database_connection
    # get existing or open new DB connection
    connection = database_connection
    try:
        cursor = connection.cursor()
        cursor.execute(f"""
                    SELECT 1 from INDICATORS_GROWTH 
                    where TICKET_NAME = '{ticker}' 
                        and STORAGE_DATE = to_date('{day}', 'yyyy-mm-dd')
                    """)
        results = cursor.fetchall()
        if len(results) == 0:
            # Not exists, create it
            insertQuery = f"""
INSERT INTO INDICATORS_GROWTH (TICKET_NAME, STORAGE_DATE, CAGR_RECEITAS_5_ANOS, 
CAGR_LUCROS_5_ANOS) 
            VALUES ('{ticker}', to_date('{day}', 'yyyy-mm-dd'), {__GetValueOrNull(growthData['cagr_receitas_5_anos'])}, 
            {__GetValueOrNull(growthData['cagr_lucros_5_anos'])})
"""
            insertCursor = connection.cursor()
            insertCursor.execute(insertQuery)
            connection.commit()
            # record should exist at this point
            print(f"Inserted GrowthData for {day} | {ticker}")
        ###
        # If already exists, do not load again
        return True
    except (psycopg2.DatabaseError, Exception) as error:
        print(f"Error checking/creating Growth data for {day} and ticket {ticker} to the database: {error}")
        connection.close()
        return False
    ###
###

# Returns boolean to identify success or failure
def __InsertProfitData(ticker: str, day: str, profitData: ProfitData):
    global database_connection
    # get existing or open new DB connection
    connection = database_connection
    try:
        cursor = connection.cursor()
        cursor.execute(f"""
                    SELECT 1 from INDICATORS_PROFIT 
                    where TICKET_NAME = '{ticker}' 
                        and STORAGE_DATE = to_date('{day}', 'yyyy-mm-dd')
                    """)
        results = cursor.fetchall()
        if len(results) == 0:
            # Not exists, create it
            insertQuery = f"""
INSERT INTO INDICATORS_PROFIT (TICKET_NAME, STORAGE_DATE, ROE, 
ROA, ROIC, GIRO_ATIVOS) 
            VALUES ('{ticker}', to_date('{day}', 'yyyy-mm-dd'), {__GetValueOrNull(profitData['roe'])}, 
            {__GetValueOrNull(profitData['roa'])}, {__GetValueOrNull(profitData['roic'])}, {__GetValueOrNull(profitData['giro_ativos'])}
            )
"""
            insertCursor = connection.cursor()
            insertCursor.execute(insertQuery)
            connection.commit()
            # record should exist at this point
            print(f"Inserted ProfitData for {day} | {ticker}")
        ###
        # If already exists, do not load again
        return True
    except (psycopg2.DatabaseError, Exception) as error:
        print(f"Error checking/creating Profit data for {day} and ticket {ticker} to the database: {error}")
        connection.close()
        return False
    ###
###

# Returns boolean to identify success or failure
def __InsertValuationData(ticker: str, day: str, valuationData: ValuationData):
    global database_connection
    # get existing or open new DB connection
    connection = database_connection
    try:
        cursor = connection.cursor()
        cursor.execute(f"""
                    SELECT 1 from INDICATORS_VALUATION 
                    where TICKET_NAME = '{ticker}' 
                        and STORAGE_DATE = to_date('{day}', 'yyyy-mm-dd')
                    """)
        results = cursor.fetchall()
        if len(results) == 0:
            # Not exists, create it
            insertQuery = f"""
INSERT INTO INDICATORS_VALUATION (TICKET_NAME, STORAGE_DATE, DAY_VALUE, DIV_YIELD, 
PRECO_OVER_LUCRO, PEG_RATIO, P_OVER_VP,
EV_OVER_EBITDA, EV_OVER_EBIT, P_OVER_EBITDA,
P_OVER_EBIT, VPA, P_OVER_ATIVO,
LPA, P_OVER_SR, P_OVER_CAP_GIRO,
P_OVER_ATIVO_CIRC_LIQ
) 
            VALUES ('{ticker}', to_date('{day}', 'yyyy-mm-dd'), {__GetValueOrNull(valuationData['day_value'])}, {__GetValueOrNull(valuationData['div_yield'])}, 
            {__GetValueOrNull(valuationData['preco_over_lucro'])}, {__GetValueOrNull(valuationData['peg_ratio'])}, {__GetValueOrNull(valuationData['p_over_vp'])},
            {__GetValueOrNull(valuationData['ev_over_ebitda'])}, {__GetValueOrNull(valuationData['ev_over_ebit'])}, {__GetValueOrNull(valuationData['p_over_ebitda'])},
            {__GetValueOrNull(valuationData['p_over_ebit'])}, {__GetValueOrNull(valuationData['vpa'])}, {__GetValueOrNull(valuationData['p_over_ativo'])},
            {__GetValueOrNull(valuationData['lpa'])}, {__GetValueOrNull(valuationData['p_over_sr'])}, {__GetValueOrNull(valuationData['p_over_cap_giro'])},
            {__GetValueOrNull(valuationData['p_over_ativo_circ_liq'])}
            )
"""
            insertCursor = connection.cursor()
            insertCursor.execute(insertQuery)
            connection.commit()
            # record should exist at this point
            print(f"Inserted ValuationData for {day} | {ticker}")
        ###
        # If already exists, do not load again
        return True
    except (psycopg2.DatabaseError, Exception) as error:
        print(f"Error checking/creating Valuation data for {day} and ticket {ticker} to the database: {error}")
        connection.close()
        return False
    ###
###
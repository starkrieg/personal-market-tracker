from bs4 import BeautifulSoup
import os

import model.standard_data_model as standard_data_model
import processors.processor_investidor10 as processor_investidor10
import processors.processor_statusInvest as processor_statusInvest

# Add more methods as necessary

def processStatusInvest(filePath):
    if not os.path.exists(filePath):
        print(f"Error: StatusInvest file to process does not exist at {filePath}")
        return None
    ###

    outputData: standard_data_model = processor_statusInvest.process(filePath)

    return outputData
###

def processInvestidor10(filePath):
    if not os.path.exists(filePath):
        print(f"Error: Investidor10 file to process does not exist at {filePath}")
        return None
    ###

    outputData: standard_data_model = processor_investidor10.process(filePath)

    return outputData
###

import os

import json
from fastapi.encoders import jsonable_encoder

import shutil

import data_processor
from model.standard_data_model import StandardizedData, mergeStandardData

base_storage_path = os.path.dirname(os.path.abspath(__file__)) + '\\storage\\'
processed_storage = base_storage_path + 'processed\\'
raw_storage = base_storage_path + 'raw\\'
archive_storage = base_storage_path + 'archive\\raw\\' # specific archive for raw data

def processDayTickFolder(dayFolder, tickFolder):

    tickerData: StandardizedData = StandardizedData()
    dataFiles = os.listdir(raw_storage + dayFolder + '\\' + tickFolder)

    # for every file, check the type
    # statusinvest, investidor10, btgpactual, etc
    # process every file in its own way

    # flag to signal that an error occured somewhere in the file processing structure
    # use it to hold back on retiring raw data until the error is verified
    isError = False

    for scrappedFile in dataFiles:
        ###

        filePath = raw_storage + dayFolder + '\\' + tickFolder + '\\' + scrappedFile
        fileData: StandardizedData = StandardizedData()

        # Scrapped files are expected to be named after source website
        # For now, required to add more options here to process new data sources

        if scrappedFile == "statusinvest_com_br.html":
            ### process status invest file
            fileData = data_processor.processStatusInvest(filePath)
        elif scrappedFile == "investidor10_com_br.html":
            ### process investidor10 file
            fileData = data_processor.processInvestidor10(filePath)
        else:
            ### report as error
            print(f"Error: Unexpected file [{scrappedFile}] at {raw_storage + dayFolder + '\\' + tickFolder}")
            fileData = None
        ###

        if fileData == None:
            isError = True
        ###

        # merge all data into a single object
        tickerData = mergeStandardData(tickerData, fileData)
    ###

    # return tupple of (data, error flag)
    return (tickerData, isError)
###

def run():
    # list all folders inside RAW
    # all folders 
    rawFoldersByDay = os.listdir(raw_storage)

    for dayFolder in rawFoldersByDay:
        # list all tick folders inside a day group
        # all folders 

        print(f"Day {dayFolder}", end=" ", flush=True)

        processedTickerPath = processed_storage + dayFolder + '\\'

        # check if a folder exists for this ticket that today
        # if not, create it
        if not os.path.exists(processedTickerPath):
            os.mkdir(processedTickerPath)
        ###

        isDayProcessError = False

        rawFoldersByTicksInADay = os.listdir(raw_storage + dayFolder)
        for tickFolder in rawFoldersByTicksInADay:
            ###
            print(".", end="", flush=True)
            
            processedTickerFilePath = processedTickerPath + tickFolder + ".json"

            isAlreadyProcessedTick = os.path.exists(processedTickerFilePath)

            # only process data if not already processed
            if not isAlreadyProcessedTick:
                (tickerData, isTickerProcessError) = processDayTickFolder(dayFolder, tickFolder)

                # store JSON object in the processed_storage folder
                tickerFile = open(processedTickerFilePath, "w", encoding="utf-8")

                jsonData = jsonable_encoder(tickerData)

                json.dump(jsonData, tickerFile)
                #tickerFile.write(tickerData)
                tickerFile.close()

                # enable dayProcessError only when still False
                if isTickerProcessError and not isDayProcessError:
                    isDayProcessError = True
                ###
                # if an error occured, do not retire the raw data folder
                # if everything went right, retire the data into a zip folder for compaction / retiring
            ###
        ###
        # day is Done
        
        if isDayProcessError:
            # if an error occured, do not retire the raw data folder
            print(f"got processings errors {dayFolder}", end="", flush=True)
        else:
            # day is fone
            print("..Done with no errors!")
            # if everything went right, retire the data into a zip folder for compaction / retiring
            # after creating the archive, the original raw folder should be removed, so its not processed again

            print("Moving to archive data......", end="", flush=True)
            # raw_storage + dayFolder
            archivedFile = shutil.make_archive(archive_storage + dayFolder, 'zip', root_dir=(raw_storage + dayFolder))
            print(f"Archived {archivedFile}")

            print("Cleaning old data...........", end="", flush=True)
            # delete original raw folder
            shutil.rmtree(raw_storage + dayFolder)
            print(f"raw {dayFolder} data was cleaned out!")
        ###

    ###
    print("All raw files processed!")
###
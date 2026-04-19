import os
from load_processed import load_processed

from model import PostgresDatabase

import argparse

parser = argparse.ArgumentParser()

# The -- makes the argument into optional
parser.add_argument("--DatabaseHost", 
    help="The host for the database. Defaults to 'localhost'. Databases supported: Postgres", 
    default='localhost', type=str)

# The -- makes the argument into optional
parser.add_argument("--DatabasePort", 
    help="The port for Database access. Defaults to '5432'.", 
    default='5432', type=str)

# The -- makes the argument into optional
parser.add_argument("--DatabaseUsername", 
    help="The username for the database. Defaults to 'postgres'.", 
    default='postgres', type=str)

# The -- makes the argument into optional
parser.add_argument("--DatabasePassword", 
    help="The password for the database. Defaults to 'pass'.", 
    default='pass', type=str)

args = parser.parse_args()

print("Starting Data Loader")
print(f"Database Host: {args.DatabaseHost}")
print(f"Database Port: {args.DatabasePort}")
print(f"Database User: {args.DatabaseUsername}")
print(f"Database Pass: ***")

base_storage_path = os.path.join( os.path.dirname(os.path.abspath(__file__)) , 'storage' )
processed_storage = os.path.join ( base_storage_path , 'processed' )

# Define variables to facilitate validation of configs before starting DB connection
databaseHost = args.DatabaseHost
databasePort = args.DatabasePort
databaseUser = args.DatabaseUsername
databasePass = args.DatabasePassword

# Default initial database
data_connection = PostgresDatabase.__postgree_connection(host=databaseHost,
                          port=databasePort,
                          user=databaseUser,
                          password=databasePass)

if not data_connection is None:
    # Load the data to the passed data connection
    load_processed(data_connection, processed_storage)
else:
    print(f'[ERROR] Error setting up database connection, data load aborted!')
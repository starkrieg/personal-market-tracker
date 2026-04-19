# Library for interacting with Postgres DB
import psycopg2

database_connection = None

# Connect to the PostgreSQL database server and return a connection object
# Must remember to close the connection manually
def __postgree_connection(host="localhost",
                          port="5432",
                          user="postgres",
                          password="pass"):
    global database_connection

    if database_connection != None:    
        return database_connection
    ###

    try:
        # Establish the connection using a connection string or keyword arguments
        database_connection = psycopg2.connect(
            host=host,
            database="postgres",
            user=user,
            password=password, # default pass - TODO change to vault/secret on prod
            port=port  # Port is optional; defaults to 5432
        )

        # Test connection
        # Create a cursor object to execute SQL queries
        cursor = database_connection.cursor()
        cursor.execute("SELECT version();")
        db_version = cursor.fetchone()
        print(f"PostgreSQL database version: {db_version}\n")
        # Remember to close the cursor
        cursor.close()

        print("Connected to PostgreSQL database successfully!")
    except (psycopg2.DatabaseError, Exception) as error:
        print(f"Error connecting to the database: {error}")
        if database_connection:
            # Make sure connection is closed if error
            database_connection.close()
            print("Database connection closed because of error.")
    ###
    
    return database_connection
###
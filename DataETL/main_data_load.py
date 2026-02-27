import os
from load_processed import load_processed

base_storage_path = os.path.dirname(os.path.abspath(__file__)) + '\\storage\\'
processed_storage = base_storage_path + 'processed\\'

load_processed(processed_storage)
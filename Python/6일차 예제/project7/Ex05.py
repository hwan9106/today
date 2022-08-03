import pprint

from pymongo import MongoClient
from datetime import datetime

client = MongoClient("mongodb+srv://ithuman202204:woaldjqtek2@cluster0.laifp.mongodb.net/?retryWrites=true&w=majority")
collection = client.mydb.data
for hanja in collection.find({"d":"특급"}):
    # print(book)
    pprint.pprint(hanja)
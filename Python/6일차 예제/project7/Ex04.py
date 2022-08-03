import pprint

from pymongo import MongoClient
from datetime import datetime

client = MongoClient("mongodb+srv://ithuman202204:woaldjqtek2@cluster0.laifp.mongodb.net/?retryWrites=true&w=majority")
collection = client.test.books
for book in collection.find():
    # print(book)
    pprint.pprint(book)
from pymongo import MongoClient

# 방법1 - URI
# mongodb_URI = "mongodb://localhost:27017/"
# client = MongoClient(mongodb_URI)

# 방법2 - HOST, PORT
client = MongoClient("mongodb+srv://ithuman202204:woaldjqtek2@cluster0.laifp.mongodb.net/?retryWrites=true&w=majority")

print(client.list_database_names())

for name in client.list_database_names():
    print(name)
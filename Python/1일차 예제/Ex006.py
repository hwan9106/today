import sys

print(sys.version)
data = input("Enter a number: ")
print(data, type(data))

data = eval(input("Enter a number: "))
print(data, type(data))

data = int(input("Enter a number(2): "), 2)
print(data, type(data))

data = int(input("Enter a number(8): "), 8)
print(data, type(data))

data = int(input("Enter a number(10): "), 10)
print(data, type(data))

data = int(input("Enter a number(16): "), 16)
print(data, type(data))

colors = input("Your favourite colours? ")
# Your favourite colours? ["red","green","blue"]
print(colors, type(colors))

colors = eval(input("Your favourite colours? "))
# Your favourite colours? ["red","green","blue"]
print(colors, type(colors))
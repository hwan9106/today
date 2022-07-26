# range함수
a=range(10)
print(a)
print(str(a))
print(str(list(a)))

for i in a:
    print(i, end=" ")
print()

print(str(list(range(10001))).count('8'))

for i in range(10,20):
    print(i, end=" ")
print()

for i in range(10,20, 2):
    print(i, end=" ")
print()
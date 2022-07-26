for i in range(2, 10):
    for j in range(1, 10):
        print("{0} * {1} = {2}".format(i, j, i * j))

result = [x * y for x in range(2, 10) for y in range(1, 10)]
print(result)

for index in range(len(result)):
    print("{0:4}".format(result[index]), end="")
    if (index + 1) % 9 == 0: print()

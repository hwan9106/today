f = open("새파일2.txt", 'w')

for i in range(1, 11):
    data = "{0:03}번째 줄입니다.\n".format(i)
    f.write(data)
f.close()


f = open("새파일2.txt", 'r')
line = f.readline()  # 1줄 읽는다.
print(line)
f.close()

print("*" * 50)

f = open("새파일2.txt", 'r')
while True:
    line = f.readline()
    if not line: break
    print(line, end="")
f.close()

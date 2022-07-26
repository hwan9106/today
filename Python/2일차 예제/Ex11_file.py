f = open("song.txt", 'r')
while True:
    line = f.readline()
    if not line: break
    if line == "\n":
        print("*" * 70)
    else:
        print(line, end="")
f.close()

sf = open("song.txt", 'r')
df = open("song3.txt", 'a')  # 추가모드 : 없으면 만들고 있으면 추가한다.
while True:
    line = sf.readline()
    if not line: break
    if line == "\n":
        df.write("*" * 70)
        df.write("\n")
    else:
       df.write(line)
sf.close()
df.close()

sf = open("song.txt", 'r')
df = open("song2.txt", 'w')
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

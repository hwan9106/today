sf = open("song.txt", 'r')

# 리스트로 읽기
lines = sf.readlines();
print(type(lines), lines)

for line in lines:
    print(line.strip())

sf.close()

print("*" * 80)

# 1번에 스트링으로 모두 읽기
sf = open("song.txt", 'r')
print(sf.read())
sf.close()



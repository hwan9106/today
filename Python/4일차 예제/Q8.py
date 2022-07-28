"""
Q8 역순 저장
다음과 같은 내용의 파일 abc.txt가 있다.

AAA
BBB
CCC
DDD
EEE
이 파일의 내용을 다음과 같이 역순으로 바꾸어 저장하시오.

EEE
DDD
CCC
BBB
AAA
"""


def view(fileName):
    f = open(fileName,'r')
    print(f.read())
    f.close()


def read(fileName):
    f = open(fileName,'r')
    result = f.readlines()
    f.close()
    return result


def save(srcFileName, dstFileName):
    lines = read(srcFileName)  # 파일을 라인의 리스트로 읽기
    f = open(dstFileName, 'w') # 쓰기 모드로 파일 열기
    for index in range(len(lines)-1, -1, -1):  # 역순으로 읽기
        f.write(lines[index])  # 저장
    f.close()  # 파일 닫기


if __name__ == '__main__':
    srcFile = 'abc.txt'
    dstFile = 'def.txt'
    view(srcFile)
    save(srcFile, dstFile)
    view(dstFile)

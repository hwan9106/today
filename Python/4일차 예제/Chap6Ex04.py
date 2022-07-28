"""
간단한 메모장 만들기
원하는 메모를 파일에 저장하고 추가 및 조회가 가능한 간단한 메모장을 만들어 보자.

필요한 기능은? 메모 추가하기, 메모 조회하기
입력 받는 값은? 메모 내용, 프로그램 실행 옵션
출력하는 값은? memo.txt
가장 먼저 해야 할 일은 메모를 추가하는 것이다. 다음 명령을 실행했을 때 메모를 추가할 수 있도록 만들어 보자.

# 메모 추가 기능
python memo.py -a "Life is too short"

# 메모 보기 기능
python memo.py -v

memo.py는 우리가 작성할 파이썬 프로그램 이름이다.
–a는 이 프로그램의 실행 옵션이고 "Life is too short"는 추가할 메모 내용이 된다.
"""


import sys

if len(sys.argv) == 2 or len(sys.argv) == 3:
    if sys.argv[1] == "-a":
        if len(sys.argv) == 3:
            content = sys.argv[2]
            f = open("memo.txt", "a")
            f.write(content)
            f.write("\n")
            f.close()
        else:
            print("잘못된 사용방법입니다.")
            print("{} -a \"추가내용\" ".format(__file__))
    elif sys.argv[1] == "-v":
        f = open("memo.txt", "r")
        print(f.read())
        f.close()
else:
    print("잘못된 사용방법입니다.")
    print("{} -a \"추가내용\" 또는".format(__file__))
    print("{} -v ".format(__file__))
    print()
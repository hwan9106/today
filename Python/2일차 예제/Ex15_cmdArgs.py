import sys  # 모듈 사용

print(type(sys.argv), ":", sys.argv)

args = sys.argv[1:]  # 명령행인수를 받는다.
for i in args:
    print(i)

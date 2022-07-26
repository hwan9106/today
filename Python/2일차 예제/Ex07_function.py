def add_and_mul(a, b):
    return a + b, a * b


result = add_and_mul(3, 4)
print(type(result), result)


def add_and_mul(a, b, c):
    return [a + b + c, a * b * c]


result = add_and_mul(3, 4, 5)
print(type(result), result)


def say_myself(name='무명', old=18, man=True):
    print("나의 이름은 {0} 입니다.".format(name))
    print("나이는 {0}살입니다.".format(old))
    if man:
        print("남자입니다.")
    else:
        print("여자입니다.")
    print()


say_myself('한사람', 34)
say_myself('두사람', 34, False)
say_myself('세사람')
say_myself(man=False, name="네사람", old=22)
say_myself(22, "네사람")

a = 1


def vartest(a):
    a = a + 1  # 함수안에서 선언함 변수는 함수내에서만 유효하다.
    return a


b = vartest(a)
print(a)  # 1
print(b)  # 2

a = 5


def vartest():
    global a  # 전역변수를 사용하겠다.
    a = a + 1


vartest()
print(a)

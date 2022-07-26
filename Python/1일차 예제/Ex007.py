# 따라해 보기

# 뭔가를 보여보자~~~~
print('hello world')
print("hello world")

# 그럼 한글은?
print("안녕 파이선")
print('안녕 파이선')

# 따옴표까지 출력하고 싶다면?
print("'안녕 파이선'")
print('"안녕 파이선"')

print('\'안녕 파이선\'')
print("\"안녕 파이선\"")

# 입력을 받을 수 없을까?
name = input("이름?")
print(name + "씨 방가방가")

age = input("나이?")
print(age + "살이네 행님이라 불러")

# 타입이 다르면? 타입을 알아야 겠구나.......
# print(name + "씨 내년에는 " + age+1 + "살이네...")
"""
Traceback (most recent call last):
  File "C:/Users/DJA/PycharmProjects/DataType/Day01/Ex01.py", line 24, in <module>
    print(name + "씨 내년에는 " + age+1 + "살이네...")
TypeError: must be str, not int
"""
# print(name + "씨 내년에는 " + int(age)+1 + "살이네...")
'''
Traceback (most recent call last):
  File "C:/Users/DJA/PycharmProjects/DataType/Day01/Ex01.py", line 31, in <module>
    print(name + "씨 내년에는 " + int(age)+1 + "살이네...")
TypeError: must be str, not int
'''
# print(name + "씨 내년에는 " + (int(age)+1) + "살이네...")
'''
Traceback (most recent call last):
22살이네 행님이라 불러
  File "C:/Users/DJA/PycharmProjects/DataType/Day01/Ex01.py", line 38, in <module>
    print(name + "씨 내년에는 " + (int(age)+1) + "살이네...")
TypeError: must be str, not int
'''
print(name + "씨 내년에는 " + str(int(age) + 1) + "살이네...")

# 그렇다면 입력을 문자가 아닌 숫자로 받으려면
age = int(input("나이?"))
# print(age + "살이네 행님이라 불러")
print(str(age) + "살이네 행님이라 불러")
# 귀찮다 그냥 출력이 않될까?
print(age, "살이네 행님이라 불러")
# 어! 떨어져서 출력이되네 붙여서는 출력이 되지 않을까?
print(age, "살이네 행님이라 불러", sep="")

# 이렇게 출력하면 어떻게 될까?
# 1번에 두줄로
print(age, "살이네~~~ \n행님이라 불러", sep="")

# 두줄로 출력
print(age, "살이네~~~ ", sep="")
print("행님이라 불러")

# 1줄로 붙일 수 없을까?
print(age, "살이네~~~ ", sep="", end="")
print("행님이라 불러")

# 안으로 들여쓰고 싶다면
print("    ", age, "살이네~~~ ", sep="", end="")
print("행님이라 불러")

print("\t", age, "살이네~~~ ", sep="", end="")
print("행님이라 불러")

# 내년 나이를 출력하려면
print("내년에 ", age + 1, "살이네 행님이라 불러", sep="")

# print("내년에 " +  age+1 + "살이네 행님이라 불러")
'''
Traceback (most recent call last):
  File "C:/Users/DJA/PycharmProjects/DataType/Day01/Ex01.py", line 79, in <module>
    print("내년에 " +  age+1 + "살이네 행님이라 불러")
TypeError: must be str, not int
'''
print("내년에 " + str(age + 1) + "살이네 행님이라 불러")
# 문자열 끼리는 더해지는구나....
print("내년에 " + str(age) + str(1) + "살이네 행님이라 불러")

# 이렇게 해서 무엇을 할 수 있을까?
# 연령대를 알아볼까?
print(name + "씨는 " + str(age / 10 * 10) + "대입니다.")
# 헐~~~~ 그럼 어떻게?
print(name + "씨는 " + str(age // 10 * 10) + "대입니다.")

# ?대 초반 후반을 출력해 볼까?
# 나머지?
print(age % 10)

print(name + "씨는 " + str(age // 10 * 10) + "대 ", end="")
print(age % 10)

print(name + "씨는 " + str(age // 10 * 10) + "대 ", end="")
print("초반" if age % 10 < 6 else "후반", "입니다.", sep="")

"""
결론?
아 ~~~~ 배워야 할게 많구나......
출력 방법
입력 방법
자료형
연산자
그리고 문제를 푸는 방법론?....
"""
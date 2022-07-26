"""
주어진 자연수가 홀수인지 짝수인지 판별해 주는 함수(is_odd)를 작성해 보자.
"""


def is_odd(number):
    return number % 2 == 1


for n in range(10,21):
    print("{0}은 '{1}'입니다.".format(n, "홀수" if is_odd(n) else "짝수"))
    print("{0}은 '{1}'입니다.".format(n, is_odd(n) and "홀수" or "짝수"))


"""
입력으로 들어오는 모든 수의 평균 값을 계산해 주는 함수를 작성해 보자. 
(단 입력으로 들어오는 수의 개수는 정해져 있지 않다.)
※ 평균 값을 구할 때 len 함수를 사용해 보자.
"""


def avg(*argv):
    return sum(argv)/len(argv)


print(avg(1,2,3,4,5,6,7,8,9,10))
print(avg(1,2,3,4,5,6,7,8,9,10,11,12))


"""
다음은 두 개의 숫자를 입력받아 더하여 돌려주는 프로그램이다.
input1 = input("첫번째 숫자를 입력하세요:")
input2 = input("두번째 숫자를 입력하세요:")
total = input1 + input2
print("두 수의 합은 %s 입니다" % total)
이 프로그램을 수행해 보자.
첫번째 숫자를 입력하세요:3
두번째 숫자를 입력하세요:6
두 수의 합은 36 입니다
3과 6을 입력했을 때 9가 아닌 36이라는 결괏값을 돌려주었다. 이 프로그램의 오류를 수정해 보자.
※ int 함수를 사용해 보자.
"""


def input_sum():
    input1 = int(input("첫번째 숫자를 입력하세요:"))
    input2 = int(input("두번째 숫자를 입력하세요:"))
    return input1 + input2


# print(input_sum())


"""
다음 중 출력 결과가 다른 것 한 개를 골라 보자.

print("you" "need" "python")
print("you"+"need"+"python")
print("you", "need", "python")
print("".join(["you", "need", "python"]))
"""

print("you"
      "need"
      "python")
print("you"+"need"+"python")
print("you", "need", "python", sep="")
print("".join(["you", "need", "python"]))


"""
다음은 "test.txt"라는 파일에 "Life is too short" 문자열을 저장한 후 다시 그 파일을 읽어서 출력하는 프로그램이다.

f1 = open("test.txt", 'w')
f1.write("Life is too short")

f2 = open("test.txt", 'r')
print(f2.read())
이 프로그램은 우리가 예상한 "Life is too short"라는 문장을 출력하지 않는다. 
우리가 예상한 값을 출력할 수 있도록 프로그램을 수정해 보자.
"""
"""
f1 = open("test.txt", 'w')
f1.write("Life is too short\n")
f1.close()  # 닫기를 수행해야 한다.
f2 = open("test.txt", 'r')
print(f2.read())
f2.close()
"""

"""
사용자의 입력을 파일(test.txt)에 저장하는 프로그램을 작성해 보자. 
(단 프로그램을 다시 실행하더라도 기존에 작성한 내용을 유지하고 새로 입력한 내용을 추가해야 한다.)
"""
f1 = open("test.txt", 'a')
f1.write(input('저장할 내용을 입력하시오'))
f1.write("\n")
f1.close()  # 닫기를 수행해야 한다.
f1 = open("test.txt", 'r')
print("읽은내용")
print("*" * 70)
print(f1.read())
print("*" * 70)
f1.close()


"""

"""
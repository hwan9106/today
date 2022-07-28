"""
입력을 정수 n으로 받았을 때, n 이하까지의 피보나치 수열을 출력하는 함수를 작성해 보자.
"""


def fibo1(n):
    first = 0
    second = 1
    while first <= n:
        print(first, end=" ")
        second = first + second
        first = second - first
    print()


fibo1(10)
fibo1(100)


# 위의 함수를 재귀 호출을 이용하여 새롭게 작성하시오

def fibo2(n):
    return n if (n == 0 or n == 1) else (fibo2(n - 2) + fibo2(n - 1))


for i in range(30):
    print(fibo2(i), end=" ")
print()

"""
한 줄 구구단
사용자로부터 2~9의 숫자 중 하나를 입력받아 해당 숫자의 구구단을 한 줄로 출력하는 프로그램을 작성하시오.

실행 예)
구구단을 출력할 숫자를 입력하세요(2~9): 2
2 4 6 8 10 12 14 16 18
"""


def gugu(n):
    return list(i * n for i in range(1, 10))


n = 2
while True:
    n = int(input("구구단을 출력할 숫자를 입력하세요(2~9, 이외의 숫자는 종료):"))
    if n < 2 or n > 9:
        break
    print(gugu(n))
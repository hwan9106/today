# While 반복문
"""
조건이 참인동안 반복실행한다.
while<조건문>:
    <수행할 문장1>
    <수행할 문장2>
    <수행할 문장3>
"""

#숫자를 계속입력받아 0이합계와 평균을 출력하는 프로그램을 만드시오.

sum = count = 0
num = 1

while num:  # 0은 거짓 그 이외의 수는 참
    num = int(input('양의 정수 입력(0을 입력 종료)?'))
    sum += num
    count += 1
    print('합계 : {0:6}'.format(sum))
    print('평균 : {0:6.2}'.format(sum/(count)))
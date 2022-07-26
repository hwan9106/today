# 출력에 대하여 알아보자
# 형식에 맞추어 출력하기

print('12'.zfill(5))
print('-12'.zfill(5))
print('3.14'.zfill(7))
print('-3.14'.zfill(7))
print('3.14159265359'.zfill(5))
print('-3.14159265359'.zfill(5))
print('-' * 40)
# 길이와 정렬
"""
{[값인덱스]:[[채움문자]정렬][부호][#][0][,][폭][.정밀도][값의타입]}
s : 문자열,  d  정수, f  부동소수, o  8진수,x  16진수,  %%  %문자 쓸수 있게 해줌
"""
print('Python is [{:15}]'.format('good'))
print('Python is [{:<15}]'.format('good'))
print('Python is [{:>15}]'.format('good'))
print('Python is [{:^15}]'.format('good'))
print('-' * 40)

for x in range(1, 4):
    print('[{0:2d}] [{1:3d}] [{2:4d}]'.format(x, x * x, x * x * x))
print()

for x in range(1, 4):
    print('[{0:^2d}] [{1:^3d}] [{2:^4d}]'.format(x, x * x, x * x * x))
print()

for x in range(1, 4):
    print('[{0:<2d}] [{1:<3d}] [{2:<4d}]'.format(x, x * x, x * x * x))
print()

for x in range(1, 4):
    print('[{0:02d}] [{1:03d}] [{2:04d}]'.format(x, x * x, x * x * x))
print()

print('[{0:05d}] [{1:05d}] [{2:05d}]'.format(1, -2, 3))  # 음수만 부호
print('[{0:+05d}] [{1:+05d}] [{2:+05d}]'.format(1, -2, 3))  # 부호
print('[{0:<5d}] [{1:<5d}] [{2:<5d}]'.format(1, -2, 3))  # 정렬
print('-' * 40)

# print('[{0:$5d}] [{1:$5d}] [{2:$5d}]'.format(1,-2,3))     # 정렬
print('[{0:05d}] [{1:05d}] [{2:05d}]'.format(1, -2, 3))  # 채움문자
print('[{0:$<5d}] [{1:$<5d}] [{2:$<5d}]'.format(1, -2, 3))  # 채움문자
print('[{0:$>5d}] [{1:$>5d}] [{2:$>5d}]'.format(1, -2, 3))  # 채움문자
print('[{0:$^5d}] [{1:$^5d}] [{2:$^5d}]'.format(1, -2, 3))  # 채움문자
print('[{0:>5,}] [{1:>5,}] [{2:>5,}]'.format(11544435, -2544254, 35454343))  # 채움문자
print('[{0:>5e}] [{1:>5e}] [{2:>5e}]'.format(11544435, -2544254, 35454343))  # 채움문자
print('-' * 40)

print('[{0:5b}] [{1:5b}] [{2:5b}]'.format(11, -22, 33))  # 2진수
print('[{0:5o}] [{1:5o}] [{2:5o}]'.format(11, -22, 33))  # 8진수
print('[{0:5x}] [{1:5x}] [{2:5x}]'.format(11, -22, 33))  # 16진수 소문자
print('[{0:5X}] [{1:5X}] [{2:5X}]'.format(11, -22, 33))  # 16진수 대문자
print('-' * 40)

import math

print('원주율 [{0:.3f}]'.format(math.pi))
print('원주율 [{0:.7f}]'.format(math.pi))
print('원주율 [{0:10.3f}]'.format(math.pi))
print('원주율 [{0:20.7f}]'.format(math.pi))
print('원주율 [{0:0.5f}]'.format(math.pi))
print('-' * 40)

print('{0}씨는 상위 {1}%안에 있는 사람입니다.'.format('한사람', 10))
print('{{0}}씨는 상위 {{1}}%안에 있는 사람입니다.'.format('한사람', 10))
print('{{{0}}}씨는 상위 {{{1}}}%안에 있는 사람입니다.'.format('한사람', 10))
print('%s씨는 상위 %d%%안에 있는 사람입니다.' % ('한사람', 10))
print('-' * 40)

"""
지금까지 고급 포맷팅 방식을 조금 복잡하게 설명을 했는데, 정리를 하자면 아래와 같이 포맷팅을 하면 됩니다.
이때, 순서가 옳바르지 않으면 에러를 일으킵니다.
format_spec ::= [[fill]align][sign][#][0][width][,][.precision][type]
fill ::= <a character other than '}'>
align ::= "<" | ">" | "=" | "^:
sign ::= "+" | "-" | " "
width ::= integer
precision ::= integer
type ::= "b" | "c" | "d" | "e" | "E" | "f" | "F" | "g" | "G" | "n" | "o" | "s" | "x" | "X" | "%"

마지막으로 format() 내장 함수가 있는데, 이것은 문자나 숫자를 포맷팅 형식으로 바꾸어 주는 함수입니다.
>>> format(1234567890, '+030,')
'+0,000,000,000,001,234,567,890'
"""
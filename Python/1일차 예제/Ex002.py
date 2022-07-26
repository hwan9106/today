# 출력에 대하여 알아보자

# 형식에 맞추어 출력하기
# 이전 방식
from functools import partial

print('나의 이름은 %s입니다' % ('한사람'))
print('나의 이름은 "%s"입니다. 나이는 %d세이고 성별은 %s입니다.' % ('한사람', 33, '남성'))
print('나이는 %d세이고 성별은 %s입니다. 나의 이름은 "%s"입니다. ' % (33, '한사람', '남성'))
print('-' * 40)

# 파이썬(Python) 3 포맷팅 방식
print('나의 이름은 {}입니다'.format('한사람'))
print('나의 이름은 "{0}"입니다. 나이는 {1}세이고 성별은 {2}입니다.'.format('한사람', 33, '남성'))
print('나이는 {1}세이고 성별은 {2}입니다. 나의 이름은 "{0}"입니다. '.format('한사람', 33, '남성'))
print('나이는 {age}세이고 성별은 {gender}입니다. 나의 이름은 "{name}"입니다. '.format(name='한사람', age=33, gender='남성'))
print('만세삼창 :  {0}!!! {0}!!! {0}!!! '.format('만세'))
print('삼삼칠 박수 :  {0}!!! {1}!!! {2}!!! '.format('짝' * 3, '짝' * 3, '짝' * 7))
print('-' * 40)

# 함수의 인자처럼 키워드를 사용해서 나타낼 수 있습니다
print('{item} is {color}'.format(item='Apple', color='red'))
dic = {'item': 'Apple', 'color': 'red'}
print('{0[item]} is {0[color]}'.format(dic))
# 위 방식은 { } 기호 내에 키워드를 집어넣은 것이고,
# 아래 방식은 사전 객체를 0에 포맷팅 시켜서 사전 내에 있는 item 키와 color 키의 값들을 매칭 시킨 방식입니다.


# 포맷팅을 쓰다 보면 S.format(*args, **kwargs) 라고 나오는데, 함수의 인자와 비슷한 형태를 취하고 있습니다.
t = ('very', 'programming')
print('Python is {0} good {1} language'.format(*t))
# 함수에 인자를 * 기호를 주면서 넣게 되면 리스트나 튜플로 인식하 듯이 포맷팅에도 위와 같이 사용할 수 있습니다.
# 따라서, ** 기호를 사용하면 사전 객체를 사용할 수 있습니다.

# 딕셔너리
table = {'Sjoerd': 4127, 'Jack': 4098, 'Dcab': 7678}
print(type(table))
for key, value in table.items():
    print('{0:10} ==> {1:10d}'.format(key, value))

print('Jack: {0[Jack]:d}; Sjoerd: {0[Sjoerd]:d}; Dcab: {0[Dcab]:d}'.format(table))
print('Jack: {Jack:d}; Sjoerd: {Sjoerd:d}; Dcab: {Dcab:d}'.format(**table))
print('-' * 40)


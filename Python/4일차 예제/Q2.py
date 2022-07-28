"""
딕셔너리 값 추출하기
다음은 딕셔너리의 a에서 'C'라는 key에 해당하는 value를 출력하는 프로그램이다.

>>> a = {'A':90, 'B':80}
>>> a['C']
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
KeyError: 'C'
a 딕셔너리에는 'C'라는 key가 없으므로 위와 같은 오류가 발생한다. 'C'에 해당하는 key 값이 없을 경우 오류 대신 70을 얻을 수 있도록 수정하시오.
"""

a = {'A':90, 'B':80}
print(a['A'])
print(a['B'])
"""
try:
    print(a['C'])
except KeyError:
    print(70)
"""
print(a.get('C', 70))
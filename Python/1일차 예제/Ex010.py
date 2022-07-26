# 튜플
"""
튜플(tuple)은 몇 가지 점을 제외하곤 리스트와 거의 비슷하며 리스트와 다른 점은 다음과 같다.
리스트는 [ ]으로 둘러싸지만 튜플은 ( )으로 둘러싼다.
리스트는 그 값의 생성, 삭제, 수정이 가능하지만 튜플은 그 값을 바꿀 수 없다.
"""

a = [1,2,3]
b = (1,2,3)

print(type(a) , ":", a)
print(type(b) , ":", b)

print(a[1])
print(b[1])

a[1] = 9
# b[1] = 9 # 에러다~~~~

print(type(a) , ":", a)
print(type(b) , ":", b)

c = 1,2,3,4,5,6
print(type(c) , ":", c)

a = 10;
b = 20;
print(a, b)

# 교환
a, b = b, a
print(a, b)

c = 30
print(a, b, c)
c,b,a = a,b,c
print(a, b, c)
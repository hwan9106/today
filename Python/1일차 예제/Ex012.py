# 집합 자료형 (set) : 중복을 허용하지 않는다.
s1 = set([1, 2, 3, 3, 4, 5, 6, 7])
print(s1)

s2 = set('Hello')
print(s2)
# set 자료형에 저장된 값을  인덱싱으로 접근하려면 다음과 같이 리스트나 튜플로 변환한후 해야 한다.

# print(s2[0])  # 에러다
l1 = list(s2)

print(l1[0])

# 교집합, 합집합, 차집합 구하기
s1 = set([1, 2, 3, 4, 5, 6])
s2 = set([4, 5, 6, 7, 8, 9])

print(s1)
print(s2)
print(s1 & s2)  # 교집합
print(s1.intersection(s2))  # 교집합

print(s1 | s2)  # 합집합
print(s1.union(s2))  # 교집합

print(s1 - s2)  # 차집합
print(s1.difference(s2))  # 차집합
print(s2 - s1)  # 차집합
print(s2.difference(s1))  # 차집합
# 추가
s1.add(7)
print(s1)
s1.update([8,9,10])
print(s1)

# 제거
s1.remove(8)
s1.remove(9)
s1.remove(10)
print(s1)



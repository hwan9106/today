# 리스트 자료형
a = [1, 2, 3, ['a', 'b', 'c']]

print(a[0])
print(a[3])
print(a[3][0])

a = [1, 2, ['a', 'b', ['Life', 'is']]]
print(a[-1][-1][0])
print(a[2][2][0])

a = [1, 2, 3]
b = [4, 5, 6]
print(a + b)
c = a + b
print(c)
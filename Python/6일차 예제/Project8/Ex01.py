import numpy as np

# 배열의 생성
a = np.array([1, 2, 3, 4, 5, 6])
print(a)
print()

a = np.array([[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]])
print(a)
print()
print(a[0])
print()
print(a[1])
print()
print(a[2])
print()
print(a[2][2])

a = np.zeros(2)
print(a)
print()

a = np.ones(2)
print(a)
print()

a = np.empty(5)
print(a)
print()


a = np.arange(4)
print(a)
print()

for i in a:
    print(i)

a = np.arange(2, 9, 2)
print(a)
print()

a = np.linspace(0, 10, num=20)
print(a)
print()


a = np.ones(10, dtype=np.int64)
print(a)
print()



import numpy as np

# 인덱싱 및 슬라이싱
ar = np.array([1,2,3,4,5,6])
print(ar)

print(ar[1])

print(ar[0:3])
print(ar[1:3])
print(ar[:3])
print(ar[3:])
print()
print(ar[-3:])
print(ar[-1:])

a = np.array([[1 , 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]])
print(a[a < 5])
print(a[a > 5])

five_up = (a >= 5)  # 조건
print(a[five_up])
print(a[a%2==0])
even = a[a%2==0]   # 결과
print(even)

print(a[(a>2) & (a<10)])  # & : AND 연산

print(a[(a>5) | (a==3)])

exp = (a>5) | (a==3)
print(exp)

# np.nonzero()예를 들어 5보다 작은 요소의 인덱스를 인쇄하는 데 사용할 수 있습니다 .
b = np.nonzero(a < 5)
print(a)
print(b)
# 첫 번째 배열은 이러한 값이 있는 행 인덱스를 나타내고 두 번째 배열은 값이 있는 열 인덱스를 나타냅니다.
print(np.nonzero(a>6))


# 요소가 있는 좌표 목록을 생성하려면 배열을 압축하고 좌표 목록을 반복하고 인쇄할 수 있습니다.
list_of_coordinates= list(zip(b[0], b[1]))  # b배열의 0행은 행값, 1행은 열값을 결합해 좌표를 생성
for coord in list_of_coordinates:
    print(coord)  # (0,0) (0,1) ....

print(a[b])  # b배열에 저장된 값을 읽어온다.

c = np.nonzero(a == 15)
print(c)


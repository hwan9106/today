import numpy as np


def array_print(ar):
    print(ar)
    print(ar.ndim)
    print(ar.size)
    print(ar.shape)
    print("*" * 50)


# 기존 데이터에서 배열을 만드는 방법
ar = np.array([1, 2, 3, 4, 5, 6, 7, 8, 9, 10])
array_print(ar)

ar2 = ar[:]  # 전체 복사
array_print(ar2)
ar2[0] = 99
array_print(ar)
array_print(ar2)

ar3 = ar[3:6]  # 일부분 복사
array_print(ar3)
print("*" * 50)

ar3 = np.array([[1, 1], [2, 2]])
ar4 = np.array([[3, 3], [4, 4]])
print(ar3)
print(ar4)
print("*" * 50)
print(np.vstack((ar3, ar4)))
print("*" * 50)
print(np.hstack((ar3, ar4)))
print("*" * 50)

ar5 = np.arange(1, 25).reshape(2, 12)  # 배열을 만듬과 동시에 행열의 차원을 변경
print(ar5)
print("*" * 50)

# 3개의 동일한 모양의 배열로 분할
print(np.hsplit(ar5, 3))

# 세 번째와 네 번째 열 이후에 배열을 분할
# 좌표를 기준으로 자르고 남는것을 앞뒤 배열로 만든다.
print(np.hsplit(ar5, (3, 4)))
print("*" * 50)

print(np.hsplit(ar5, (3, 3)))
print("*" * 50)

print(np.hsplit(ar5, (3, 5)))
print("*" * 50)


# 얕은 복사
print(ar5)
print("*" * 50)
ar6 = ar5[0, :]  # 0행만 복사
print(ar6)
print("*" * 50)

ar6[4] = 888;
print(ar5)
print("*" * 50)
print(ar6)
print("*" * 50)  # 두게 배열이 동시에 변경 ===> 얕은복사 : 참조 주소가 복사 된거다.

##############################################################################
# 깊은 복사 : 내용이 복사된다.
##############################################################################

ar7 = ar5.copy();
print("-" * 50)
print(ar5)
print("-" * 50)
print(ar7)

ar7[0, 6] = 444
print("-" * 50)
print(ar5)
print("-" * 50)
print(ar7)  # ar7만 변경되었다. 깊은 복사이다.




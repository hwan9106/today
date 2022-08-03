import numpy as np

# 1D 배열을 2D 배열로 변환하는 방법(배열에 새 축을 추가하는 방법)
# np.newaxis및 를 사용 np.expand_dims하여 기존 배열의 차원을 늘릴 수 있습니다.

ar1 = np.arange(1, 7)
print(ar1)
print(ar1.ndim)
print(ar1.size)
print(ar1.shape)
print("-" * 50)

# np.newaxis다음을 사용 하여 새 축을 추가 할 수 있습니다 .
ar2 = ar1[np.newaxis, :]  # 행이 추가되었다.
print(ar2)
print(ar2.ndim)
print(ar2.size)
print(ar2.shape)
print("-" * 50)

ar3 = ar1[:, np.newaxis]  # 열이 추가 되었다.
print(ar3)
print(ar3.ndim)
print(ar3.size)
print(ar3.shape)
print("-" * 50)

ar4 = np.array([1, 2, 3, 4, 5, 6])
print(ar4)
print(ar4.ndim)
print(ar4.size)
print(ar4.shape)
print("-" * 50)


ar5 = np.expand_dims(ar4, axis=1)
print(ar5)
print(ar5.ndim)
print(ar5.size)
print(ar5.shape)
print("-" * 50)

ar6 = np.expand_dims(ar4, axis=0)
print(ar6)
print(ar6.ndim)
print(ar6.size)
print(ar6.shape)
print("-" * 50)

import numpy as np

# 기본 배열 연산 더하기, 빼기, 곱하기, 나누기 등을 다룹니다.
ar1 = np.array([1, 2])
ar2 = np.ones(2, dtype=int)

print("ar1 =", ar1)
print("ar2 =", ar2)
print("ar1 + ar2 =", ar1 + ar2)
print("ar1 - ar2 =", ar1 - ar2)
print("ar1 * ar2 =", ar1 * ar2)  # 행열의 곱이 아니라 각각의 요소의 연산
print("ar1 / ar2 =", ar1 / ar2)

ar3 = np.array([[1, 2], [3, 4]])
ar4 = np.array([[1, 2], [3, 4]])
print("ar3 * ar4 =", ar3 * ar4)  # 행열의 곱이 아니라 각각의 요소의 연산

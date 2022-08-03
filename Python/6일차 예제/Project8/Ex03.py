import numpy as np

# 배열을 재구성할 수 있습니까?
ar = np.arange(6)
print(ar)  # [0 1 2 3 4 5]
print()

ar2 = ar.reshape(2, 3)
print(ar)
print(ar2)
print()

# 차원 변경은 되지 않는다. 결과는 원본의 차원과 같아야 한다.
ar3 = np.reshape(ar2, newshape=(1, 6), order='C')  # 2차원 배열
print(ar)
print(ar2)
print(ar3)
print()

ar3 = np.reshape(ar2, newshape=(3, 2))  # 2차원 배열
print(ar)
print(ar2)
print(ar3)
print()



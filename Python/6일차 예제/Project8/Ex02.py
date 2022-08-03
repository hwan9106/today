import numpy as np

# 요소 추가, 제거 및 정렬
ar = np.array([3, 2, 5, 4, 1, 6, 9, 8, 7])
print(ar)
# 정렬
np.sort(ar)  # 자신이 변하지 않는다.
print(ar)

ar2 = np.sort(ar)  # 대입해서 처리
print(ar2)

# 배열명.sort()
ar.sort()  # 자신이 변한다.
print(ar)

"""
배열의 정렬된 복사본을 반환하는 정렬 외에도 다음을 사용할 수 있습니다.
argsort, 지정된 축을 따른 간접 정렬,
lexsort, 여러 키에 대한 간접적인 안정적인 정렬입니다.
searchsorted, 정렬된 배열에서 요소를 찾습니다.
partition, 이는 부분 정렬입니다.
"""

ar3 = np.array([1, 2, 3, 4])
ar4 = np.array([5, 6, 7, 8])
print(ar3)
print(ar4)
print()

# 배열의 연결 : np.concatenate()
ar5 = np.concatenate((ar3, ar4))
print(ar3)
print(ar4)
print(ar5)

ar6 = np.array([[1, 2], [3, 4]])
ar7 = np.array([[5, 6]])
ar8 = np.concatenate((ar6, ar7), axis=0)
print(ar6)
print(ar7)
print(ar8)

#  배열의 모양과 크기를 어떻게 알 수 있습니까?
print(ar7.ndim,'차원')
print(ar8.ndim,'차원')

ar9 = np.array([
                [[1,2,3],[4,5,6]],
                [[1,2,3],[4,5,6]],
                [[1,2,3],[4,5,6]],
                [[1,2,3],[4,5,6]],
                ])
print(ar9.ndim,'차원')
print(ar9.size,'개')
print(ar9.shape)  # 배열의 크기를 튜플로 알려준다. (4, 2, 3)
print(ar8.shape)  # 배열의 크기를 튜플로 알려준다. (3, 2)

# 배열을 재구성할 수 있습니까?


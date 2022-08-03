import numpy as np

ar = np.array([[1, 2], [3, 4], [5, 6]])  # 3행 2열
print(ar.ndim)  # 차원
print(ar.shape) # 행열의 크기를 튜플로
print(ar)
print("-" * 50)

# 행열에서 필요한 부분만 잘라내보자
print(ar[0,1])
print(ar[1:3])
print(ar[1:3, 0])
print(ar[1:3, 1])
print(ar[1:3, 1].sum())
print("-" * 50)

# NumPy는 작업에 브로드캐스트 규칙을 사용합니다.
# [1,1] => [[1,1],[1,1],[1,1]] 로 바뀌어 연산이 된다.
ones_row = np.array([[1, 1]])
print(ar)
print(ones_row)
print( ar + ones_row);

# 난수로 배열 생성
rng = np.random.default_rng()
print(rng.random(3))
ar2 = rng.random((3,3))  # 다차원 배열은 튜플로 지정해서 만든다.
print(ar2)
# 정수 난수로 배열 생성
ar3 = rng.integers(5, size=(2, 4))  # 5전까지 2 행 4열의 정수 난수 배열 생성
print(ar3)

ar4 = np.array([11, 11, 12, 13, 14, 15, 16, 17, 12, 13, 11, 14, 18, 19, 20])
print(ar4)
unique_values = np.unique(ar4)  # 중복을 제거한 배열
print(unique_values)
# 앞에는 중복이 제거된 배열, 뒤에는 첫번째 나오는 인덱스 값
unique_values, indices_list = np.unique(ar4, return_index=True)
print(indices_list)

# 앞에는 중복이 제거된 배열, 뒤에는 나오는 개수
unique_values, count_list = np.unique(ar4, return_counts=True)
print(count_list)

# 앞에는 중복이 제거된 배열, 뒤에는 ?
unique_values, inverse_list = np.unique(ar4, return_inverse=True)
print(inverse_list)

# 다 차원 배열에서 중복을 제거하면
ar5 = np.array([[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12], [1, 2, 3, 4]])
unique_values = np.unique(ar5)  # 중복을 제거한 배열
print(unique_values) # 1차원으로 결과가 나온다.

unique_values = np.unique(ar5, axis=0)  # 중복을 제거한 배열
print(unique_values) # 2차원으로 결과가 나온다.(열에 대하여 중복 제거)

unique_values = np.unique(ar5, axis=1)  # 중복을 제거한 배열
print(unique_values) # 2차원으로 결과가 나온다.(행에 대하여 중복 제거)

unique_rows, indices, occurrence_count = np.unique(ar5, axis=0, return_counts=True, return_index=True)
print(unique_rows)
print(indices)
print(occurrence_count)
print("*" * 60)
# 행렬의 크기 변경 및 전치 행렬 구하기
ar6 = np.array([[1, 2],[3, 4], [5, 6]])
print(ar6)
print("*" * 60)
# 크기변경 : reshape()
ar7 = ar6.reshape(2,3)
print(ar7)
print("*" * 60)
ar8 = ar6.reshape(1, 6)
print(ar8)
print("*" * 60)

ar8 = ar6.reshape(6)
print(ar8)
print("*" * 60)

# ar8 = ar6.reshape(9)  # 에러다 개수가 맞지 않는다.
# print(ar8)
# print("*" * 60)

# 전치 행렬 : 행과 열을 바꾼다.
ar9 = np.arange(6).reshape((2, 3))
print(ar9)
print("*" * 60)
ar9 = ar9.transpose()
print(ar9)
print("*" * 60)
ar9 = ar9.T
print(ar9)
print("*" * 60)

# 행열 뒤집기
ar10 = np.arange(8) + 1;
print(ar10)
print("*" * 60)
reversed_arr = np.flip(ar10)
print(reversed_arr)
print("*" * 60)

ar11 = np.array([[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]])
reversed_arr = np.flip(ar11)
print(ar11)
print(reversed_arr)
print("*" * 60)

ar11 = ar11.flatten()
print(ar11)
print("*" * 60)

#  ravel 사용할 때 새 배열에 대한 변경 사항은 상위 배열에 영향을 미칩니다.
ar11 = np.array([[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]])
ar12 = ar11.ravel()
print(ar11)
print(ar12)
print("*" * 60)
ar12[0] = 999
print(ar11)
print(ar12)
print("*" * 60)

help(max)  # 함수에 대한 설명이 나온다.


def myfn():
    pass


help(myfn)

def myfn():
    """
    내가 만든 함수입니다,
    아무 내용도 없어요
    :return:
    """
    pass


def myfn(a,b):
    """
    :param a:
    :param b:
    :return:
    """
    pass


help(myfn)


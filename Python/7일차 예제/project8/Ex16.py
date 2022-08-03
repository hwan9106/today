import matplotlib.pyplot as plt

# x 값은 기본적으로 [0, 1, 2, 3]이 되어서, 점 (0, 2), (1, 3), (2, 5), (3, 10)를 잇는 꺾은선 그래프가 나타납니다.
plt.plot([2, 3, 5, 10])
plt.show()

# (1,2), (2,3), (3,4), (4,10)를 잇는 꺾은선 그래프가 나타납니다.
plt.plot([1, 2, 3, 4], [2, 3, 5, 10])
plt.show()

data_dict = {'data_x': [1, 2, 3, 4, 5], 'data_y': [2, 3, 5, 10, 8]}
# 데이터를 dict자료형으로 사용하겠다.
plt.plot('data_x', 'data_y', data=data_dict)
plt.show()
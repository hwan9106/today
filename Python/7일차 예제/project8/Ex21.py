import matplotlib.pyplot as plt


"""
Matplotlib 축 범위 지정하기
xlim() - X축이 표시되는 범위를 지정하거나 반환합니다.
ylim() - Y축이 표시되는 범위를 지정하거나 반환합니다.
axis() - X, Y축이 표시되는 범위를 지정하거나 반환합니다.
"""
plt.plot([1, 2, 3, 4], [2, 3, 5, 10])
plt.xlabel('X-Axis')
plt.ylabel('Y-Axis')
# plt.xlim([0, 5])      # X축의 범위: [xmin, xmax]
# plt.ylim([0, 20])     # Y축의 범위: [ymin, ymax]
# 위의 두줄을 1줄로 쓰면
# plt.axis([0, 5, 0, 20])  # X, Y축의 범위: [xmin, xmax, ymin, ymax]

"""
axis() 함수는 아래와 같이 축에 관한 다양한 옵션을 제공합니다.
'on' | 'off' | 'equal' | 'scaled' | 'tight' | 'auto' | 'normal' | 'image' | 'square'
"""
# plt.axis('scaled')
# plt.axis('tight')
# plt.axis('equal')
# plt.axis('image')
plt.axis('square')

# 축의 범위값 얻기
x_range, y_range = plt.xlim(), plt.ylim()
print( x_range, y_range)
axis = plt.axis('scaled')
print(axis)

plt.show()


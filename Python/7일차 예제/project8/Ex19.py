import matplotlib.pyplot as plt

plt.plot([1, 2, 3, 4], [2, 3, 5, 10])
# labelpad : 여백
# loc : 라벨의 위치
# fontdict : 폰트의 속성을 지정
plt.xlabel('X-축', labelpad=15, loc='right',
           fontdict={'family': 'Malgun Gothic', 'color': 'b', 'weight': 'bold', 'size': 14})
plt.ylabel('Y-축', labelpad=20, loc='top',
           fontdict={'family': 'Malgun Gothic', 'color': 'deeppink', 'weight': 'normal', 'size': 'xx-large'})
plt.show()

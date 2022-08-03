import matplotlib.pyplot as plt

plt.plot([1, 2, 3, 4], [2, 3, 5, 10], label='Price ($)') # 우선 label을 지정해야 한다.
plt.plot([1, 2, 3, 4], [3, 5, 9, 7], label='Demand (#)')
plt.xlabel('X-Axis')
plt.ylabel('Y-Axis')
# 범례 지정
# plt.legend() # 범례를 보여라!!!
# plt.legend(loc=(0.0, 0.0))
# plt.legend(loc=(0.5, 0.5))
# plt.legend(loc=(1.0, 1.0))
# plt.legend(loc=(0.7, 0.1)) # 위치를 직접 지정
# plt.legend(loc='lower right') # 우측 하단에 나타내라
# plt.legend(loc='best') # 좋은 위치에 나타내라
# plt.legend(loc='best', ncol=2) # 1줄에 몇개씩
# plt.legend(loc='best', ncol=2, fontsize=13) # 폰트 크기
# 범례테두리(frameon=테두리 표시유무, shadow=그림자 표시유무)
plt.legend(loc='best', ncol=2, fontsize=13, frameon=True, shadow=True)

plt.show()
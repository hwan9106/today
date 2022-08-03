import matplotlib.pyplot as plt

plt.plot([1, 2, 3, 4], [1, 4, 9, 16], 'ro')  # 세번째 인수가 색상과 타입
plt.axis([0, 6, 0, 20])  # 축의 상하한 값지정 (x시작, x종료, y시작, y종료)
plt.show()
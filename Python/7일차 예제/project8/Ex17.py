import matplotlib.pyplot as plt

#  Matplotlib 축 레이블 설정하기
#  xlabel(), ylabel() 함수를 사용하면 그래프의 x, y 축에 대한 레이블을 표시할 수 있습니다.

# 한글이 깨질 경우 처리 방법 : https://operstu1.tistory.com/80

plt.plot([1, 2, 3, 4], [1, 4, 9, 16])
#plt.xlabel('X-Label')
#plt.ylabel('Y-Label')
plt.xlabel('월', labelpad=10)
plt.ylabel('판매량', labelpad=10)
plt.show()




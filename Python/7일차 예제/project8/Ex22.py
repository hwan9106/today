import matplotlib.pyplot as plt


"""
Matplotlib 선 종류 지정하기
Keyword: plt.plot(), 포맷 문자열, 선 종류, linestyle, Solid, Dashed, Dotted, Dash-dot
"""
#        x좌표, y좌표, 선종류, 색상, 범례문자열
# plt.plot([1, 2, 3], [4, 4, 4], '-', color='C0', label='Solid')
# plt.plot([1, 2, 3], [3, 3, 3], '--', color='C0', label='Dashed')
# plt.plot([1, 2, 3], [2, 2, 2], ':', color='C0', label='Dotted')
# plt.plot([1, 2, 3], [1, 1, 1], '-.', color='C0', label='Dash-dot')

# plt.plot([1, 2, 3], [4, 4, 4], linestyle='solid', color='C0', label='Solid')
# plt.plot([1, 2, 3], [3, 3, 3], linestyle='dashed', color='C0', label='Dashed')
# plt.plot([1, 2, 3], [2, 2, 2], linestyle='dotted', color='C0', label='Dotted')
# plt.plot([1, 2, 3], [1, 1, 1], linestyle='dashdot', color='C0', label='Dash-dot')

#  (0, (1, 1))은 ‘dotted’와 같고, (0, (5, 5))는 ‘dashed’와 같습니다.
# 또한 (0, (3, 5, 1, 5))는 ‘dashdotted’와 같습니다.
# plt.plot([1, 2, 3], [4, 4, 4], linestyle=(0, (1, 1)), color='C0', label='(0, (1, 1))')
# plt.plot([1, 2, 3], [3, 3, 3], linestyle=(0, (1, 5)), color='C0', label='(0, (1, 5))')
# plt.plot([1, 2, 3], [2, 2, 2], linestyle=(0, (5, 1)), color='C0', label='(0, (5, 1))')
# plt.plot([1, 2, 3], [1, 1, 1], linestyle=(0, (3, 5, 1, 5)), color='C0', label='(0, (3, 5, 1, 5))')

# linewidth=10,   : 선의 두께
# solid_capstyle='butt'  : 선 끝모양 지정
plt.plot([1, 2, 3], [4, 4, 4], linestyle='solid', linewidth=10,
      solid_capstyle='butt', color='C0', label='solid+butt')
plt.plot([1, 2, 3], [3, 3, 3], linestyle='solid', linewidth=10,
      solid_capstyle='round', color='C0', label='solid+round')

plt.plot([1, 2, 3], [2, 2, 2], linestyle='dashed', linewidth=10,
      dash_capstyle='butt', color='C1', label='dashed+butt')
plt.plot([1, 2, 3], [1, 1, 1], linestyle='dashed', linewidth=10,
      dash_capstyle='round', color='C1', label='dashed+round')

plt.xlabel('X-Axis')  # 축레이블 지정
plt.ylabel('Y-Axis')
plt.axis([0.8, 3.2, 0.5, 5.0])  # 축의 범위
plt.legend(loc='upper right', ncol=2) # 범례의 위치와 모양
plt.show()


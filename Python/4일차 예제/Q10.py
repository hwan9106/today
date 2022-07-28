"""
사칙연산 계산기
다음과 같이 동작하는 클래스 Calculator를 작성하시오.

>>> cal1 = Calculator([1,2,3,4,5])
>>> cal1.sum() # 합계
15
>>> cal1.avg() # 평균
3.0
>>> cal2 = Calculator([6,7,8,9,10])
>>> cal2.sum() # 합계
40
>>> cal2.avg() # 평균
8.0
"""


class Calculator:
    def __init__(self, num_list):
        self.list = num_list

    def sum(self):
        return sum(self.list)

    def avg(self):
        return self.sum() / len(self.list)


cal1 = Calculator([1, 2, 3, 4, 5])
print(cal1.sum())
print(cal1.avg())

cal2 = Calculator([6, 7, 8, 9, 10])
print(cal2.sum())
print(cal2.avg())

"""
10 미만의 자연수에서 3과 5의 배수를 구하면 3, 5, 6, 9이다. 이들의 총합은 23이다.
1000 미만의 자연수에서 3의 배수와 5의 배수의 총합을 구하라.
"""


def multi(limit):
    sum = 0
    for i in range(1, limit):
        if i % 3 == 0 or i % 5 == 0:
            # print(i, end=" ")
            sum += i
    return sum


print("1~10사이의 3과 5의 배수 합 : ", multi(10))
print("1~100사이의 3과 5의 배수 합 : ", multi(100))

"""
multi(10)
print()
multi(20)
print()
multi(100)
print()
"""



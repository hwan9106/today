print(dir([]))
print(dir(()))

d, m = divmod(10, 3)
print(d)
print(m)
print(divmod(10, 3))
print("*" * 30)

for name in ['body', 'foo', 'bar']:
    print(name)
print("*" * 30)

l = ['body', 'foo', 'bar']
for i in range(len(l)):
    print(i, ":", l[i])
print("*" * 30)

for i, name in enumerate(['body', 'foo', 'bar']):
    print(i, ":", name)
print("*" * 30)


# 필터의 사용법
def positive(x):
    return x > 0


# fileter(걸러줄 함수, 대상)
print(list(filter(positive, [1, 2, -3, 0, 7])))


def is_even(x):
    return x % 2 == 0


print(list(filter(is_even, [1, 2, 3, 4, 5, 6, 7, 8, 0])))

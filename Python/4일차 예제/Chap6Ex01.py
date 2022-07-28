# 정수 1개를 인수로 받아 구구단의 결과를 리스트로 리턴하는 함수를 만들어 보자
def gugu(n):
    # print(list(i * n for i in range(1, 10)))
    return list(i * n for i in range(1, 10))


# result = gugu(2)
# print(result)

for i in range(1, 10):
    print(gugu(i))


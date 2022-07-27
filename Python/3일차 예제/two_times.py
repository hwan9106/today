# two_times.py
# 리스트를 인수로 받아 각각의 값을 두배한 리스트를 리턴하는 함수
def two_times(numberList):
    result = []
    for number in numberList:
        result.append(number * 2)
    return result


result = two_times([1, 2, 3, 4])
print(result)


# 제곱을 해주는 함수
def pow(x):
    return x * x


# map함수
# map(함수, 대상) : 대상의 각각의 요소에 대하여 함수를 실행한 결과를 리턴해준다.
print(list(map(pow, [1, 2, 3, 4])))

# map함수에 함수인자로 람다식을 적용가능하다.
print(list(map(lambda x : x * x, [1, 2, 3, 4])))  # 제곱
print(list(map(lambda x : x ** 2, [1, 2, 3, 4])))  # 제곱
print(list(map(lambda x : x * 2, [1, 2, 3, 4])))  # 2배

print(chr(97), ":", ord('a'), ord('z')-ord('a'))



# 별도의 파일로 자주 사용하는 함수나 클랫를 만들어 놓는다.
def add(a, b):
    return a + b


def sub(a, b):
    return a - b


# 이 파일이 모듈로 포함될 경우에는 실행되지 말아라!!!
# 모듈 자체를 실행할 떄만 실행되어라!!!
if __name__ == "__main__":
    print(add(1, 4))
    print(sub(4, 2))

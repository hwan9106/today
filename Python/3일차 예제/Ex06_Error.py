try:
    4 / 0
except ZeroDivisionError as e:
    print("에러메세지 : {}".format(e))
finally:
    print("나는 항상 실행!!!!")
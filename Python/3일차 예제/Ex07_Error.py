try:
    a = [1, 2]
    print(a[3])
    # 4/0
    open("xxx.txt", "r")
except ZeroDivisionError as e:
    print("0으로 나눌 수 없습니다. : {}".format(e))
except IndexError as e:
    print("인덱싱 할 수 없습니다. : {}".format(e))
except:
    print("알 수 없는 에러입니다.")
finally:
    print("나는 항상 실행!!!")

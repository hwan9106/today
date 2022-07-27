# 사칙연산을 하는 클래스를 만들어 보자
class FourCal:
    def __init__(self):
        self.first = 0
        self.second = 0

    def __init__(self, first=0, second=0):
        self.first = first
        self.second = second

    def set_data(self, first, second):
        self.first = first
        self.second = second

    def add(self):
        return self.first + self.second

    def sub(self):
        return self.first - self.second

    def mul(self):
        return self.first * self.second

    def div_float(self):
        return self.first / self.second

    def div_int(self):
        return self.first // self.second

    def mod(self):
        return self.first % self.second


class MoreCalc(FourCal):  # 상속
    def pow(self):  # 기능 추가
        return self.first ** self.second

    def mod(self):  # 기능 변경
        return self.first % self.second + 1

if __name__=='__main__':
    m = MoreCalc()
    m.set_data(11, 5)
    print("덧셈 : {}".format(m.add()))
    print("제곱 : {}".format(m.pow()))
    print("나머지 : {}".format(m.mod()))

    m = MoreCalc(2, 10)
    print("덧셈 : {}".format(m.add()))
    print("제곱 : {}".format(m.pow()))
    print("나머지 : {}".format(m.mod()))

    a = FourCal()
    print(type(a))
    a.set_data(10, 20)
    print(type(a))
    print(a.first)
    print(a.second)
    print("덧셈 : {}".format(a.add()))
    print("뺄셈 : {}".format(a.sub()))
    print("곱셈 : {}".format(a.mul()))
    print("정수몫 : {}".format(a.div_int()))
    print("실수몫 : {}".format(a.div_float()))
    print("나머지 : {}".format(a.mod()))

    b = FourCal()
    print("덧셈 : {}".format(b.add()))
    b.set_data(11,4)
    print("덧셈 : {}".format(b.add()))

    c = FourCal(10, 6)
    print(c.first)
    print(c.second)
    print("정수몫 : {}".format(c.div_int()))

    d = FourCal(10, 4)
    print(d.first)
    print(d.second)
    print("정수몫 : {}".format(d.div_int()))

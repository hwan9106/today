class Calculator:  # 클래스 선언
    def __init__(self):  # 생성자 : 내부 함수들은 첫번째 인수를 self를 가져라!!!
        self.result = 0  # 속성(멤버변수, 필드)를 선언하고 초기화
        self._priVar = 100  # _로 시작하는 변수는 지역변수이다.

    def add(self, num):  # 사용할 메서드를 정의
        self.result += num
        return self.result

    def sub(self, num):
        self.result -= num
        return self.result


# 객체 생성
cal1 = Calculator()
cal2 = Calculator()
print(type(cal1), " : ", type(cal2))

# 객체 사용 : 객체별로 별도의 데이터를 가진다.
print(cal1.add(3))
print(cal1.add(4))

cal1.result = 100  # 외부에서 마음대로 접근 가능
print(cal1.add(4))

print(cal1._priVar)  # 지역변수에 접근이 가능하다.
cal1._priVar = 88
print(cal1._priVar)  # 지역변수에 접근이 가능하다.

print(cal2.add(3))
print(cal2.add(7))
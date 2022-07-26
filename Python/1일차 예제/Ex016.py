# 년도를 입력받아 윤/평년을 판단하는 프로그램을 만드시오
# if문으로 해보고 삼항 연산자로도 해봐라!!!!

year = int(input('년도는?'))

isLeapYear = year % 400 == 0 or year % 4 == 0 and year % 100 != 0

if isLeapYear:
    print("%d년은 윤년입니다." % year)
else:
    print("%d년은 평년입니다." % year)

print("{0}년은 {1}년입니다.".format(year, "윤" if isLeapYear else "평"))
print("{0}년은 {1}년입니다.".format(year, isLeapYear and "윤" or "평"))
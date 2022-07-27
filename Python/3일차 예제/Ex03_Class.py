class Family:
    last_name = None


kimc = Family()
leec = Family()

kimc.last_name = "김"
print(kimc.last_name)
print(leec.last_name)
print(Family.last_name)
print()

leec.last_name = "이"
print(kimc.last_name)
print(leec.last_name)
print(Family.last_name)
print()

Family.last_name = "신"  # 클래스 이름으로 접근하면 자바의 static처럼 사용가능
print(kimc.last_name)
print(leec.last_name)
print(Family.last_name)
print()

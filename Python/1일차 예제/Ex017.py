# 점수를 입력받아 A~F까지 학점을 판단하는 프로그램을 만드시오
# elif 명령을 사용해라!!!

score = int(input('점수는?'))

if 100 >= score >= 90:
    grade = 'A'
elif 90 > score >= 80:
    grade = 'B'
elif 80 > score >= 70:
    grade = 'C'
elif 70 > score >= 60:
    grade = 'D'
else:
    grade = 'F'

print(str(score) + '의 학점은 "' + grade + '"입니다.');


if score >= 90:
    grade = 'A'
elif score >= 80:
    grade = 'B'
elif score >= 70:
    grade = 'C'
elif score >= 60:
    grade = 'D'
else:
    grade = 'F'

print(str(score) + '의 학점은 "' + grade + '"입니다.');

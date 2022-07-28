"""
평균값 구하기
다음과 같이 총 10줄로 이루어진 sample.txt 파일이 있다.
sample.txt 파일의 숫자 값을 모두 읽어 총합과 평균 값을 구한 후
평균 값을 result.txt 파일에 쓰는 프로그램을 작성하시오.
70
60
55
75
95
90
80
80
85
100
"""
f1 = open("sample.txt", "r")
total=avg=cnt=0
while True:
    line = f1.readline()
    if not line:
        break
    total += int(line.strip())
    cnt += 1
f1.close()

avg = total / cnt
f2 = open("result.txt", "w")
f2.write("{:10.3}\n".format(avg))
f2.close()
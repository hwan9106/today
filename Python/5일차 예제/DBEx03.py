import pymysql

# 전역변수 선언부
conn = None
cur = None
sql = ""
# 메인 코드
conn = pymysql.connect(host='localhost', user='jspuser', password='123456', db='jspdb', charset='utf8')  # 접속정보
cur = conn.cursor()  # 커서생성
sql = "show tables"  # 실행할 sql문
cur.execute(sql)  # 커서로 sql문 실행

print("테이블 목록")
while True:
    row = cur.fetchone()
    if row == None:
        break
    print(row[0])

# conn.commit()  # 저장
conn.close()  # 종료

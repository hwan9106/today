import cx_Oracle

dsn = cx_Oracle.makedsn("localhost", 1521, service_name = "XE") # 오라클 주소
connection = cx_Oracle.connect(user="jspuser", password="123456", dsn=dsn, encoding="UTF-8") # 오라클 접속

cur = connection.cursor() # 실행 결과 데이터를 담을 메모리 객체

# 시간 알아내기
for row in cur.execute("select sysdate from dual"):
    print(row)
print("-"*50)

# 테이블 목록보기
for row in cur.execute("select * from tab"):
    print(row)
print("-"*50)

# memo테이블 내용보기
for row in cur.execute("select * from memo"):
    print(row)


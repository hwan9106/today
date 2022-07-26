"""
다음과 같은 내용을 지닌 파일 test2.txt가 있다.
이 파일의 내용 중 "java"라는 문자열을 "python"으로 바꾸어서 저장해 보자.

Life is too short
you need java

※ replace 함수를 사용해 보자.

"""
content = """Life is too short
you need java
"""
# 저장
f = open('test2.txt', 'w') 
f.write(content)
f.close()

# 읽어서 변경
f = open('test2.txt', 'r')
content = f.read().replace('java','python')
f.close()

# 저장
f = open('test2.txt', 'w')
f.write(content)
f.close()

# 읽어서 출력
f = open('test2.txt', 'r')
print("읽은내용\n" + "*" * 80)
print(f.read())
print("*" * 80)
f.close()

"""
이번에는 문서 파일을 읽어서 그 문서 파일 안에 있는 탭(tab)을 공백(space) 4개로 바꾸어 주는
스크립트를 작성해 보자.

필요한 기능은? 문서 파일 읽어 들이기, 문자열 변경하기
입력 받는 값은? 탭을 포함한 문서 파일
출력하는 값은? 탭이 공백으로 수정된 문서 파일
다음과 같은 형식으로 프로그램이 수행되도록 만들 것이다.

python tabto4.py src dst

원본파일은 파이참에서 만들지 마시고 별도의 에디터에서 만드세요
파이참 에디터가 탭을 공백으로 바꿔서 저장을 해주기 때문에 결과를 확인 못해!!!!
"""
import sys

if len(sys.argv) == 3:
    src = sys.argv[1]  # 원본 파일명
    dst = sys.argv[2]  # 사본 파일명
    # print(src , dst)
    # 데이터 읽기
    srcFile = open(src, "r", encoding="utf-8")
    content = srcFile.read()
    srcFile.close()
    # 문자열 변경하기
    newContent =  content.replace("\t", "*" * 10)
    # 데이터 저장하기
    dstFile = open(dst, "w")
    dstFile.write(newContent)
    dstFile.close()
else:
    print("잘못된 사용방법입니다.")
    print("python {} 원본파일 사본파일".format(__file__))
    print()
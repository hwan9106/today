"""
하위 디렉터리 검색하기
특정 디렉터리부터 시작해서 그 하위 모든 파일 중 파이썬 파일(*.py)만 출력해 주는
프로그램을 만들려면 어떻게 해야 할까?
"""
import os

"""
filenames = os.listdir(".")
# print(filenames)
for filename in filenames:
    print(filename)
"""


def search_dir(dirname):
    try:
        filenames = os.listdir(dirname)  # 모든 파일 목록을 가져오기
        for filename in filenames:
            full_filename = os.path.join(dirname, filename)
            # print(full_filename)
            # 현재 이름이 폴더인지 파일인지를 판단
            if os.path.isdir(full_filename):  # 폴더라면
                search_dir(full_filename)  # 재귀 호출
            else:  # 파일이라면
                ext = os.path.splitext(full_filename)[-1]  # 확장자만 추출
                if ext == '.py':   # 파일의 확장자가 .py라면
                    print(full_filename)  # 출력
    except PermissionError:
        pass
    except FileNotFoundError:
        pass


search_dir("d:\\")  # d드라이브의 모든 파이선 파일 검색
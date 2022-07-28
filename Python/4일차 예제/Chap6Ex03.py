"""
A 씨는 게시판 프로그램을 작성하고 있다.
그런데 게시물의 총 건수와 한 페이지에 보여 줄 게시물 수를 입력으로 주었을 때
총 페이지 수를 출력하는 프로그램이 필요하다고 한다.
"""

# 총 페이지수를 리턴하는 함수를 만들어 보자. 인수로 2개(총건수, 페이지당 글 수)
"""
총개수 : 67, 페이지당 글수 : 10 ===> 총 페이지수 : 7 = 67//10 + 1  = (67-1)//10 + 1
총개수 : 89, 페이지당 글수 : 10 ===> 총 페이지수 : 9 = 89//10 + 1  = (89-1)//10 + 1
총개수 : 100, 페이지당 글수 : 10 ===> 총 페이지수 : 10 = 100//10   = (100-1)//10 + 1

문제점이 하나 있다. 총 개수가 페이지당 글수의 배수인 경우는 몫에다가 +1을 해주면 안된다.

"""


def get_total_page(total_count, size_of_page):
    total_page = total_count // size_of_page  # 일단 몫을 구한다.
    if total_count % size_of_page != 0:  # 배수가 아닌 경우만
        total_page += 1   # +1을 해준다.
    return total_page


print(get_total_page(67, 10))
print(get_total_page(89, 10))
print(get_total_page(100, 10))


def get_total_page(total_count, size_of_page):
    return (total_count-1) // size_of_page + 1


print(get_total_page(67, 10))
print(get_total_page(89, 10))
print(get_total_page(100, 10))

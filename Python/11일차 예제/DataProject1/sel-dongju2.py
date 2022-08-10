from bs4 import BeautifulSoup 
import urllib.request as req
# 뒤의 인코딩 부분은 "저자:윤동주"라는 의미입니다.
# 따로 입력하지 말고 위키 문헌 홈페이지에 들어간 뒤에 주소를 복사해서 사용하세요.
url = "https://ko.wikisource.org/wiki/%EC%A0%80%EC%9E%90:%EC%9C%A4%EB%8F%99%EC%A3%BC"
res = req.urlopen(url)
soup = BeautifulSoup(res, "html.parser")
print("윤동주 작가의 저작물")
print("-" * 100)
toc = soup.select("h3 > span.mw-headline")
ul = soup.select("h3+ul")
for i in range(len(toc)):
    print("[" + toc[i].string + "]")
    li = ul[i].select("li")
    for item in li:
        print("   -", item.select_one("a").text)
print("-" * 100)

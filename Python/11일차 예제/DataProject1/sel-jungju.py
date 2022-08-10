from bs4 import BeautifulSoup 
import urllib.request as req
# 뒤의 인코딩 부분은 "저자:서정주"라는 의미입니다.
# 따로 입력하지 말고 위키 문헌 홈페이지에 들어간 뒤에 주소를 복사해서 사용하세요.
url = "https://ko.wikisource.org/wiki/%EC%A0%80%EC%9E%90:%EC%84%9C%EC%A0%95%EC%A3%BC"
res = req.urlopen(url)
soup = BeautifulSoup(res, "html.parser")
print("서정주 작가의 저작물")
print("-" * 100)
toc = soup.select("h3 > span.mw-headline") #h3태그의 클래스 이름이 mw-headline인것을 찾앗다.
ul = soup.select("h3+ul") #h3태그의 형제 ul을 찾는다.
for i in range(len(toc)): #개수만큼 반복
    print("[" + toc[i].string + "]") #몇번째 것의 가져와라 ->시,평론 등 구분이 나옴
    #h3형제 ul의 자시들만 반복
    li = ul[i].select("li")
    for item in li:
        print("   -", item.select_one("a").text)
print("-" * 100)

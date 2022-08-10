import urllib.request as req
from bs4 import BeautifulSoup

url="https://www.naver.com"
# urlopen()으로 데이터 가져오기 --- (※1)
res = req.urlopen(url) # 읽음
# BeautifulSoup으로 분석하기 --- (※2)
soup = BeautifulSoup(res, "html.parser") #결과를 넣어줌
# 원하는 데이터 추출하기 --- (※3)
links = soup.find_all("a")
#링크 목록 출력하기
for a in links:
    href=a.attrs['href']
    text=a.string
    if not href.startswith("#"):
        print(text,">",href)
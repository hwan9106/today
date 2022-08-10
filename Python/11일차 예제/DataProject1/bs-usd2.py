from bs4 import BeautifulSoup
import urllib.request as req
from requests import get

# HTML 가져오기
url = "http://finance.naver.com/marketindex/"
res = get(url)
# HTML 분석하기
soup = BeautifulSoup(res.content.decode('euc-kr','replace'), "html.parser")
# 원하는 데이터 추출하기 --- (※1)
exchangeList = soup.select("ul#exchangeList li")
print("개수 : ", len(exchangeList))

for exchange in exchangeList:
    blind = exchange.select_one("h3 > span")
    value = exchange.select_one("div > span.value")
    print(blind.string, ":" , value.string,"원")
package kr.human.mvc08;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Lotto {
	public static void main(String[] args) {
		try {
			Document doc = Jsoup.connect("https://dhlottery.co.kr/gameResult.do?method=byWin&wiselog=H_C_1_1").get();
			
			Elements elements = doc.select("div.num p span");
			System.out.println(elements.size() + "개");
			for(Element e : elements) {
				System.out.print(e.text() + " ");
			}
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

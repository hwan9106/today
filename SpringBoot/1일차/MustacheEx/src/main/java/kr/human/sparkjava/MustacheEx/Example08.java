package kr.human.sparkjava.MustacheEx;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Example08 {
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	static class ButtonTitle{
		private String id;
		private String title;
	}
	
	public static void main(String[] args) throws IOException {
		MustacheFactory mf = new DefaultMustacheFactory();
		Mustache mustache = mf.compile("templates/hello8.mustache");

		// 객체 리스트 사용
		HashMap<String, Object> map = new HashMap<>();
		map.put("buttonOne",new ButtonTitle("myBtn1","HTML"));
		map.put("buttonTwo",new ButtonTitle("myBtn2","CSS"));
		StringWriter sw = new StringWriter();
		mustache.execute(sw, map).flush();
		System.out.println(sw.toString());
	}
}
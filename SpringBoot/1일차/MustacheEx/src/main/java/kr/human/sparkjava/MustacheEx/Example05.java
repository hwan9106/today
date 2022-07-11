package kr.human.sparkjava.MustacheEx;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Example05 {
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	static class Person{
		private String name;
		private int age;
		private boolean gender;
	}
	
	public static void main(String[] args) throws IOException {
		MustacheFactory mf = new DefaultMustacheFactory();
		Mustache mustache = mf.compile("templates/hello5.mustache");

		// 객체 배열의 사용
		HashMap<String, Object> map = new HashMap<>();
		map.put("users",new Person[] {
			new Person("한사람", 22, false),
			new Person("두사람", 18, true),
			new Person("세사람", 31, true),
			new Person("네사람", 42, false)
		});

		mustache.execute(new PrintWriter(System.out), map).flush();
	}
}
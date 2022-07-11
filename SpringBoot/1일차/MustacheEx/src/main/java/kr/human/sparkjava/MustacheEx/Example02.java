package kr.human.sparkjava.MustacheEx;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

public class Example02 {

  public static void main(String[] args) throws IOException {
    MustacheFactory mf = new DefaultMustacheFactory();
    Mustache mustache = mf.compile("templates/hello2.mustache");

    // 변수 사용
    HashMap<String, Object> map = new HashMap<>();  
    map.put("name", "한사람");
    map.put("age", 33);
    map.put("gender",true);
    
    mustache.execute(new PrintWriter(System.out), map).flush();
  }
}
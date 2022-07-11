package kr.human.sparkjava.MustacheEx;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

public class Example03 {

  public static void main(String[] args) throws IOException {
    MustacheFactory mf = new DefaultMustacheFactory();
    Mustache mustache = mf.compile("templates/hello3.mustache");

    // 배열
    HashMap<String, Object> map = new HashMap<>();  
    map.put("users", "한사람,두사람,세사람,네사람".split(","));
    
    mustache.execute(new PrintWriter(System.out), map).flush();
  }
}
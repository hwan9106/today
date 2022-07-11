package kr.human.sparkjava.MustacheEx;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

public class Example01 {

  public static void main(String[] args) throws IOException {
    MustacheFactory mf = new DefaultMustacheFactory();
    Mustache mustache = mf.compile("templates/hello.mustache");

    HashMap<String, String> map = new HashMap<>();  
    map.put("planet", "Mustache!!!");
    
    mustache.execute(new PrintWriter(System.out), map).flush();
  }
}
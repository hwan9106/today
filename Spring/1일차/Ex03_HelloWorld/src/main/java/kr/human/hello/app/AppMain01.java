package kr.human.hello.app;

import kr.human.hello.vo.HelloWorld;
import kr.human.hello.vo.HelloWorldImpl;

public class AppMain01 {
	public static void main(String[] args) {
		HelloWorld helloWorld = new HelloWorldImpl();
		helloWorld.sayHello("한사람");
	}
}

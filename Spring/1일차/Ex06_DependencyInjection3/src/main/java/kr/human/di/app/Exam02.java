package kr.human.di.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.human.di.vo.Student02;

public class Exam02 {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		
		Student02 student02 = context.getBean("student", Student02.class);
		
		student02.cheating();
		
	}
	
}

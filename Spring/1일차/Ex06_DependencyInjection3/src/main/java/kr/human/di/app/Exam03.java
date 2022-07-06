package kr.human.di.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.human.di.vo.Student03;

public class Exam03 {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans02.xml");
		
		Student03 amiya= context.getBean("studentAmiya",Student03.class);
		amiya.diplayInfo();
		
		Student03 asish= context.getBean("studentAsish",Student03.class);
		asish.diplayInfo();
	}
}

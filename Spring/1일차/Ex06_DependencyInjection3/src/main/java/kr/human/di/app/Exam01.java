package kr.human.di.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.human.di.vo.Student01;

public class Exam01 {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		
		Student01 amiya = context.getBean("studentAmiya", Student01.class);
		
		amiya.displayInfo();
		
		Student01 asish = context.getBean("studentAsish", Student01.class);
		
		asish.displayInfo();
	}
}

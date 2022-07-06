package kr.human.di.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.human.di.vo.Application;

public class AppliecationApp {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("Application.xml");
		
		Application application1 = context.getBean("application1",Application.class);
		System.out.println(application1);
		
		Application application2 = context.getBean("application2",Application.class);
		System.out.println(application2);
		
		
		
	}
}

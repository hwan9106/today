package kr.human.di.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.human.di.vo.Driver;

public class DriverApp {
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("Driver.xml");
		
		Driver driver1 = context.getBean("driver1",Driver.class);
		System.out.println(driver1);
		
		Driver driver2 = context.getBean("driver2",Driver.class);
		System.out.println(driver2);
		
		context.close();
	}
}

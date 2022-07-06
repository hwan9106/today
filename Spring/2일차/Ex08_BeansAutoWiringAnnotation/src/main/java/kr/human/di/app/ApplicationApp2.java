package kr.human.di.app;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.human.di.vo.Application;
import kr.human.di.vo.Bond;
import kr.human.di.vo.Driver;

public class ApplicationApp2 {
	public static void main(String[] args) {
		AbstractApplicationContext context = 
				new ClassPathXmlApplicationContext("AppConfig.xml");
		
		Application application = context.getBean("application",Application.class);
		System.out.println(application);
		
		Driver driver = context.getBean("driver",Driver.class);
		System.out.println(driver);
		
		Bond bond = context.getBean("bond",Bond.class);
		bond.showCar();
		context.close();
	}
}

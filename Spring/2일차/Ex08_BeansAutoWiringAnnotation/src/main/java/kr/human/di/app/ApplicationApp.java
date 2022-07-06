package kr.human.di.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.human.di.config.AppConfig;
import kr.human.di.vo.Application;
import kr.human.di.vo.Bond;
import kr.human.di.vo.Driver;

public class ApplicationApp {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context=
				new AnnotationConfigApplicationContext(AppConfig.class);
		
		Application application = context.getBean("application",Application.class);
		System.out.println(application);
		
		Driver driver = context.getBean("driver",Driver.class);
		System.out.println(driver);
		
		Bond bond = context.getBean("bond",Bond.class);
		bond.showCar();
		
		context.close();
	}
}

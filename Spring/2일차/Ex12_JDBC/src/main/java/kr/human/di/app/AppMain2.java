package kr.human.di.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import kr.human.di.config.AppConfig;
import kr.human.di.service.TestService;
import kr.human.di.vo.TestVO;

public class AppMain2 {
	public static void main(String[] args) {
		AbstractApplicationContext  context = new AnnotationConfigApplicationContext(AppConfig.class);

		TestService testService = context.getBean("testService", TestService.class);
		
		TestVO testVO1 = testService.selectTest();
		System.out.println(testVO1);
		
		TestVO testVO2 = testService.selectTest2();
		System.out.println(testVO2);
		
        context.close();
	}

}

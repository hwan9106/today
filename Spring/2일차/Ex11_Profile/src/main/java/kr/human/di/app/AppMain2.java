package kr.human.di.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.human.di.config.AppConfig;
import kr.human.di.service.FileService;


public class AppMain2 {
	public static void main(String[] args) {
		AbstractApplicationContext  context = new ClassPathXmlApplicationContext("AppConfig.xml");
        //Sets the active profiles
        context.getEnvironment().setActiveProfiles("Development");
        /*
         * Perform any logic here
         */
        context.close();
	} 
}

package kr.human.di.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import kr.human.di.config.AppConfig;
import kr.human.di.service.FileService;


public class AppMain {
	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		FileService fileService = context.getBean("fileService", FileService.class);
		fileService.readValues();
         
        context.close();
	} 
}

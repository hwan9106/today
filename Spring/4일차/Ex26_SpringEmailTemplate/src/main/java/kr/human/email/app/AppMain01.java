package kr.human.email.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.human.email.config.AppConfig;
import kr.human.email.service.MailService;

public class AppMain01 {
	public static void main(String[] args) {
		// 메일 발송!!!
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		
		MailService mailService = context.getBean("mailService", MailService.class);
		
		mailService.sendEmail();
		
		mailService.sendEmail("hwan9532@naver.com", "제목", "내용<h1>꽝</h1>");
		
		context.close();
	}
}

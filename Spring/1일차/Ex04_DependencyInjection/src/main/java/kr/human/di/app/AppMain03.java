package kr.human.di.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.human.di.config.CommunicationConfig;
import kr.human.di.vo.Communication;
import kr.human.di.vo.Encryption;
import kr.human.di.vo.Messaging;

public class AppMain03 {
	public static void main(String[] args) {
		AbstractApplicationContext context = 
				new AnnotationConfigApplicationContext(CommunicationConfig.class);		
		
		Communication communication = context.getBean("communication", Communication.class);
		communication.communicate();
		
		Messaging messaging = context.getBean("messaging",Messaging.class);
		messaging.sendMessage();
		
		Encryption encryption = context.getBean("encryption", Encryption.class);
		encryption.encryptData();
		
		
		context.close();
	}
}

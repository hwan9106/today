package kr.human.di.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.human.di.config.SetObjectConfig;
import kr.human.di.vo.ArrayObject;
import kr.human.di.vo.SetObject;

public class SetObjectMain2 {
	public static void main(String[] args) {
		AbstractApplicationContext context = 
				new AnnotationConfigApplicationContext(SetObjectConfig.class);
		
		SetObject setObject1 = context.getBean("setObj1",SetObject.class);
		System.out.println(setObject1);
		
		SetObject setObject2 = context.getBean("setObj2",SetObject.class);
		System.out.println(setObject2);
		
		context.close();
	}
}

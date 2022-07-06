package kr.human.di.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.human.di.config.ListObjectConfig;
import kr.human.di.vo.ListObject;

public class ListObjectMain2 {
	public static void main(String[] args) {
		AbstractApplicationContext context = 
				new AnnotationConfigApplicationContext(ListObjectConfig.class);//ACAC -> config
		
		ListObject object1 = context.getBean("listObj1",ListObject.class);
		System.out.println(object1);
		ListObject object2 = context.getBean("listObj2",ListObject.class);
		System.out.println(object2);
		ListObject object3 = context.getBean("listObj3",ListObject.class);
		System.out.println(object3);
		
		
		
		context.close();
	}
}

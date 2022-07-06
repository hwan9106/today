package kr.human.di.app;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.human.di.vo.ArrayObject;

public class ArrayObjectMain {
	public static void main(String[] args) {
		AbstractApplicationContext context = 
				new ClassPathXmlApplicationContext("ArrayObject.xml");
		
		ArrayObject object1 = context.getBean("arrayObject1",ArrayObject.class);
		System.out.println(object1);
		
		ArrayObject object2 = context.getBean("arrayObject2",ArrayObject.class);
		System.out.println(object2);
		
		//인덱스 확인
		for(int i =0; i<object2.getNames().length;i++) {
			System.out.println(object2.getNames()[i]);
		}
		
		context.close();
	}
}

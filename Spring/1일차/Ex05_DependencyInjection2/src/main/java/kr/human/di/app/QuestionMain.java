package kr.human.di.app;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.human.di.vo.QuestionVO;

public class QuestionMain {
	public static void main(String[] args) {
		AbstractApplicationContext context = 
				new ClassPathXmlApplicationContext("QuestionVO.xml");
		
		QuestionVO vo1 = context.getBean("question1", QuestionVO.class);
		System.out.println(vo1);
		
		QuestionVO vo2 = context.getBean("question2", QuestionVO.class);
		System.out.println(vo2);
		
		context.close();
	}
}

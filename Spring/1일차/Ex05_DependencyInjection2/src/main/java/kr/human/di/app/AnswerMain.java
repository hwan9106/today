package kr.human.di.app;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.human.di.vo.AnswerVO;
import kr.human.di.vo.SetObject;
import kr.human.di.vo.UserVO;

public class AnswerMain {
	public static void main(String[] args) {
		AbstractApplicationContext context = 
				new ClassPathXmlApplicationContext("Answer.xml");
		
		AnswerVO vo1 = context.getBean("answer1", AnswerVO.class);
		System.out.println(vo1);

		AnswerVO vo2 = context.getBean("answer2", AnswerVO.class);
		System.out.println(vo2);
		
		UserVO userVO1 = context.getBean("user1", UserVO.class);
		System.out.println(userVO1);

		UserVO userVO2 = context.getBean("user2", UserVO.class);
		System.out.println(userVO2);
		
		context.close();
	}
}

package kr.human.di.app;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.human.di.vo.ArrayObject;
import kr.human.di.vo.EmpVO;
import kr.human.di.vo.SetObject;

public class EmpVOMain {
	public static void main(String[] args) {
		AbstractApplicationContext context = 
				new ClassPathXmlApplicationContext("EmpVO.xml");
		
		EmpVO vo1 = context.getBean("emp1",EmpVO.class);
		System.out.println(vo1);
		
		//두개의 인자
		EmpVO vo2 = context.getBean("emp2",EmpVO.class);
		System.out.println(vo2);		
		EmpVO vo3 = context.getBean("emp3",EmpVO.class);
		System.out.println(vo3);
		EmpVO vo4 = context.getBean("emp4",EmpVO.class);
		System.out.println(vo4);
		EmpVO vo5 = context.getBean("emp5",EmpVO.class);
		System.out.println(vo5);
	
		//Setter를 통한 주입
		EmpVO vo6 = context.getBean("emp6",EmpVO.class);
		System.out.println(vo6);
		EmpVO vo7 = context.getBean("emp7",EmpVO.class);
		System.out.println(vo7);
		
		EmpVO vo8 = context.getBean("emp8",EmpVO.class);
		System.out.println(vo8);
		EmpVO vo9 = context.getBean("emp9",EmpVO.class);
		System.out.println(vo9);
		EmpVO vo10 = context.getBean("emp10",EmpVO.class);
		System.out.println(vo10);
		context.close();
	}
}

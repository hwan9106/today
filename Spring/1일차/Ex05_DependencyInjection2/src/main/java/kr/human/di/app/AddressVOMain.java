package kr.human.di.app;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.human.di.vo.AddressVO;
import kr.human.di.vo.ArrayObject;
import kr.human.di.vo.SetObject;

public class AddressVOMain {
	public static void main(String[] args) {
		AbstractApplicationContext context = 
				new ClassPathXmlApplicationContext("AddressVO.xml");
		
		AddressVO vo1 = context.getBean("addr1",AddressVO.class);
		System.out.println(vo1);
		
		AddressVO vo2 = context.getBean("addr2",AddressVO.class);
		System.out.println(vo2);
		
		AddressVO vo3 = context.getBean("addr3",AddressVO.class);
		System.out.println(vo3);
		context.close();
	}
}

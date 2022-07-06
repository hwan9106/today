package kr.human.di.app;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.human.di.service.PeopleService;
import kr.human.di.service.TestService;
import kr.human.di.vo.PeopleVO;

public class AppMain {
	public static void main(String[] args) throws Exception {
		AbstractApplicationContext  context = new ClassPathXmlApplicationContext("AppConfig.xml");

		TestService testService = context.getBean("testService", TestService.class);
		System.out.println("현재 : " + testService.today());
		int num1 = 12;
		int num2 = 34;
		System.out.println( num1 +  " + " + num2 + " = " + testService.sum(num1, num2));
		System.out.println( num1 +  " * " + num2 + " = " + testService.mul(num1, num2));
		
		System.out.println("VO : " + testService.vo(num1, num2));
		System.out.println("-".repeat(80));
		
		PeopleService peopleService = context.getBean("peopleService", PeopleService.class);
		
		selectList(peopleService);
		
		// peopleService.insert(new PeopleVO(0, "추가인", 22));
		// selectList(peopleService);
		
		// PeopleVO vo = new PeopleVO(5, "변경인", 33);
		// peopleService.update(vo);
		// selectList(peopleService);
		
		// peopleService.delete(7);
		// selectList(peopleService);
		
		
        context.close();
	}

	// 목록보기!!!!!
	private static void selectList(PeopleService peopleService) {
		List<PeopleVO> list = peopleService.selectList();
		System.out.println(list.size() + "개의 데이터가 있습니다.");
		for(PeopleVO vo : list)
			System.out.println(vo);
		System.out.println("-".repeat(80));
	}
}

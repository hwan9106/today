package kr.human.di.app;

import java.util.List;
import java.util.Random;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.human.di.service.PeopleService;
import kr.human.di.vo.PeopleVO;

public class AppMain {
	public static void main(String[] args) throws Exception {
		AbstractApplicationContext  context = new ClassPathXmlApplicationContext("AppConfig.xml");

		selectList(context);
		insert(context);
		selectList(context);
		update(context, 1, "바뀐놈", 24);
		delete(context, 11);
		selectList(context);
		
        context.close();
	}

	private static void insert(AbstractApplicationContext context) {
		PeopleService peopleService = context.getBean("peopleService", PeopleService.class);
		Random random = new Random();
		if(peopleService.insert(new PeopleVO(0, "이름 "  + random.nextInt(101), random.nextInt(100)+1))){
			System.out.println("저장 성공!!!");
		}else{
			System.out.println("저장 실패!!!");
		}
	}
	@SuppressWarnings("unused")
	private static void update(AbstractApplicationContext context, int idx, String name, int age) throws Exception {
		PeopleService peopleService = context.getBean("peopleService", PeopleService.class);
		System.out.println("수정전 : " + peopleService.selectByIdx(idx));
		if(peopleService.update(new PeopleVO(idx, name, age))){
			System.out.println("수정 성공!!!");
		}else{
			System.out.println("수정 실패!!!");
		}
		System.out.println("수정후 : " + peopleService.selectByIdx(idx) );
	}

	@SuppressWarnings("unused")
	private static void delete(AbstractApplicationContext context, int idx) throws Exception {
		PeopleService peopleService = context.getBean("peopleService", PeopleService.class);
		System.out.println("삭제 전 : " + peopleService.selectByIdx(idx));
		if(peopleService.delete(new PeopleVO(idx, null, 0))){
			System.out.println("삭제 성공!!!");
		}else{
			System.out.println("삭제 실패!!!");
		}
	}
	
	private static void selectList(AbstractApplicationContext context) {
		PeopleService peopleService = context.getBean("peopleService", PeopleService.class);
		List<PeopleVO> list = peopleService.selectList();
		
		System.out.println("전체 : " + list.size() + "개");
		System.out.println("-".repeat(80));
		for(PeopleVO vo : list) {
			System.out.println(vo.getIdx() + ". " + vo.getName() + "(" + vo.getAge() + "세)");
		}
		System.out.println("-".repeat(80));
	}

}

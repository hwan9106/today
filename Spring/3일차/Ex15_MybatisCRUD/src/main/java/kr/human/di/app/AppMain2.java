package kr.human.di.app;

import java.util.HashMap;
import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.human.di.service.PeopleService;
import kr.human.di.service.PeopleService2;
import kr.human.di.service.TestService;
import kr.human.di.vo.PeopleVO;

public class AppMain2 {
	public static void main(String[] args) throws Exception {
		AbstractApplicationContext  context = new ClassPathXmlApplicationContext("AppConfig.xml");

		PeopleService2 peopleService2 = context.getBean("peopleService2",PeopleService2.class);
		HashMap<String, Object> map = peopleService2.selectByIdx(1);
		System.out.println(map);
		System.out.println(map.get("idx") +"."+map.get("name") + "(" + map.get("age") +")");
		
		List<HashMap<String, Object>> list = peopleService2.selectList();
		System.out.println(list);
		System.out.println(list.size()+"개");
		/*for(HashMap<String, Object> m :list) {
			System.out.println(m.get("idx") +"."+m.get("name") + "(" + m.get("age") +")");
		}*/
		
		map.put("name", "하하하하하");
		map.put("age", 88);
		peopleService2.insert(map);
		list = peopleService2.selectList();
		System.out.println(list);
		System.out.println(list.size()+"개");
        context.close();
	}

	
}	

package kr.human.di.app;

import java.util.Map;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.human.di.vo.ArrayObject;
import kr.human.di.vo.MapObject;
import kr.human.di.vo.SetObject;

public class MapObjectMain {
	public static void main(String[] args) {
		AbstractApplicationContext context = 
				new ClassPathXmlApplicationContext("MapObject.xml");
		MapObject mapObject1 = context.getBean("mapObj1", MapObject.class);
		System.out.println(mapObject1);
		
		MapObject mapObject2 = context.getBean("mapObj2", MapObject.class);
		System.out.println(mapObject2);
		
		MapObject mapObject3 = context.getBean("mapObj3", MapObject.class);
		System.out.println(mapObject3);
		
		@SuppressWarnings("unchecked")
		Map<String, Integer> map = context.getBean("map", Map.class);
		System.out.println(map);
		
		context.close();
	}
}

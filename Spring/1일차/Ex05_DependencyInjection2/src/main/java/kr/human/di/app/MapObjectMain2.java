package kr.human.di.app;

import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import kr.human.di.config.MapObjectConfig;
import kr.human.di.vo.MapObject;

public class MapObjectMain2 {
	public static void main(String[] args) {
		AbstractApplicationContext context = 
				new AnnotationConfigApplicationContext(MapObjectConfig.class);
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

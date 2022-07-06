package kr.human.di.config;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.human.di.vo.MapObject;
import kr.human.di.vo.SetObject;

@Configuration
public class MapObjectConfig {
	
	@Bean(name = "mapObj1")
	public MapObject mapObject1() { //생성자에 의한 주입
		MapObject mapObject = new MapObject();
		Map<String, Integer> map = new TreeMap<>();
		map.put("computer",1254480);
		map.put("mouse",50000);
		map.put("keyboard",102000);
		MapObject mapObject2 = new MapObject(map);
		return mapObject;
	}
	@Bean(name = "mapObj2")
	public MapObject mapObject2() { //Setter 에 의한 주입
		Map<String, Integer> map = new TreeMap<>();
		map.put("computer",1254480);
		map.put("mouse",50000);
		map.put("keyboard",102000);
		MapObject mapObject = new MapObject();
		mapObject.setMap(map);
		return mapObject;
	}
	@Bean(name = "map")
	public Map<String, Integer> getMap(){ // 맵 객체를 스프링에 등록
		Map<String, Integer> map  = new TreeMap<>();
		map.put("computer", 1285400);
		map.put("mouse", 1400);
		map.put("keyboard", 12000);
		return map;
	}
	
	@Bean(name = "mapObj3")
	public MapObject mapObject3() {
		MapObject mapObject = new MapObject();
		mapObject.setMap(getMap()); // 참조에 의한 주입
		return mapObject;
	}
}

package kr.human.di.config;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.human.di.vo.ListObject;
import kr.human.di.vo.SetObject;

@Configuration
public class ListObjectConfig {
	
	@Bean(name = "listObj1")
	public ListObject listObject1() {
		
		return new ListObject(Arrays.asList("한사람","두사람","세사람")); //생성자에 의한 주입
	}
	
	@Bean(name = "listObj2")
	public ListObject listObject2() {
		ListObject listObject = new ListObject();
		listObject.setList(Arrays.asList("한사람","두사람","세사람")); //Setter에 의한 주입
		return listObject;
	}
	
	@Bean(name = "nameList")
	public List<String> list() {
		
		return Arrays.asList("한사람","두사람","세사람");
	}
	@Bean(name = "listObj3")
	public ListObject listObject3() {
		return new ListObject(list());// 참조에 의한 주입 
	}
	
}

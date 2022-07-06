package kr.human.di.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.human.di.vo.SetObject;

@Configuration
public class SetObjectConfig {
	@Bean(name = "setObj1")
	public SetObject setObject1() {
		Set<String> nameSet = new HashSet<>();
		nameSet.add("한사람");
		nameSet.add("두사람");
		nameSet.add("세사람");
		nameSet.add("네사람");
		nameSet.add("한사람");
		
		return new SetObject(nameSet);
		
	}
	
	@Bean(name = "setObj2")
	public SetObject setObject2() {
		Set<String> nameSet = new HashSet<>();
		nameSet.add("한사람");
		nameSet.add("두사람");
		nameSet.add("세사람");
		nameSet.add("한사람");
		SetObject setObject = new SetObject();
		setObject.setNameSet(nameSet);
		return setObject;
	}
}

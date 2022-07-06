package kr.human.di.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.human.di.vo.ArrayObject;

@Configuration
public class ArrayObjectConfig {
	private int ids[] = {11,22,33,44,55};
	private String names[] = "한사람,두사람,세사람,네사람".split(",");
	
	@Bean(name = "arObj1")
	public ArrayObject arrayObject1() {
		return new ArrayObject(ids, names); // 생성자를 통한 의존성 주입
	}
	
	@Bean(name = "arObj2")
	public ArrayObject arrayObject2() {
		ArrayObject arrayObject = new ArrayObject(); 
		// Setter를 통한 주입
		arrayObject.setIds(new int[] {99,88,77});
		arrayObject.setNames("한놈,두식이,석삼,너구리".split(","));
		return arrayObject;
	}
	
}

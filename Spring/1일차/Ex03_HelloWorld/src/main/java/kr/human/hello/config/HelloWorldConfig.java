package kr.human.hello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.human.hello.vo.HelloWorld;
import kr.human.hello.vo.HelloWorldImpl;

@Configuration // 이 클래스를 환경 설정 파일로 사용 하겠다.
public class HelloWorldConfig {

	// 객체를 스프링 프레임워크에 등록해준다.
	@Bean(name = "helloWorld") // HelloWorld객체를 helloWorld라는 이름으로 스프링에 등록
	public HelloWorld helloWorld() {
		return new HelloWorldImpl();
	}
}

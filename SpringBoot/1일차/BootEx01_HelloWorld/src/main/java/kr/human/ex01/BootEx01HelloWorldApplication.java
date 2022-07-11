package kr.human.ex01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.human.ex01.vo.TestVO;

@SpringBootApplication
@RestController //이 클래스를 REST컨트롤러로 사용하겠다.
public class BootEx01HelloWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootEx01HelloWorldApplication.class, args);
	}
	//GET방식으로 /에 접속하는 주소를 하나 생성했다.
	@GetMapping(value = "/")
	public String hello() {		
		return "Hello Spring Boot!!!";
	}
	@RequestMapping(value = {"/hi","/hello"}) //하나의 메서드로 주소 여러개 지정 가능(GET/POST 모두 가능)
	//@RequestParam(required = false) 인수를 받는데 필수가 아니다.
	public String hello2(@RequestParam (required = false) String name) {
		if(name==null || name.trim().length() ==0) {
			name = "손";
		}
		return name + "님 반갑습니다.";
	}
	@RequestMapping(value = "vo")
	public TestVO testVO(){
		return new TestVO ("한사람",22,false);
	}

}

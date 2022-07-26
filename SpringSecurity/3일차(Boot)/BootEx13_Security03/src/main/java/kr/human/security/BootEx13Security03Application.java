package kr.human.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BootEx13Security03Application {

	public static void main(String[] args) {
		SpringApplication.run(BootEx13Security03Application.class, args);
	}
	
	@RequestMapping(value = "/")
	public String hello() {
		
		return "안녕 스프링시큐리티";
	}

}

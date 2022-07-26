package kr.human.security01;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BootEx12SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootEx12SecurityApplication.class, args);
	}

	// 스프링 부트 구동 시점에 특정 코드 실행 시키기 (CommandLineRunner & ApplicationRunner)
	/*
	 * CommandLineRunner 인터페이스는 구동 시점에 실행되는 코드가 자바 문자열 아규먼트 배열에 접근해야할 필요가 있는 경우에
	 * 사용합니다.
	 * 
	 */
	@Bean
	public CommandLineRunner start1() throws Exception {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				System.out.println("CommandLineRunner 를 이용한 실행!!!");
				//웹브라우저 띄우기
				try {
					System.setProperty("java.awt.headless", "false");
					Desktop.getDesktop().browse(new URI("http://localhost:8080/"));
				} catch (IOException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}
		};
	}

	/*
	 * ApplicationRunner 인터페이스도 CommandLineRunner 인터페이스와 마찬가지로 구동 시점에 run() 메소드를
	 * 실행시키지만 다른 타입의 인자를 받습니다. 단순 인자의 스트링 배열을 포함한 추상화한 ApplicationArguments 타입의 객체가
	 * 대신 run() 메소드의 인자로 넘어옵니다.
	 */
	@Bean
	public ApplicationRunner start2() throws Exception {
		return new ApplicationRunner() {
			@Override
			public void run(ApplicationArguments args) throws Exception {
				System.out.println("ApplicationRunner 를 이용한 실행!!!");
			}
		};
	}
}

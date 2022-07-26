package kr.human.security01;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BootEx13SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootEx13SecurityApplication.class, args);
	}
	@Bean
	public CommandLineRunner start1() throws Exception {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				System.out.println("CommandLineRunner 를 이용한 실행!!!");
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

}

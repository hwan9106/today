package kr.human.ex08;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import kr.human.ex08.service.StudentService;
import kr.human.ex08.vo.Student;

@SpringBootApplication
public class BootEx08HelloWorldJspJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootEx08HelloWorldJspJpaApplication.class, args);
	}
	
	@Bean // Spring Boot Application이 시작될때 자동으로 실행되는 메서드
	public CommandLineRunner commandLineRunner(StudentService studentService) {
		return (args) -> {
			studentService.insert(new Student("한", "사람", "자바"));
			studentService.insert(new Student("두", "인간", "Boot"));
			studentService.insert(new Student("한", "세상", "JPA"));
			
			List<Student> list = studentService.selectAll();
			System.out.println(list.size() + "개 있음!!!");
			for(Student vo : list) System.out.println(vo);
		};
	}

}

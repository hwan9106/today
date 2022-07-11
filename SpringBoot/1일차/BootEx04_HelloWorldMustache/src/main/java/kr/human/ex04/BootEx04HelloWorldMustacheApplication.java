package kr.human.ex04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.human.ex04.vo.TestVO;

@SpringBootApplication
@Controller
public class BootEx04HelloWorldMustacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootEx04HelloWorldMustacheApplication.class, args);
	}

	@GetMapping("/")
    public String index(Model model){
		model.addAttribute("name", "한사람");
		model.addAttribute("gender", false);
		
		List<String> hobby = new ArrayList<>();
		hobby.add("술먹기");
		hobby.add("잠자기");
		hobby.add("고성방가하기");
		hobby.add("노상방뇨");
		
		model.addAttribute("hobby", hobby);
		
		List<TestVO> list = Arrays.asList(new TestVO("한사람", 12, false),new TestVO("두사람", 18, true),new TestVO("세사람", 22, false));
		model.addAttribute("list", list);
		
        return "index";
    }
	
	@GetMapping("/list")
	public String list(Model model){
		List<TestVO> list = Arrays.asList(new TestVO("한사람", 12, false),new TestVO("두사람", 18, true),new TestVO("세사람", 22, false));
		model.addAttribute("list", list);
		return "list";
	}
}

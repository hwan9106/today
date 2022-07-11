package kr.human.ex05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.human.ex05.vo.TestVO;


@SpringBootApplication
@Controller
public class BootEx05HelloWorldJspApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootEx05HelloWorldJspApplication.class, args);
	}

	@GetMapping("/")
    public String index(Model model){
		List<String> hobby = new ArrayList<>();
		hobby.add("술먹기");
		hobby.add("잠자기");
		hobby.add("고성방가하기");
		hobby.add("노상방뇨");
		
		Map<String, Object> map = new HashMap<>();
		map.put("name","한사람");
		map.put("age",22);
		map.put("gender",false);
		
		List<TestVO> list = Arrays.asList(new TestVO("한사람", 12, false),new TestVO("두사람", 18, true),new TestVO("세사람", 22, false));

		model.addAttribute("hobby", hobby);
		model.addAttribute("map", map);
		model.addAttribute("list", list);
		
        return "index";
    }
	
	@GetMapping("/list")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("list");
		List<TestVO> list = Arrays.asList(new TestVO("한사람", 12, false),new TestVO("두사람", 18, true),new TestVO("세사람", 22, false));
		mv.addObject("list", list);
		return mv;
	}
}

package kr.human.ex02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("name", "한사람");
		model.addAttribute("age", 23);
		return "hello"; //확장자를 뺀 템플릿 파일의 이름을 리턴한다.
	}
	
}

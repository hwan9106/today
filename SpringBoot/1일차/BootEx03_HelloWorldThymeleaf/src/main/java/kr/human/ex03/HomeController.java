package kr.human.ex03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.human.ex03.vo.TestVO;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String index(Model model) {
		Map<String ,Object> map = new HashMap<>();
		map.put("name","한사람");
		map.put("age",22);
		map.put("gender","false");
		List<String> hobby = new ArrayList<>();
		hobby.add("술먹기");
		hobby.add("잠자기");
		hobby.add("책읽기");
		hobby.add("고성방가하기");
		
		TestVO VO = new TestVO("두사람",12,true);
		
		model.addAttribute("map",map);
		model.addAttribute("hobby",hobby);
		model.addAttribute("VO", VO);
		model.addAttribute("name", "세사람");
		return "index";
	}
}

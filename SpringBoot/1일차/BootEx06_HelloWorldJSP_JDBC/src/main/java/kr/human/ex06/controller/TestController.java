package kr.human.ex06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.human.ex06.service.TestService;

@Controller
public class TestController {
	
	@Autowired
	private TestService testService;

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("now", testService.selectNow());
		model.addAttribute("today", testService.selectToday());
		return "index";
	}
}

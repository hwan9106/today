package kr.human.memo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.human.memo.service.TestService;

@Controller
public class TestController {
	@Autowired
	private TestService testService;
	@RequestMapping("/today")
	public String today(Model model) {
		model.addAttribute("today", testService.today());
		return "today";
	}
}

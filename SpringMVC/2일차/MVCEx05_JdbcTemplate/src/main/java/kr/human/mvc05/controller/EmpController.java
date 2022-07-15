package kr.human.mvc05.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.human.mvc05.service.EmpService;
import kr.human.mvc05.vo.EmpVO;

@Controller
public class EmpController {

	@Autowired
	private EmpService empService;
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		try {
			model.addAttribute("totalCount", empService.selectCount());
			model.addAttribute("list", empService.selectList());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateGet(Model model) {
		return "redirect:/list";
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updatePost(@ModelAttribute EmpVO empVO, @RequestParam(defaultValue = "0") int mode) {
		try {
			switch (mode) {
			case 0:
				empService.insert(empVO);
				break;
			case 1:
				empService.update(empVO);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/list";
	}

	@RequestMapping(value = "/delete")
	public String delete(@RequestParam(defaultValue = "0") int idx) {
		try {
			empService.delete(idx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/list";
	}

}

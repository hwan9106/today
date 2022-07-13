package kr.human.memo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.human.memo.service.MemoService;
import kr.human.memo.vo.CommVO;
import kr.human.memo.vo.MemoVO;
import kr.human.memo.vo.PagingVO;

//@Controller
public class MemoController {

	@Autowired
	private MemoService memoService;
	
	// 목록보기
	@RequestMapping("/list")
	public String selectList(
			@RequestParam(required = false, defaultValue = "1") int p,
			@RequestParam(required = false, defaultValue = "3") int s,
			@RequestParam(required = false, defaultValue = "10") int b,
			Model model 
			){
		PagingVO<MemoVO> pagingVO = memoService.selectList(p, s, b);
		model.addAttribute("pv", pagingVO);
		model.addAttribute("p", p);
		model.addAttribute("s", s);
		model.addAttribute("b", b);
		model.addAttribute("br", "<br>");
		model.addAttribute("newLine", "\n");
		return "list";
	}
	
	// 저장/수정/삭제
	@RequestMapping(value = "/update", method= RequestMethod.GET)
	public String updateGet() {
		return "redirect:/list";
	}
	@RequestMapping(value = "/update", method= RequestMethod.POST)
	public String updatePost(@ModelAttribute CommVO commVO, @ModelAttribute MemoVO memoVO) {
		switch (commVO.getMode()) {
		case "insert":
			memoService.insert(memoVO);
			break;
		case "update":
			memoService.update(memoVO);
			break;
		case "delete":
			memoService.delete(memoVO);
			break;
		}

		return "redirect:/list";
	}
}
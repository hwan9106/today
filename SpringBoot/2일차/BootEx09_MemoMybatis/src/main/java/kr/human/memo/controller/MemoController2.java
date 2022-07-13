package kr.human.memo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import kr.human.memo.service.MemoService;
import kr.human.memo.vo.CommVO;
import kr.human.memo.vo.MemoVO;
import kr.human.memo.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemoController2 {

	@Autowired
	private MemoService memoService;
	
	// 목록보기
	@RequestMapping("/list")
	public String selectList(
			@RequestParam(required = false, defaultValue = "1") int p,
			@RequestParam(required = false, defaultValue = "3") int s,
			@RequestParam(required = false, defaultValue = "10") int b,
			HttpServletRequest request,
			Model model 
			){

		//  Redirect시 POST전송된 값을 받는다.
		//  Map<String, ?> ? 과 object 다름
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
	    if(flashMap != null){
	    	log.info("맵 : " + flashMap.toString());
			Map<String,Integer> map =(Map<String, Integer>) flashMap.get("map");
	    	log.info("맵 : " + map.toString());
	    	p = map.get("p");
	    	s = map.get("s");
	    	b = map.get("b");
	    	log.info("POST전송 값 받기 : " + p + ", " + s + ", " + b);
	    }

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
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateGet() {
		return "redirect:/list";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	// Redirect시 POST전송하기
	// 1. 파라메터로 RedirectAttributes를 받는다.
	public String updatePost(@ModelAttribute CommVO commVO, @ModelAttribute MemoVO memoVO, RedirectAttributes redirectAttributes) {
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
		// 2. 맵을 만든다.
		Map<String, Integer> map = new HashMap<String,Integer>();
		map.put("p", commVO.getP());
		map.put("s", commVO.getS());
		map.put("b", commVO.getB());
		// 3. 맵을 RedirectAttribute에 추가한다.
		redirectAttributes.addFlashAttribute("map", map);
		return "redirect:/list";
	}
}

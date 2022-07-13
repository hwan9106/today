package kr.human.memo.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.human.memo.dao.MemoDAO;
import kr.human.memo.service.TestService;
import kr.human.memo.vo.MemoVO;

@Controller
public class TestController {
	@Autowired
	private TestService testService;
	
	@Autowired
	private MemoDAO memoDAO;
	
	@RequestMapping("/today")
	public String today(Model model) {
		model.addAttribute("today", testService.today());
		return "today";
	}
	
	
	@RequestMapping(value = "/text", produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public String text() {
		return "이 내용이 뷰를 통하지 않고 그냥 출력된다."; 
	}

	@RequestMapping(value = "/json", produces = "application/json; charset=UTF-8")
	public 	@ResponseBody String json() {
		return "{\"message\":\"이 내용이 뷰를 통하지 않고 그냥 출력된다.\"}"; 
	}

	@RequestMapping(value = "/jsonList", produces = "application/json; charset=UTF-8")
	public 	@ResponseBody List<MemoVO> list() throws SQLException {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("startNo", 0);
		map.put("pageSize", 100);
		return memoDAO.selectList(map);
	}
}


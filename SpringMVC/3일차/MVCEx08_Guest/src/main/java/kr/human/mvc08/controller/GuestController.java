package kr.human.mvc08.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.human.mvc08.service.GuestService;
import kr.human.mvc08.vo.GuestVO;

@Controller
public class GuestController {

		@Autowired
		private GuestService guestService;
		
		// 목록보기
		@RequestMapping(value = "/list")
		public String selectList(Model model) {
			List<GuestVO> list = guestService.selectList();
			model.addAttribute("list", list);
			model.addAttribute("newLine", "\n");
			model.addAttribute("br", "<br/>");
			return "list";
		}
		// 원본글 저장하기
		@RequestMapping(value = "/insert", method= RequestMethod.GET)
		public String insertGet() {
			return "redirect:/list";
		}
		@RequestMapping(value = "/insert", method= RequestMethod.POST)
		public String insertPost(@ModelAttribute GuestVO guestVO, HttpServletRequest request ) {
			guestVO.setIp(request.getRemoteAddr());
			guestService.insert(guestVO);
			return "redirect:/list";
		}
		// 답변 저장하기
		@RequestMapping(value = "/reply", method= RequestMethod.GET)
		public String replyGet() {
			return "redirect:/list";
		}
		@RequestMapping(value = "/reply", method= RequestMethod.POST)
		public String replyPost(@ModelAttribute GuestVO guestVO, HttpServletRequest request ) {
			guestVO.setIp(request.getRemoteAddr());
			guestService.reply(guestVO);
			return "redirect:/list";
		}
		// 수정하기
		@RequestMapping(value = "/update", method= RequestMethod.GET)
		public String updateGet() {
			return "redirect:/list";
		}
		@RequestMapping(value = "/update", method= RequestMethod.POST)
		public String updatePost(@ModelAttribute GuestVO guestVO, HttpServletRequest request ) {
			guestVO.setIp(request.getRemoteAddr());
			guestService.update(guestVO);
			return "redirect:/list";
		}

		// 삭제하기
		@RequestMapping(value = "/delete", method= RequestMethod.GET)
		public String deleteGet() {
			return "redirect:/list";
		}
		@RequestMapping(value = "/delete", method= RequestMethod.POST)
		public String deletePost(@ModelAttribute GuestVO guestVO ) {
			guestService.delete(guestVO);
			return "redirect:/list";
		}
}

package kr.green.member.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.green.member.service.MemberService;
import kr.green.member.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value = "/insertForm")
	public String insertForm() {
		return "insertForm";
	}

	@RequestMapping(value = "/insertOk", method=RequestMethod.GET)
	public String insertOk() {
		return "redirect:/";
	}
	
	@RequestMapping(value = "/insertOk", method=RequestMethod.POST)
	public String insertOk(@ModelAttribute MemberVO memberVO, Model model) {
		memberVO.setCol1(UUID.randomUUID().toString());
		memberVO.setPassword(bCryptPasswordEncoder.encode(memberVO.getPassword())); // 비번 암호화
		memberService.insert(memberVO); // DB에 저장
		model.addAttribute("vo", memberVO);
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "/login")
	public String login(
			@RequestParam(value = "error", required = false) String error,
			Model model
			) {
		if(error!=null) {
			model.addAttribute("error", "아이디가 없거나 비번이 일치하지 않습니다.");
		}
		
		return "login";
	}
	
	
	@RequestMapping(value = "/idCheck", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String idCheck(@RequestParam(required = false) String userid) {
		String userids[] = "admin,root,master,webmaster,administrator".split(","); // 금지 아이디 목록
		int count = 0;
		for(String id : userids) { 
			if(userid.equals(id)) {
				count=1;
				break;
			}
		}
		if(count==0) {
			// 서비스를 호출하여 동일한 아이디의 개수를 얻어소 count변수에 넣는다.
			count = memberService.idCheck(userid);
		}
		return count + "";
	}
	// 이메일 인증처리
	@RequestMapping(value = "/confirm")
	public String confirm(@RequestParam String userid, @RequestParam String col1, Model model) {
		log.debug("confirm 호출 : " + userid + ", " + col1);
		MemberVO memberVO = memberService.updateUse(userid, col1); // use값을 1로 변경
		model.addAttribute("memberVO", memberVO);
		return "confirm";
	}
	
	@RequestMapping(value = "/findUserid")
	public String findUserid(@RequestParam(value = "error", required = false) String error,Model model) {
		if(error!=null) {
			model.addAttribute("error", "입력하신 정보의 아이디는 존재하지 않습니다.");
		}
		return "findUserid";
	}
	
	@RequestMapping(value = "/findUseridOk", method = RequestMethod.GET)
	public String findUseridOkGET() {
		return "findUserid";
	}
	
	@RequestMapping(value = "/findUseridOk", method = RequestMethod.POST)
	public String findUseridOkPOST(@ModelAttribute MemberVO memberVO, Model model) {
		MemberVO vo = memberService.idSearch(memberVO);
		if(vo==null) {
			// 일치하는 정보가 없다.
			return "redirect:findUserid?error";
		}else{
			// 일치하는 정보가 있다.
			model.addAttribute("vo", vo);
			return "findUseridOk";
		}
	}
	
	
	@RequestMapping(value = "/findPassword")
	public String findPassword(@RequestParam(value = "error", required = false) String error,Model model) {
		if(error!=null) {
			model.addAttribute("error", "입력하신 정보가 일치하지 않습니다.");
		}
		return "findPassword";
	}
	
	@RequestMapping(value = "/findPasswordOk", method = RequestMethod.GET)
	public String findPasswordOkGET() {
		return "findPassword";
	}
	
	@RequestMapping(value = "/findPasswordOk", method = RequestMethod.POST)
	public String findPasswordOkPOST(@ModelAttribute MemberVO memberVO, Model model) {
		MemberVO vo = memberService.passwordSearch(memberVO);
		if(vo==null) {
			// 일치하는 정보가 없다.
			return "redirect:findPassword?error";
		}else{
			// 일치하는 정보가 있다.
			// 임시비밀번호를 만들어서 DB에 저장하고
			String newPassword = memberService.makePassword(15);
			memberVO.setPassword(newPassword);
			memberService.updatePassword(memberVO);
			// 만들어진 임시비밀번호를 알려준다.
			model.addAttribute("vo", memberVO);
			return "findPasswordOk";
		}
	}
	
	// 비밀번호 변경하기 폼
	@RequestMapping(value = "/updatePasswordForm")
	public String updatePasswordForm(@RequestParam(value = "error", required = false) String error,Model model) {
		if(error!=null) {
			model.addAttribute("error", "입력하신 정보가 일치하지 않습니다.");
		}
		return "updatePasswordForm";
	}
	
	@RequestMapping(value = "/updatePasswordOk", method = RequestMethod.GET)
	public String updatePasswordOkGET() {
		return "updatePasswordForm";
	}
	
	@RequestMapping(value = "/updatePasswordOk", method = RequestMethod.POST)
	public String updatePasswordOkPOST(@ModelAttribute MemberVO memberVO,@RequestParam String newPassword, 
			                           HttpSession session, HttpServletRequest request, HttpServletResponse response ) {
		MemberVO vo = memberService.selectByUserId(memberVO);
		
		if(vo==null) {
			// 일치하는 정보가 없다.
			return "redirect:updatePasswordForm?error";
		}else{
			// 일치하는 정보가 있다.
			
			// 조건문을 써서 입력된 비밀 번호와 DB의 비밀번호가 일치 하는지를 추가 해야한다.
			if(bCryptPasswordEncoder.matches(memberVO.getPassword(), vo.getPassword())) {
				// 새로운 비밀번호를 DB에 저장하고
				memberVO.setPassword(newPassword);
				memberService.updatePassword(memberVO);
				
				// 현재 로그인된 세션 정보를 지우고
				session.removeAttribute("mvo");
				
				// 시크리트의 로그인 정보도 지워줘야 한다.
				// 인증정보를 얻어낸다.
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				// 인증정보가 있다면
				if(authentication!=null) {
					// 로그아웃을 시킨다.
					new SecurityContextLogoutHandler().logout(request, response, authentication);
				}
			
				// 로그인 화면으로 보내주자
				return "redirect:/login";
			}else {
				return "redirect:updatePasswordForm?error";
			}
		}
	}
	
	// 회원 탈퇴 폼
	@RequestMapping(value = "/deleteForm")
	public String deleteForm(@RequestParam(value = "error", required = false) String error,Model model) {
		if(error!=null) {
			model.addAttribute("error", "입력하신 정보가 일치하지 않습니다.");
		}
		return "deleteForm";
	}

	@RequestMapping(value = "/deleteOk", method = RequestMethod.GET)
	public String deleteOkGET() {
		return "deleteForm";
	}
	
	@RequestMapping(value = "/deleteOk", method = RequestMethod.POST)
	public String deleteOkPOST(@ModelAttribute MemberVO memberVO,HttpSession session, HttpServletRequest request, HttpServletResponse response ) {
		MemberVO vo = memberService.selectByUserId(memberVO);
		if(vo==null) {
			// 일치하는 정보가 없다.
			return "redirect:deleteForm?error";
		}else{
			// 일치하는 정보가 있다.
			// DB에서 삭제하고
			memberService.delete(memberVO);
			
			// 현재 로그인된 세션 정보를 지우고
			session.removeAttribute("mvo");
			
			// 시크리트의 로그인 정보도 지워줘야 한다.
			// 인증정보를 얻어낸다.
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			// 인증정보가 있다면
			if(authentication!=null) {
				// 로그아웃을 시킨다.
				new SecurityContextLogoutHandler().logout(request, response, authentication);
			}
			
			// 홈화면으로 보내주자
			return "redirect:/";
		}
	}
	
	// 회원 정보 수정 폼
	@RequestMapping(value = "/updateForm")
	public String updateForm() {
		// 게시판과 다르게 글번호를 받지도 않고 수정할 글을 DB에서 가져오지도 않는다. 
		// 왜? 이미 회원의 모든 정보는 세션에 정장되어있다. 
		return "updateForm";
	}
	
	@RequestMapping(value = "/updateOk", method=RequestMethod.GET)
	public String updateOk() {
		return "redirect:/";
	}
	
	@RequestMapping(value = "/updateOk", method=RequestMethod.POST)
	public String updateOk(@ModelAttribute MemberVO memberVO, HttpSession session, Model model) {
		MemberVO dbVO = memberService.update(memberVO); // DB에 수정
		if(dbVO!=null) {
			model.addAttribute("msg", "수정성공!!!!");
			session.setAttribute("mvo", dbVO); // 수정된 값으로 세션정보 변경
		}else {
			model.addAttribute("msg", "수정실패!!!!");
		}
		return "updateOk";
	}	
	
}

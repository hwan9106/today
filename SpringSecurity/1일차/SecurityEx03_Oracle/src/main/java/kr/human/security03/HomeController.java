package kr.human.security03;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.human.security03.service.TestService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private TestService testService;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("user", getPrincipal());
		model.addAttribute("today", testService.selectToday());
		return "home";
	}

	@RequestMapping(value = "/home")
	public String homePage(Model model) {
		model.addAttribute("msg", "누구나 접근 가능한 페이지 입니다.");
		model.addAttribute("user", getPrincipal());
		return "welcome";
	}

	// 권한이 없는 페이지에 접근했을때 보여줄 페이지 맵핑
	@RequestMapping(value = "/403")
	public String page403(Model model) {
		String userName = null;
		SecurityContext context = SecurityContextHolder.getContext();
		Object principal = context.getAuthentication().getPrincipal();
		System.out.println(principal);
		if (principal instanceof UserDetails) {
			System.out.println((UserDetails) principal);
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		model.addAttribute("username", userName);
		return "403";
	}

	@RequestMapping(value = { "/admin", "/admin2" })
	public String admin(Model model) {
		model.addAttribute("msg", "관리자 전용 페이지 입니다.");
		model.addAttribute("user", getPrincipal());
		return "admin";
	}

	@RequestMapping(value = { "/dba", "/dba2" })
	public String dba(Model model) {
		model.addAttribute("msg", "DB관리자 전용페이지 입니다.");
		model.addAttribute("user", getPrincipal());
		return "dba";
	}
	/*
	@RequestMapping(value = "/Access_Denied")
	public String access_Denied(Model model) {
		model.addAttribute("msg", "등록되지 않은 사용자이거나 비밀번호가 일치하지 않습니다.");
		return "access_Denied";
	}
	*/
	@RequestMapping(value = "/login")
	public String loginPage(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			Model model
	) {
			if(error!=null) {
				model.addAttribute("error","아이디가 없거나 비번이 일치하지 않습니다.");
			}
			if(logout!=null) {
				model.addAttribute("msg","성공적으로 로그아웃을 했습니다.");
			}
		return "login";
	}
	/*
	@RequestMapping(value = "/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}
	*/
	// 인증 정보를 시큐리트에서 얻어내는 메서드
	private String getPrincipal() {
		String userName = null;
		SecurityContext context = SecurityContextHolder.getContext();
		System.out.println(context);
		System.out.println(context.getAuthentication());
		Object principal = context.getAuthentication().getPrincipal();
		System.out.println(principal);
		if (principal instanceof UserDetails) {
			System.out.println((UserDetails) principal);
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	//평문을 암호화해서 출력해보기
	@RequestMapping(value="/getPassword")
	public String getPassword(@RequestParam(value="pwd",defaultValue = "admin", required = false)
																	String password, Model model) {
		model.addAttribute("password",password);
		model.addAttribute("newPassword",bCryptPasswordEncoder.encode(password));
		return "bcrypt";
	}
	
	//암호화된 암호의 일치여부 판단하기
	@RequestMapping(value="/checkPwd")
	public String passwordCheck(@RequestParam(value="pwd",defaultValue = "admin", required = false)
																	  String password, Model model) {
		String newPassword =bCryptPasswordEncoder.encode(password);
		String dbPassword =bCryptPasswordEncoder.encode(password);
		
		boolean isCheck1 = newPassword.equals(dbPassword);
		//서큐리티에선 비밀번호 확인을 bCryptPasswordEncoder.matches 이걸로 해야한다.
		boolean isCheck2 = bCryptPasswordEncoder.matches(password, dbPassword); //이렇게 비교해야 한다
		
		model.addAttribute("isCheck1", isCheck1);
		model.addAttribute("isCheck2", isCheck2);
		model.addAttribute("newPassword", newPassword);
		model.addAttribute("dbPassword", dbPassword);
		
		return "bcrypt2";
	}
}

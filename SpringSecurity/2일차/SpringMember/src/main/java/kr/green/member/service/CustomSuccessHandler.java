package kr.green.member.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import kr.green.member.dao.MemberDAO;
import kr.green.member.vo.MemberVO;

// 로그인 성공시 추가적으로 처리할 내용을 여기에 적는다.
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	//리다이렉트되는 주소의 앞에 자동으로 ContextPath를 붙여주고 인코딩 까지 처리해준다.
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
	@Autowired
	MemberDAO memberDAO;
	
    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
      // 로그인 성공시 처리할 내용을 여기에 적는다.
      // 회원 정보를 세션에 저장
      String userid = "";
      // 인증정보에서 사용자 아이디를 받아온다.
      Object principal = authentication.getPrincipal();
      if (principal instanceof UserDetails) {
    	  userid = ((UserDetails)principal).getUsername();
      } else {
    	  userid = principal.toString();
      }
      // DB에서  회원 정보를 얻어온다.
      MemberVO memberVO = memberDAO.selectUserId(userid);
      
      //System.out.println(authentication.getPrincipal());
      //System.out.println(userid);
      //System.out.println(memberVO);
      // 얻어온 회원 정보를 세션에 저장하고 홈으로 이동한다. 
      HttpSession session = request.getSession();
      session.setAttribute("mvo", memberVO);
      //세번째 인수에 내 사이트의 주소를 /로 부터 써주면 완성된다.
      redirectStrategy.sendRedirect(request, response, "/");
    }
}

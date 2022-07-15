package kr.human.mvc03.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.human.mvc03.vo.CommVO;
import kr.human.mvc03.vo.MemoVO;
import kr.human.mvc03.vo.TestVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MappingController {

	// jsp파일이름을 리턴하게 만들어 준다.
	// @RequestMapping은 주소를 한 생성한다.
	@RequestMapping(value = "/m01", method = RequestMethod.GET)
	// value값이 주소가 되고 method가 전송방식을(생략가능) 지정한다.
	//@GetMapping : GET방식만 처리
	//@PostMapping : POST방식만 처리
	public String m01() {
		log.info("/m01 {}", "호출");
		return "m01"; // jsp파일의 이름만 리턴한다. /WEB-INF/views/리턴값.jsp가 보인다.
	}
	
	// 2. 하나의 메서드로 여러개의 주소 만들기
	@RequestMapping(value = {"/m02","/m02.it", "/admin/m02.human"}) // 주소값을 배열로 지정 가능하다.
	public String m02() {
		log.info("/m02 {}", "호출");
		return "m02"; // jsp파일의 이름만 리턴한다. /WEB-INF/views/리턴값.jsp가 보인다.
	}
	
	// 3. JSP를 통과하지 않고 바로 출력하는 주소 만들기
	@RequestMapping(value = "/m03") // 한글 깨짐!!!
	@ResponseBody
	public String m03() {
		log.info("/m03 {}", "호출");
		return "한글qwerty1234!@#$";
	}
	@RequestMapping(value = "/m04", produces = "text/plain;charset=UTF-8") // 한글 나옴!!!
	@ResponseBody
	public String m04() {
		log.info("/m04 {}", "호출");
		return "한글qwerty1234!@#$";
	}
	
	// 4. GET/POST 구분해서 처리하기
	@RequestMapping(value = "/form")
	public String form1() { // 그냥 폼을 띄운다.
		return "form1";
	}
	
	@RequestMapping(value = "/m05", method = RequestMethod.GET)
	public String m05(@RequestParam String name, Model model) { // JSP로 무언가를 넘겨주려면 Model 파라메터가 필요하다.
		log.info("/m05 GET GET GET {} : {}", "호출",name);
		model.addAttribute("name", name);
		return "result1"; 
	}
	
	@RequestMapping(value = "/m05", method = RequestMethod.POST)
	public ModelAndView m06(@RequestParam String name) {
		log.info("/m06 POST POST POST {} : {}", "호출", name);
		// JSP로 무언가를 넘겨주려면 ModelAndView를 이용 가능하다.
		ModelAndView mv = new ModelAndView("result1");
		mv.addObject("name", name);
		return mv; 
	}
	
	// 5. Path를 이용하여 데이터 받아보자
	@RequestMapping(value = "/name/{id}", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String path01(@PathVariable int id) {
		return "<h1>Path를 이용하여 데이터를 받아보자. 받은값 : " + id + "</h1>";
	}
	
	@RequestMapping(value = "/{name}/{no}", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String path02(@PathVariable String name, @PathVariable(required = false, name = "no") Integer age) {
		return "<h1>" + name  + "님 " + age +  "살이네 행님이라 불러</h1>";
	}
	
	@RequestMapping(value = "/list/{idx}/{p}/{s}/{b}", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String path03( 
			@PathVariable Integer idx, 
			@PathVariable Integer p, 
			@PathVariable Integer s, 
			@PathVariable Integer b 
			) {
		return "<h1>" + idx  + "번글 " + p + ", " + s + ", " + b + "</h1>";
	}
	
	// 6. request로 받아보기
	@RequestMapping(value = "/form2")
	public String form2() { // 그냥 폼을 띄운다.
		return "form2";
	}
	
	@RequestMapping(value = "/get1", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String get1(HttpServletRequest request) {
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		return "<h1>" + name  + "님 " + age +  "살이네 행님이라 불러</h1>";
	}
	
	@RequestMapping(value = "/get2", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String get2(@RequestParam String name, @RequestParam int age) {
		return "<h1>" + name  + "님 " + age +  "살이네 행님이라 불러</h1>";
	}
	
	@RequestMapping(value = "/get2", produces = "text/html;charset=UTF-8", method = RequestMethod.GET)
	public String get3(@RequestParam String name, @RequestParam int age) {
		return "redirect:/form2";
	}
	
	@RequestMapping(value = "/get3", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String get4(@RequestParam(defaultValue="무명", required=false) String name,
					   @RequestParam(defaultValue="18", required=false) int age) {
		return "<h1>" + name  + "님 내년 나이가" + (age+1) +  "살이네요</h1>";
	}
	
	// 7. session 사용하기
	@RequestMapping(value = "/session")
	public String session(@RequestParam(defaultValue="무명", required=false) String name,
					   @RequestParam(defaultValue="18", required=false) int age,
					   HttpSession session,
					   HttpServletRequest request
					   ) {
		session.setAttribute("name", "세션" + name);
		session.setAttribute("age", "세션" + age);

		request.setAttribute("name", "리퀘스트" + name);
		request.setAttribute("age", "리퀘스트" + age);
		
		return "session";
	}
	// 8. VO로 받기
	@RequestMapping(value = "/vo")
	public String vo(@ModelAttribute TestVO vo, HttpSession session){
		session.setAttribute("vo", vo);
		return "vo";
	}	
	
	@RequestMapping(value = "/board")
	@ResponseBody
	public String board( // 왕왕짜증이다.
			@RequestParam(defaultValue = "1", required = false) int p,
			@RequestParam(defaultValue = "10", required = false) int s,
			@RequestParam(defaultValue = "10", required = false) int b,
			@RequestParam(defaultValue = "-1", required = false) int idx
			) {
		return "<h1>" + p + ", " + b + ", " + s +", " + idx + "</h1>";
	}
	
	@RequestMapping(value = "/board2")
	@ResponseBody
	public String board2(@ModelAttribute CommVO cv) { // VO로 받는다.
		return "<h1>" + cv.getP() + ", " + cv.getB() + ", " + cv.getS() +", " + cv.getIdx() + "</h1>"
		 + "<h2>" + cv.getCurrentPage() + ", " + cv.getBlockSize() + ", " + cv.getPageSize() +", " + cv.getIdx() + "</h2>";
	}
	
	@RequestMapping(value = "/memoForm")
	public String memoForm() {
		return "memoForm";
	}
	
	@RequestMapping(value = "/insertOk", method = RequestMethod.GET)
	public String memoGet() {
		return "redirect:/memoForm"; // 무작정 이동!!!
	}

	@RequestMapping(value = "/insertOk", method = RequestMethod.POST)
	public String memoPost(@ModelAttribute CommVO cv, @ModelAttribute MemoVO memoVO, Model model) {
		model.addAttribute("cv", cv);
		model.addAttribute("mv", memoVO);
		return "insertOk"; 
	}
	
	@RequestMapping(value = "/mapForm")
	public String mapForm() {
		return  "mapForm";
	}
	// 맵으로 받기
	@RequestMapping(value = "/result")
	public String map(@RequestParam Map<String, String> map, @RequestParam(required = false) String[] d, Model model) {
		model.addAttribute("map", map);
		model.addAttribute("d", d);
		return "result";
	}
	
}

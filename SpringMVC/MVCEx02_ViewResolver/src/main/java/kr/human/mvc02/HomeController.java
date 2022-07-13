package kr.human.mvc02;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.human.mvc02.vo.Pizza;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		return "home";
	}
	// JSP로 출력하겠다.
	@RequestMapping(value = "/pizza")
	public String getPizza(Model model) {
		model.addAttribute("pizza", new Pizza("고구마"));
		return "pizza";
	}

	@RequestMapping("/Pizza")
	@ResponseBody // JSP를 통하지 않고 직접 출력하겠다.
	public Pizza getPizza() {
		return new Pizza("고구마");
	}
	
	@RequestMapping(value = "/Pizza.xml", produces = {MediaType.APPLICATION_XML_VALUE})
	@ResponseBody // JSP를 통하지 않고 직접 출력하겠다.
	public Pizza getPizza2() {
		return new Pizza("고구마");
	}
	
	@RequestMapping(value = "/Pizza.json", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody // JSP를 통하지 않고 직접 출력하겠다.
	public Pizza getPizza3() {
		return new Pizza("고구마");
	}
	
	
}

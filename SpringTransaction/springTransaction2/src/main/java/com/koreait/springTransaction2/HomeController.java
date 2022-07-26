package com.koreait.springTransaction2;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.koreait.dao.TransactionDAO;
import com.koreait.vo.CardVO;

@Controller
public class HomeController {
	
	private TransactionDAO dao;
	
	@Autowired
	public void setDao(TransactionDAO dao) {
		this.dao = dao;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/ticket")
	public String ticket() {
		System.out.println("컨트롤러의 ticket() 메소드 실행");
		return "ticket";
	}
	
	@RequestMapping("/ticketCard")
	public String ticketCard(CardVO cardVO, Model model) {
		System.out.println("컨트롤러의 ticketCard() 메소드 실행");
		System.out.println("consumerId : " + cardVO.getConsumerId());
		System.out.println("amount : " + cardVO.getAmount());

		dao.buyTicket(cardVO);
		model.addAttribute("ticketInfo", cardVO);
		
		return "ticketEnd";
	}
	
}

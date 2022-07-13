package kr.human.mvc02.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.human.mvc02.service.RssService;
import kr.human.mvc02.vo.PersonVO;
import kr.human.mvc02.vo.Pizza;
import kr.human.mvc02.vo.Rss;

@RestController
public class MyController {
	
	@Autowired
	private RssService rssService;

	@RequestMapping(value = "/rest/Pizza", produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
	public Pizza getPizza() {
		return new Pizza("고구마");
	}
	
	@RequestMapping(value = "/rest/Pizza.xml")
	public Pizza getPizza2() {
		return new Pizza("고구마");
	}
	
	@RequestMapping(value = "/rest/Pizza.json", produces = {MediaType.APPLICATION_JSON_VALUE})
	public Pizza getPizza3() {
		return new Pizza("고구마");
	}
	
	@RequestMapping(value = "/rest/hello", produces = "text/plain; charset=UTF-8")
	public String getHello() {
		return "안녕하세요!!!";
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/rest/person")
	public PersonVO getPerson1() {
		return new PersonVO("한사람", 22, false, new Date(122, 7, 13));
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/rest/person.xml", produces = {MediaType.APPLICATION_XML_VALUE})
	public PersonVO getPerson2() {
		return new PersonVO("한사람", 22, false, new Date(122, 7, 13));
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/rest/person.json", produces = {MediaType.APPLICATION_JSON_VALUE})
	public PersonVO getPerson3() {
		return new PersonVO("한사람", 22, false, new Date(122, 7, 13));
	}

	@RequestMapping(value = "/rest/rss901.json", produces = {MediaType.APPLICATION_JSON_VALUE})
	public Rss getRss901() {
		return rssService.readRss("https://rss.etnews.com/Section901.xml");
	}
	
	@RequestMapping(value = "/rest/rss902.json", produces = {MediaType.APPLICATION_JSON_VALUE})
	public Rss getRss902() {
		return rssService.readRss("https://rss.etnews.com/Section902.xml");
	}
	
}

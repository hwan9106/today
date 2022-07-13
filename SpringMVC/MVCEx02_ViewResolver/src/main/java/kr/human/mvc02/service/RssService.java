package kr.human.mvc02.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Service;

import kr.human.mvc02.vo.Rss;

@Service
public class RssService {

	public Rss readRss(String urlAddress) {
		Rss rssVO = null;
		try {
			JAXBContext context = JAXBContext.newInstance(Rss.class);
			URL url = new URL(urlAddress);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			rssVO = (Rss) unmarshaller.unmarshal(new InputStreamReader(url.openStream(),"UTF-8"));
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rssVO;
	}
}

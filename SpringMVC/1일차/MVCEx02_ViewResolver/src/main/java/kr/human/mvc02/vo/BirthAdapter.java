package kr.human.mvc02.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class BirthAdapter extends XmlAdapter<String, Date>{

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일(E) hh:mm:ss ");
	
	@Override
	public Date unmarshal(String v) throws Exception {
		return sdf.parse(v);
	}

	@Override
	public String marshal(Date v) throws Exception {
		return sdf.format(v);
	}

}

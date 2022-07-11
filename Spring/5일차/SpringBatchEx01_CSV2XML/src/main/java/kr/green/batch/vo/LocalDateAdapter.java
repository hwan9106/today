package kr.green.batch.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.time.LocalDate;
//XML에 저장되어있는 문자열을 자바 객체로 변환할때 처리할 어댑터 클래스
//unmarshal XML의 Text 데이터를 java object 로 변환
//marshal java object를 XML의 Text데이터로 변환
public class LocalDateAdapter extends XmlAdapter<String, LocalDate>{

	@Override
	public LocalDate unmarshal(String v) throws Exception {
		return new LocalDate(v);
	}

	@Override
	public String marshal(LocalDate v) throws Exception {
		return v.toString();
	}

}
//성별을 boolean으로 저장 했다면 문자로 바꿔주는 어댑터를 만들어 사용한다.
class GenderAdapter extends XmlAdapter<String, Boolean>{

	@Override
	public Boolean unmarshal(String v) throws Exception {
		
		return v.equals("남자");
	}

	@Override
	public String marshal(Boolean v) throws Exception {
		
		return v ? "남자" : "여자";
	}
	
}
/*
class DataAdapter extends XmlAdapter<String, Date>{

	@Override
	public Date unmarshal(String v) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 mm월 dd일");
		return sdf.parse(v);
	}

	@Override
	public String marshal(Date v) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 mm월 dd일");
		return sdf.format(v);
	}
	
}
*/
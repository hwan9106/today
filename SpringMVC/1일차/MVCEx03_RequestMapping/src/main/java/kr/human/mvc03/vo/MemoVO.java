package kr.human.mvc03.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlRootElement
public class MemoVO {
	private int idx;
	private String name;
	private String password;
	private String content;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date   regDate;
	private String ip;
}

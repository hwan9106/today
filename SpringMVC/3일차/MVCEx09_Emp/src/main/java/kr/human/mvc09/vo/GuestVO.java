package kr.human.mvc09.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
public class GuestVO {
	private int idx;
	private int ref;
	private int seq;
	private int lev;
	private String name;
	private String password;
	private String content;
	private Date   regDate;
	private String ip;
	private String del;
}

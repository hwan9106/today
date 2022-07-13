package kr.human.memo.vo;

import java.util.Date;

import lombok.Data;

@Data
public class MemoVO {
	private int idx;
	private String name;
	private String password;
	private String content;
	private Date regDate;
	private String ip;

}

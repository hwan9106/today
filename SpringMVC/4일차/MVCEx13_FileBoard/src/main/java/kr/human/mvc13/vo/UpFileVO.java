package kr.human.mvc13.vo;

import lombok.Data;

@Data
public class UpFileVO {
	private int idx;
	private int ref;
	private String saveFileName;
	private String originalFileName;
}

package kr.green.batch.vo;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlRootElement //JAXB
@XmlType(propOrder = {"idx","h","k","m"}) //필드 순서
public class HanjaVO {
	private int idx;
	private String h;
	private String k;
	private String m;
}

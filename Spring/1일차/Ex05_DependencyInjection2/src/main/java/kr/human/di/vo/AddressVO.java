package kr.human.di.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressVO {
	//타입이 모두 같은 필드인 경우
	private String city;
	private String state;
	private String country;
}

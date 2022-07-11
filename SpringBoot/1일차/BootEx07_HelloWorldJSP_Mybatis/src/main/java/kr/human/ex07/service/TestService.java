package kr.human.ex07.service;

import kr.human.ex07.vo.TestVO;

public interface TestService {
	String today();
	int    sum(int num1, int num2);
	int    mul(int num1, int num2);
	TestVO vo(int num1, int num2);
}


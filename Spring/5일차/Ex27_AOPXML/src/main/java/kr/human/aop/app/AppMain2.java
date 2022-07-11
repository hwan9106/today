package kr.human.aop.app;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.human.aop.service.EmployeeService;
import kr.human.aop.vo.EmployeeVO;

public class AppMain2 {
	public static void main(String[] args) {
		AbstractApplicationContext context =
				new ClassPathXmlApplicationContext("bean2.xml");
		
		EmployeeService employeeService = context.getBean("employeeService",EmployeeService.class);
		
		EmployeeVO employeeVO = employeeService.selectById(3);
		System.out.println(employeeVO);
		System.out.println("=".repeat(80));
		
		List<EmployeeVO> list = employeeService.selectList();
		System.out.println(list);
		System.out.println("=".repeat(80));
		context.close();
	}
}

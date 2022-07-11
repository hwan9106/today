package kr.human.aop.app;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.human.aop.service.EmployeeService;
import kr.human.aop.vo.EmployeeVO;

public class AppMain3 {
	public static void main(String[] args) {
		AbstractApplicationContext context =
				new ClassPathXmlApplicationContext("bean3.xml");
		
		EmployeeService employeeService = context.getBean("employeeService",EmployeeService.class);
		
		EmployeeVO employeeVO = employeeService.selectById(1);
		System.out.println(employeeVO);
		System.out.println("=".repeat(80));
		
		List<EmployeeVO> list = employeeService.selectList();
		System.out.println(list);
		System.out.println("=".repeat(80));
		
		employeeService.insertEmployee(null);
		context.close();
	}
}

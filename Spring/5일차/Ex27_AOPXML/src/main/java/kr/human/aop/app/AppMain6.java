package kr.human.aop.app;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.human.aop.service.EmployeeService;
import kr.human.aop.vo.EmployeeVO;

public class AppMain6 {
	public static void main(String[] args) {
		AbstractApplicationContext context =
				new ClassPathXmlApplicationContext("bean6.xml");
		
		EmployeeService employeeService = context.getBean("employeeService",EmployeeService.class);
		
		EmployeeVO employeeVO = employeeService.selectById(1);
		System.out.println(employeeVO);
		System.out.println("=".repeat(80));
		
		List<EmployeeVO> list = employeeService.selectList();
		System.out.println(list);
		System.out.println("=".repeat(80));
		
		employeeService.insertEmployee(null);
		
		try {
			employeeService.deleteEmployee(2);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		context.close();
	}
}

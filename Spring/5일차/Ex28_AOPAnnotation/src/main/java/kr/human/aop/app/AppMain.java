package kr.human.aop.app;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import kr.human.aop.config.AppConfig;
import kr.human.aop.service.EmployeeService;
import kr.human.aop.vo.EmployeeVO;

public class AppMain {
	public static void main(String[] args) {
		AbstractApplicationContext context =
				new AnnotationConfigApplicationContext(AppConfig.class);
		
		EmployeeService employeeService = context.getBean("employeeService",EmployeeService.class);
		
		EmployeeVO employeeVO = employeeService.selectById(1);
		System.out.println(employeeVO);
		System.out.println("=".repeat(80));
		
		List<EmployeeVO> list = employeeService.selectList();
		System.out.println(list);
		System.out.println("=".repeat(80));
		context.close();
	}
}

package kr.human.di.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.human.di.config.AppConfig;
import kr.human.di.service.EmployeeService;
import kr.human.di.vo.Employee;

public class AppMain2 {
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("AppConfig.xml");
		EmployeeService service = (EmployeeService) context.getBean("employeeService");
		Employee employee = new Employee();
        employee.setName("Danny Theys");
        service.registerEmployee(employee);
         
        context.close();
	} 
}

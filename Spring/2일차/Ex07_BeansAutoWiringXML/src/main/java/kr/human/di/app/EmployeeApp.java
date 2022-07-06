package kr.human.di.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.human.di.vo.Employee;

public class EmployeeApp {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("Employee.xml");
		
		Employee employee1 = context.getBean("emp1",Employee.class);
		System.out.println(employee1);
		
		Employee employee2 = context.getBean("emp2",Employee.class);
		System.out.println(employee2);
		
		context.close();
	}
}

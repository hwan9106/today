package kr.human.di.dao;

import org.springframework.stereotype.Repository;

import kr.human.di.vo.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao{

	@Override
	public void saveInDatabase(Employee employee) {
		System.out.println(employee + "저장 성공");
		
	}
	
}

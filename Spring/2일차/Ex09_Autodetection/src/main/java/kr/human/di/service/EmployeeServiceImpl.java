package kr.human.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.di.dao.EmployeeDao;
import kr.human.di.vo.Employee;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private DateService dateService;
	
	@Override
	public void registerEmployee(Employee employee) {
		employee.setAssessmentDate(dateService.getNextAssessmentDate());
		employeeDao.saveInDatabase(employee);
		System.out.println("서비스" + employee + "저장!!");
		
	}

}

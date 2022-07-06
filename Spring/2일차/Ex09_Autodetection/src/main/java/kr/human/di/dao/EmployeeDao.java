package kr.human.di.dao;

import kr.human.di.vo.Employee;

public interface EmployeeDao {	 
    void saveInDatabase(Employee employee);
}
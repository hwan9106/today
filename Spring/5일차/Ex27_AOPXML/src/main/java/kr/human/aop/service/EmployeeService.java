package kr.human.aop.service;

import java.util.List;

import kr.human.aop.vo.EmployeeVO;

public interface EmployeeService {
	// 1. 모두 얻기
	public List<EmployeeVO> selectList();
	// 2. 1개 얻기
	public EmployeeVO selectById(int employeeId);
	public EmployeeVO selectByName(String employeeName);
	// 3. 저장하기
	public void insertEmployee(EmployeeVO employeeVO);
	// 4. 수정하기
	public void updateEmployee(EmployeeVO employeeVO);
	// 5. 삭제하기
	public void deleteEmployee(EmployeeVO employeeVO);
	public void deleteEmployee(int employeeId) throws Exception;
}

package kr.human.aop.service;

import java.util.ArrayList;
import java.util.List;

import kr.human.aop.vo.EmployeeVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public List<EmployeeVO> selectList() {
		log.debug("selectList 호출");
		List<EmployeeVO> list = new ArrayList<>();
		list.add(new EmployeeVO(1, "한사람"));
		list.add(new EmployeeVO(2, "한사장"));
		list.add(new EmployeeVO(3, "한전무"));
		list.add(new EmployeeVO(4, "한상무"));
		list.add(new EmployeeVO(5, "한사원"));
		return list;
	}

	@Override
	public EmployeeVO selectById(int employeeId) {
		log.debug("selectById 호출");
		return new EmployeeVO(employeeId, "나사원");
	}

	@Override
	public EmployeeVO selectByName(String employeeName) {
		log.debug("selectByName 호출");
		return new EmployeeVO(6, employeeName);
	}

	@Override
	public void insertEmployee(EmployeeVO employeeVO) {
		log.debug("insertEmployee 호출");
	}

	@Override
	public void updateEmployee(EmployeeVO employeeVO) {
		log.debug("updateEmployee 호출");
	}

	@Override
	public void deleteEmployee(EmployeeVO employeeVO) {
		log.debug("deleteEmployee 호출");
	}

	@Override
	public void deleteEmployee(int employeeId)  throws Exception{
		throw new Exception("지정한 사번의 사원이 존재하지 않습니다.");
	}

}

package kr.human.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class EmployeeAspect {
	
	
	private long startTime, endTime;
	
	//EmployeeServiceImpl 클래스의 메서드이름이 selectById이면 메서드 호출 전에 적용
	@Before("execution(* kr.human.aop.service.EmployeeServiceImpl.selectById(..))")
	public void beforeLog(JoinPoint joinPoint) {
		startTime = System.currentTimeMillis();
		System.out.println("EmployeeAspect.beforeLog() 실행 : " + joinPoint.getSignature().getName());
				
	}
	//EmployeeServiceImpl 클래스의 모든메서드 호출 후에 적용
	@After("execution(* kr.human.aop.service.EmployeeServiceImpl.*(..))")
	public void afterLog(JoinPoint joinPoint) {
		endTime = System.currentTimeMillis();
		System.out.println("EmployeeAspect.afterLog() 실행 : " + joinPoint.getSignature().getName());
		System.out.println( joinPoint.getSignature().getName() + "의 실행시간 : " + (endTime-startTime) + "ms");
				
	}
	public void afterReturningLog(Object returnValue) {
		System.out.println("EmployeeAspect.afterReturningLog() 실행 : " + returnValue);
	}
	public void afterThrowingLog(Exception exception) {
		System.out.println("EmployeeAspect.afterThrowingLog() 실행 : " + exception);
	}
	public Object aroundAllLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
	      System.out.println("EmployeeAspect.aroundAllLog() 선행 실행");
	      // 반드시 다음으로 연결시켜 줘야 한다.
	      Object object = proceedingJoinPoint.proceed();
	      System.out.println("EmployeeAspect.aroundAllLog() 후행 실행");
	      return object;
	   }
	//EmployeeServiceImpl 클래스의 모든메서드 호출 전후에 적용 : 실행시산을 로그로 출력해준다.
	@Around("execution(* kr.human.aop.service.EmployeeServiceImpl.*(..))")
	public Object aroundTimeLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
	      long start = System.currentTimeMillis();
	      Object object = proceedingJoinPoint.proceed();
	      System.out.println(proceedingJoinPoint.getSignature().getName() + "메서드 실행시간 : " + (System.currentTimeMillis()-start) + "ms");
	      return object;
	}
}

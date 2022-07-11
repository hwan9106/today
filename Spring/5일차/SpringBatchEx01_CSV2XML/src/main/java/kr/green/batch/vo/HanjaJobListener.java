package kr.green.batch.vo;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class HanjaJobListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		//작업 시작 전처리
		System.out.println("천자문 변환처리를 시작합니다.");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		// 작업 시작 후처리
		System.out.println("천자문 변환처리를 종료합니다.");
	}

}

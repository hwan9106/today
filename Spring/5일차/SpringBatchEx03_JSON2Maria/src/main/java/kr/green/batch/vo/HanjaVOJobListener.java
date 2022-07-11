package kr.green.batch.vo;

import org.joda.time.DateTime;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

// 처리될때 감시자
public class HanjaVOJobListener implements JobExecutionListener {
	private DateTime startTime, stopTime;
	// 처리전에 실행할 내용
	@Override
	public void beforeJob(JobExecution jobExecution) {
		startTime = new DateTime();
		System.out.println("일을 시작합니다...... : " + startTime);
		
	}
	// 처리후에 실행할 내용
	@Override
	public void afterJob(JobExecution jobExecution) {
		stopTime = new DateTime();
		System.out.println("일을 종료합니다...... : " + stopTime);
		System.out.println("경과시간 : " + (stopTime.getMillis()-startTime.getMillis()) + "ms");
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("성공적으로 완료했습니다.");
		}else if(jobExecution.getStatus()== BatchStatus.FAILED) {
			System.out.println("실패했습니다.");
		}
	}
}

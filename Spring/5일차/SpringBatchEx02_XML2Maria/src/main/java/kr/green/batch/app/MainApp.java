package kr.green.batch.app;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("batch_config.xml");
		// 실행기 찾기
		JobLauncher jobLauncher = context.getBean("jobLauncher", JobLauncher.class);
		// 일 찾기
		Job job = context.getBean("examResultJob", Job.class);
		
		try {
			// 일 실행
			jobLauncher.run(job, new JobParameters());
			
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			System.out.println("배치 실패!!!!");
			e.printStackTrace();
		}
		context.close();
	}
}

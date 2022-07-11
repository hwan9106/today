package kr.green.batch.vo;

import org.springframework.batch.item.ItemProcessor;

// 1개의 아이템을 처리할때마다 실행될 프로세서
public class ExamResultItemProcessor implements ItemProcessor<ExamResult, ExamResult>{

	@Override
	public ExamResult process(ExamResult item) throws Exception {
		//if(item.getPercentage()<60) return null; // percentage 60 미만이면 처리하지 않겠다.
		System.out.println(item + "을 처리함");
		return item;
	}

}

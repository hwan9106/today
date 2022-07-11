package kr.green.batch.vo;

import org.springframework.batch.item.ItemProcessor;

public class HanjaItemProcessor implements ItemProcessor<HanjaVO, HanjaVO>{

	@Override
	public HanjaVO process(HanjaVO item) throws Exception {
		System.out.println("읽은 한자 : " + item);
		return item;
	}

}

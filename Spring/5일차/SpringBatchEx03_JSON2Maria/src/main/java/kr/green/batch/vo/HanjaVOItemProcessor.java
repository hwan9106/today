package kr.green.batch.vo;

import org.springframework.batch.item.ItemProcessor;

// 1개의 아이템을 처리할때마다 실행될 프로세서
public class HanjaVOItemProcessor implements ItemProcessor<HanjaVO, HanjaVO>{

	@Override
	public HanjaVO process(HanjaVO item) throws Exception {
		System.out.println(item + "을 처리함");
		return item;
	}

}

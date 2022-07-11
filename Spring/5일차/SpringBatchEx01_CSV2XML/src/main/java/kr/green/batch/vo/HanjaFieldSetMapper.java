package kr.green.batch.vo;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class HanjaFieldSetMapper implements FieldSetMapper<HanjaVO>{

	@Override
	public HanjaVO mapFieldSet(FieldSet fieldSet) throws BindException {
			HanjaVO hanjaVO = new HanjaVO();
			hanjaVO.setIdx(fieldSet.readInt(0));
			hanjaVO.setH(fieldSet.readString(1));
			hanjaVO.setK(fieldSet.readString(1));
			hanjaVO.setM(fieldSet.readString(1));
		return hanjaVO;
	}

}

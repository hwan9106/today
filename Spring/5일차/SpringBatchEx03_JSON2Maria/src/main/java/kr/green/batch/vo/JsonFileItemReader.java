package kr.green.batch.vo;

import java.io.InputStreamReader;
import java.util.List;

import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.ClassUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
public class JsonFileItemReader extends AbstractItemCountingItemStreamItemReader<HanjaVO>
	implements ResourceAwareItemReaderItemStream<HanjaVO>, InitializingBean {

	private Resource resource;
	@SuppressWarnings("unused")
	private String classToBound;
	private List<HanjaVO> items;
	private int index;
	private Gson gson = new Gson();
	
	public JsonFileItemReader() {
		setName(ClassUtils.getShortName(JsonFileItemReader.class));
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		;
	}

	public void setClassToBound(String classToBound) {
		this.classToBound = classToBound;
	}

	@Override
	public void setResource(Resource resource) { 
		this.resource = resource;
	}

	@Override
	protected HanjaVO doRead() throws Exception { // 1개 읽기
		return this.items==null || index >= this.items.size() ? null : this.items.get(index++);
	}

	@Override
	protected void doOpen() throws Exception { // 파일에서 읽어서 리스트에 저장
		try {
			InputStreamReader isr = new InputStreamReader(resource.getInputStream());
			this.items = gson.fromJson(isr, new TypeToken<List<HanjaVO>>() {}.getType());
			System.out.println(this.items.size() + "개 읽음!!!");
		}catch (Exception e) {
			System.out.println("파싱에러 : " + e.getMessage());
		}
	}

	@Override
	protected void doClose() throws Exception {
		;
	} 

}

package kr.green.member.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;


// 파일 다운로드를 책임지는 클래스
public class FileDownloadView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String ofileName = (String) model.get("ofileName"); // 원본이름
		String sfileName = (String) model.get("sfileName"); // 저장이름
		
		// 저장된 파일 객체 얻기
		@SuppressWarnings("deprecation")
		File file = new File(request.getRealPath("upload"), sfileName);
		
		response.setContentType(getContentType());
		response.setContentLength((int)file.length());
		String fileName = URLEncoder.encode(ofileName, "UTF-8").replaceAll("\\+", "%20");
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        OutputStream out = response.getOutputStream();
        FileInputStream fis = null;
        
        //파일 카피 후 마무리
        try {
            fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, out);
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            if(fis != null) fis.close();
        }
        out.flush();
	}
}

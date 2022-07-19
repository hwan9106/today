package kr.human.mvc13;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

// 다운로드를 책임질 객체
public class FileDownloadView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 컨트롤러에서 전달해준 데이터를 받는다.
		String oFileName = (String) model.get("ofileName"); // 원본 파일의  이름
		String sFileName = (String) model.get("sfileName"); // 저장 파일의  이름
		
		// 읽을 파일 객체
		@SuppressWarnings("deprecation")
		File file = new File(request.getRealPath("upload"), sFileName);
		
		// 다운로드 처리
		response.setContentType(getContentType());
		response.setContentLength((int)file.length());
		// 다운로드할 이름에 공백이나 한글처리를 담당
		String filename = URLEncoder.encode(oFileName,"UTF-8").replaceAll("\\+", "%20");
		
		// 다운로드할 이름과 타입을 지정한다.
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
       
        // 실제 다운로드를 실행한다.
        OutputStream os = response.getOutputStream();
        FileInputStream fis = null;
        try {
        	fis = new FileInputStream(file);
        	FileCopyUtils.copy(fis,os);
        }catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(fis!=null) fis.close();
		}
        os.flush();
	}
}

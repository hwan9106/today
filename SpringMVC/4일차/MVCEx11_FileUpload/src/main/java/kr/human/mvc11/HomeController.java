package kr.human.mvc11;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/multiUploadForm")
	public String multiUploadForm() {
		return "multiUploadForm";
	}
	
	@RequestMapping(value = "/multiUploadOk", method = RequestMethod.GET)
	public String multiUploadOkGet() {
		return "redirect:/multiUploadForm";
	}
	
	@RequestMapping(value = "/multiUploadOk", method = RequestMethod.POST)
	public String multiUploadOkPost(MultipartHttpServletRequest request, Model model) {
		logger.info("multiUploadOk 호출!!!!");
		
		// 실제 업로드돨 폴더의 절대 경로 구하기
		@SuppressWarnings("deprecation")
		String realPath = request.getRealPath("upload");
		// 내용 받기
		String content = request.getParameter("content");
		
		// 파일을 받아서 저장을 해야 한다.
		// 모든 파일을 리스트로 받는다.
		List<MultipartFile> list = request.getFiles("files");
		
		// 받은 정보를 저장할 스트링 버퍼를 만들자.
		StringBuffer sb = new StringBuffer();
		sb.append("받은 내용 : " + content + "<br>");
		sb.append("절대 경로 : " + realPath + "<br>");
		
		if(list!=null && list.size()>0) { // 파일이 존재 한다면
			for(MultipartFile file : list) {
				// 파일 처리하기
				if(file!=null && file.getSize()>0) {
					try {
						// 이름 중복처리를 하기 위해 중복되지 않는 이름을 만들어 주어야 한다.
						String saveFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
						// 파일 객체를 만들어 저장을 수행해야 한다.
						File saveFile = new File(realPath, saveFileName);
						// 파일 복사
						FileCopyUtils.copy(file.getBytes(), saveFile);
						
						// 파일의 정보를 처리한다.
						sb.append("saveName : " + saveFileName + "<br>");
						sb.append("originalName : " + file.getOriginalFilename() + "<br>");
						sb.append("contentType : " + file.getContentType() + "<br>");
						sb.append("fileSize : " + file.getSize() + "<br>");
						sb.append("<button onclick=\"fileDown('" + file.getOriginalFilename() + "','" + saveFileName + "');\">다운로드</button><br/><hr/>");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			model.addAttribute("result", sb.toString());
			return "multiUploadOk";
		}
		return "redirect:/multiUploadForm";
	}

	@RequestMapping(value = "/uploadForm")
	public String uploadForm() {
		return "uploadForm";
	}
	
	@RequestMapping(value = "/uploadOk", method = RequestMethod.GET)
	public String uploadOkGet() {
		return "redirect:/uploadForm";
	}
	
	@RequestMapping(value = "/uploadOk", method = RequestMethod.POST)
	public String uploadOkPost(HttpServletRequest request, MultipartFile file, Model model) {
		logger.info("uploadOk 호출!!!!");
		
		// 실제 업로드돨 폴더의 절대 경로 구하기
		@SuppressWarnings("deprecation")
		String realPath = request.getRealPath("upload");
		// 내용 받기
		String content = request.getParameter("content");
		
		// 파일을 받아서 저장을 해야 한다.
		if(file!=null && file.getSize()>0) { // 파일이 존재 한다면
			try {
				// 이름 중복처리를 하기 위해 중복되지 않는 이름을 만들어 주어야 한다.
				String saveFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
				// 파일 객체를 만들어 저장을 수행해야 한다.
				File saveFile = new File(realPath, saveFileName);
				// 파일 복사
				FileCopyUtils.copy(file.getBytes(), saveFile);
				
				// 파일의 정보를 처리한다.
				model.addAttribute("saveName", saveFileName);
				model.addAttribute("originalName", file.getOriginalFilename());
				model.addAttribute("contentType", file.getContentType());
				model.addAttribute("fileSize", file.getSize());
				model.addAttribute("realPath", realPath);
				model.addAttribute("content", content);
				return "uploadOk";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "redirect:/uploadForm";
	}

	// 다운로드가 가능하게 하자
	@RequestMapping(value = "/download")
	public ModelAndView download(@RequestParam HashMap<String, Object> params, ModelAndView mv) {
		String oFileName = (String) params.get("of");
		String sFileName = (String) params.get("sf");
		mv.setViewName("downloadView");
		mv.addObject("oFileName", oFileName);
		mv.addObject("sFileName", sFileName);
		return mv;
	}
	
}

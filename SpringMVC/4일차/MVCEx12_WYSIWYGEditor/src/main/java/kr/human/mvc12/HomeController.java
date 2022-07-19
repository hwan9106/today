package kr.human.mvc12;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

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
		
	//SummerNote 추가
	@RequestMapping(value = "summernote")
	public String summnernote() {
		return "summernote";
	}
	
	@RequestMapping(value = "result")
	public String result() {
		return "result";
	}
	
	//SummerNote에서 크기가 작은 파일을 base64로 인코딩되어 처리되지만 이미지 파일의 크기가 크면
	//직접 업로드 하는 로직을 만들어 주어야한다. 파일의 저장된 위치를 리턴하도록 만들어 주면 img태그로 이미지가 삽입 된다.
	@ResponseBody
	@RequestMapping(value = "/imageUpload", method=RequestMethod.POST)
	public String imageUpload(MultipartHttpServletRequest request) {
		String saveName="";
		String realPath= request.getRealPath("upload");
		//모든 파일을 받는다.
		List<MultipartFile> list = request.getFiles("file");
		if(list!=null && list.size()>0) {
			for(MultipartFile file : list) {
				if(file!=null && file.getSize()>0) {
					try {
						saveName=UUID.randomUUID().toString();
						File saveFile=new File(realPath, saveName);
						FileCopyUtils.copy(file.getBytes(), saveFile);
						
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		logger.info(request.getContextPath()+"/upload/"+saveName);
		return request.getContextPath()+"/upload/"+saveName;
	}
	//ckeditor 추가
		@RequestMapping(value = "ckeditor")
		public String ckeditor() {
			return "ckeditor";
		}
		
		@RequestMapping(value = "result2")
		public String result2() {
			return "result2";
		}
	
		 // Ckeditor에서 로컬파일 이미지를 추가하려면 직접 서버로 업로드를 실행하는 주소를 1개 만들어 주어야 한다.
		   // 리턴타입이 json타입이어야 한다.
		   @RequestMapping(value = "/fileupload", produces = "application/json;charset=UTF-8")
		   @ResponseBody
		   public JsonObject fileupload(MultipartHttpServletRequest request) {
		      String saveName = "";
		      String ofile = "";
		      @SuppressWarnings("deprecation")
		      String realPath = request.getRealPath("upload");
		      // 모든 파일을 받는다.
		      List<MultipartFile> list = request.getFiles("upload");
		      if(list!=null && list.size()>0) {
		         for(MultipartFile file : list) {
		            if(file!=null && file.getSize()>0) {
		               try {
		                  saveName = UUID.randomUUID() + "_" + file.getOriginalFilename();
		                  ofile = file.getOriginalFilename();
		                  File saveFile = new File(realPath, saveName);
		                  FileCopyUtils.copy(file.getBytes(), saveFile);
		               }catch (Exception e) {
		                  e.printStackTrace();
		               }
		            }
		         }
		      }
		      // 리턴할 데이터를 만들어 주어야 한다.
		        // {"uploaded" : 1, "fileName" : "test.jpg", "url" : "/img/test.jpg"}
		      
		      JsonObject json = new JsonObject();
		      json.addProperty("uploaded", 1);
		      json.addProperty("fileName", ofile);
		      json.addProperty("url", request.getContextPath() + "/upload/" + saveName);
		      logger.info(json.toString());
		      return json;
		   }
	
	
}

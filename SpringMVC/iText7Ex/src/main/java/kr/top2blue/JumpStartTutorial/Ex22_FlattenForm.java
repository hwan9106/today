package kr.top2blue.JumpStartTutorial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

// 폼을 만듭니다.
public class Ex22_FlattenForm {
	static String srcFileName = "JumpStartTutorial/result/Ex20_WidgetAnnotatin.pdf";
	static String destFileName = "JumpStartTutorial/result/Ex22_FlattenForm.pdf";
	static String fontFile = "font/NanumGothicCoding-Bold.ttf";
	static PdfFont korFont = null;

	public static void main(String[] args) {
		makePdf(destFileName);
		PDFView.viewChrome(destFileName); 
		// PDFView.viewAcrobat(destFileName); // 아크로벳 리더로 봐야 함
	}

	private static void makePdf(String fileName) {
		// 지정 파일의 상위 폴더가 없으면 만들어 준다.
		File destFile = new File(fileName);
		destFile.getParentFile().mkdirs();
		// PDF 문서 만들기
		PdfWriter pdfWriter = null;

		try {
			korFont = PdfFontFactory.createFont(fontFile, PdfEncodings.IDENTITY_H);
			pdfWriter = new PdfWriter(destFile);// PdfWriter객체 생성
			PdfDocument pdfDocument = new PdfDocument(new PdfReader(srcFileName), pdfWriter); // PdfDocument객체 생성
			
			PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDocument, true);
			
			// 폼에 내용을 채운다
			Map<String, PdfFormField> fields = form.getFormFields();
	        fields.get("name").setValue("제임스 본드").setFont(korFont);
	        fields.get("language").setValue("Java");
	        fields.get("experience1").setValue("Off");
	        fields.get("experience2").setValue("Yes");
	        fields.get("experience3").setValue("Yes");
	        fields.get("shift").setValue("6.30 am - 2.30 pm");
	        fields.get("info").setValue("제가 MI6 요원이 되었을 때 저는 38세였습니다.").setFont(korFont);
			
	        pdfDocument.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

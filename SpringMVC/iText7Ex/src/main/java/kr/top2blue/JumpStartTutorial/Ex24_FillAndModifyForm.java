package kr.top2blue.JumpStartTutorial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfArray;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;

// 폼을 만듭니다.
public class Ex24_FillAndModifyForm {
	static String srcFileName = "JumpStartTutorial/result/Ex20_WidgetAnnotatin.pdf";
	static String destFileName = "JumpStartTutorial/result/Ex24_FillAndModifyForm.pdf";
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
	        Map<String, PdfFormField> fields = form.getFormFields();

	        fields.get("name").setFont(korFont).setBackgroundColor(ColorConstants.ORANGE).setValue("제임스 본드");
	        fields.get("name").setRequired(true);

	        fields.get("language").setValue("Java");

	        fields.get("experience1").setValue("Yes");
	        fields.get("experience2").setValue("Yes");
	        fields.get("experience3").setValue("Yes");

	        List<PdfString> options = new ArrayList<PdfString>();
	        options.add(new PdfString("Any"));
	        options.add(new PdfString("8.30 am - 12.30 pm"));
	        options.add(new PdfString("12.30 pm - 4.30 pm"));
	        options.add(new PdfString("4.30 pm - 8.30 pm"));
	        options.add(new PdfString("8.30 pm - 12.30 am"));
	        options.add(new PdfString("12.30 am - 4.30 am"));
	        options.add(new PdfString("4.30 am - 8.30 am"));
	        PdfArray arr = new PdfArray(options);
	        fields.get("shift").setOptions(arr);
	        fields.get("shift").setValue("Any");
	        fields.get("info").setValue("제가 MI6 요원이 되었을 때 저는 38세였습니다.", korFont, 10f);
	        // 포커스 보내기
	        fields.get("name").setAdditionalAction(PdfName.U,PdfAction.createJavaScript("this.getField('name').setFocus();"));
	        
	        pdfDocument.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

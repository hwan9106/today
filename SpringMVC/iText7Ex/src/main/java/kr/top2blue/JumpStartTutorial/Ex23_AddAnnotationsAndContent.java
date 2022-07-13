package kr.top2blue.JumpStartTutorial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfButtonFormField;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfTextAnnotation;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

// 폼을 만듭니다.
public class Ex23_AddAnnotationsAndContent {
	static String srcFileName = "JumpStartTutorial/result/Ex20_WidgetAnnotatin.pdf";
	static String destFileName = "JumpStartTutorial/result/Ex23_AddAnnotationsAndContent.pdf";
	static String fontFile = "font/NanumGothicCoding-Bold.ttf";
	static PdfFont korFont = null;

	public static void main(String[] args) {
		makePdf(destFileName);
		// PDFView.viewChrome(destFileName); 
		PDFView.viewAcrobat(destFileName); // 아크로벳 리더로 봐야 함
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
			
			// Add text annotation
	        PdfAnnotation ann = new PdfTextAnnotation(new Rectangle(400, 795, 0, 0))
	                .setOpen(true)
	                .setTitle(new PdfString("Alert"))
	                .setContents("양식을 작성해 주십시오.");
	        pdfDocument.getFirstPage().addAnnotation(ann);

	        PdfCanvas canvas = new PdfCanvas(pdfDocument.getFirstPage());
	        canvas.beginText().setFontAndSize(korFont, 12).moveText(265, 597)
	                .showText("이용약관에 동의합니다.").endText();

	        // Add form field
	        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDocument, true);
	        PdfButtonFormField checkField = PdfFormField.createCheckBox(pdfDocument, new Rectangle(245, 594, 15, 15),
	                "agreement", "Off", PdfFormField.TYPE_CHECK);
	        checkField.setRequired(true);
	        form.addField(checkField);

	        // Update reset button
	        form.getField("reset").setAction(PdfAction.createResetForm(new String[]{"name", "language",
	                "experience1", "experience2", "experience3", "shift", "info", "agreement"}, 0));

	        pdfDocument.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

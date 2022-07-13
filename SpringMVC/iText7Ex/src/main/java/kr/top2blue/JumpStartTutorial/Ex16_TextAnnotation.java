package kr.top2blue.JumpStartTutorial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfTextAnnotation;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

// 주석을 추가 합니다.
public class Ex16_TextAnnotation {
	static String destFileName = "JumpStartTutorial/result/Ex16_TextAnnotation.pdf";
	static String fontFile = "font/NanumGothicCoding-Bold.ttf";
	static PdfFont korFont = null;
	static PdfFont helvetica = null;
	static PdfFont helveticaBold = null;

	public static void main(String[] args) {
		makePdf(destFileName);
		// PDFView.viewChrome(destFileName); // 크롬에서는 한글이 깨짐
		PDFView.viewAcrobat(destFileName); // 아크로벳으로 봐야 함
	}

	private static void makePdf(String fileName) {
		// 지정 파일의 상위 폴더가 없으면 만들어 준다.
		File destFile = new File(fileName);
		destFile.getParentFile().mkdirs();
		// PDF 문서 만들기
		PdfWriter pdfWriter = null;

		try {
			korFont = PdfFontFactory.createFont(fontFile, PdfEncodings.IDENTITY_H);
			helvetica = PdfFontFactory.createFont(StandardFonts.HELVETICA);
			helveticaBold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
			
			pdfWriter = new PdfWriter(destFile);// PdfWriter객체 생성
			PdfDocument pdfDocument = new PdfDocument(pdfWriter); // PdfDocument객체 생성
			
			PageSize pageSize = PageSize.A4; // 페이지 사이즈
			Document document = new Document(pdfDocument, pageSize);
			document.setFont(korFont);
			document.add(new Paragraph("The example of text annotation. 하하하 한글은?"));

			// 주석 추가
	        PdfAnnotation ann = new PdfTextAnnotation(new Rectangle(20, 800, 0, 0))
	                .setOpen(true)
	                .setColor(ColorConstants.GREEN)
	                .setTitle(new PdfString("iText"))
	                .setContents("With iText, you can truly take your documentation needs to the next level.");
	        pdfDocument.getFirstPage().addAnnotation(ann);
	        
	        PdfAnnotation ann2 = new PdfTextAnnotation(new Rectangle(270, 800, 0, 0))
	                .setOpen(true)
	                .setColor(ColorConstants.CYAN)
	                .setTitle(new PdfString("iText"))
	                .setContents("한글로 주석이 달릴까요?");
	        pdfDocument.getFirstPage().addAnnotation(ann2);
	        
	        document.close(); // Document객체 닫기
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

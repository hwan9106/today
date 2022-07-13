package kr.top2blue.JumpStartTutorial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.annot.PdfLinkAnnotation;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Link;
import com.itextpdf.layout.element.Paragraph;

// 주석을 추가 합니다.
public class Ex17_LinkAnnotation {
	static String destFileName = "JumpStartTutorial/result/Ex17_LinkAnnotation.pdf";
	static String fontFile = "font/NanumGothicCoding-Bold.ttf";
	static PdfFont korFont = null;

	public static void main(String[] args) {
		makePdf(destFileName);
		PDFView.viewChrome(destFileName);
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
			PdfDocument pdfDocument = new PdfDocument(pdfWriter); // PdfDocument객체 생성
			
			PageSize pageSize = PageSize.A4; // 페이지 사이즈
			Document document = new Document(pdfDocument, pageSize);
			document.setFont(korFont);
			
			// 링크 달기
			PdfLinkAnnotation annotation = new PdfLinkAnnotation(new Rectangle(0, 0))
	                .setAction(PdfAction.createURI("http://itextpdf.com/"));
	        Link link = new Link("http://itextpdf.com/", annotation);
	        Paragraph paragraph = new Paragraph("링크를 눌러 사이트를 방문해 주세요 ")
	                .add(link.setUnderline().setFontColor(ColorConstants.BLUE));
	        document.add(paragraph);
	        
			document.close(); // Document객체 닫기
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

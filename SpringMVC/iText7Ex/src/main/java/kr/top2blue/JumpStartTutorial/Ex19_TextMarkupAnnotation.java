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
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfTextMarkupAnnotation;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

// 주석을 추가 합니다.
public class Ex19_TextMarkupAnnotation {
	static String destFileName = "JumpStartTutorial/result/Ex19_TextMarkupAnnotation.pdf";
	static String fontFile = "font/NanumGothicCoding-Bold.ttf";
	static PdfFont korFont = null;

	public static void main(String[] args) {
		makePdf(destFileName);
		// PDFView.viewChrome(destFileName); // 크롬에서 안보임
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
			PdfDocument pdfDocument = new PdfDocument(pdfWriter); // PdfDocument객체 생성
			
			PageSize pageSize = PageSize.A4; // 페이지 사이즈
			Document document = new Document(pdfDocument, pageSize);
			document.setFont(korFont);
			
	        Paragraph paragraph = new Paragraph("The example of text markup annotation.");
	        document.showTextAligned(paragraph, 20, 795, 1, TextAlignment.LEFT, VerticalAlignment.MIDDLE, 0);

	        // markup annotation
	        PdfAnnotation ann = PdfTextMarkupAnnotation.createHighLight(new Rectangle(105, 790, 0, 0),// 주석이 나타날 위치
	                new float[]{175, 785, 110, 785, 175, 805, 110, 805}) // 링크 테두리 : 우하->좌하->우상->좌상 순서임
	                .setColor(ColorConstants.CYAN)
	                .setTitle(new PdfString("Hello!"))
	                .setContents("나는 주석입니다.");
	        pdfDocument.getFirstPage().addAnnotation(ann);
	        
			document.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

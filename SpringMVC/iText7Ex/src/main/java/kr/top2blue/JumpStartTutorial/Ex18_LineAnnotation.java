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
import com.itextpdf.kernel.pdf.PdfArray;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfLineAnnotation;
import com.itextpdf.layout.Document;

// 주석을 추가 합니다.
public class Ex18_LineAnnotation {
	static String destFileName = "JumpStartTutorial/result/Ex18_LineAnnotation.pdf";
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
			
			PdfPage page = document.getPdfDocument().addNewPage();

	        PdfArray lineEndings = new PdfArray();
	        lineEndings.add(new PdfName("Diamond"));
	        lineEndings.add(new PdfName("Diamond"));

	        // Create line annotation with inside caption
	        PdfAnnotation annotation = new PdfLineAnnotation(
	            new Rectangle(0, 0),
	            new float[]{20, 790, page.getPageSize().getWidth() - 20, 790})
	                .setLineEndingStyles((lineEndings))
	                .setContentsAsCaption(true)
	                .setTitle(new PdfString("iText"))
	                .setContents("이 예제는 라인 주석을 추가하는 예제 입니다.")
	                .setColor(ColorConstants.BLUE);
	        page.addAnnotation(annotation);
	        
			document.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

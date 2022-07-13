package kr.top2blue.JumpStartTutorial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

// 페이지 수정
public class Ex26_ChangePage {
	static String srcFileName = "JumpStartTutorial/result/Ex15_UFO.pdf";
	static String destFileName = "JumpStartTutorial/result/Ex26_ChangePage.pdf";
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
			
			float margin = 72;
	        for (int i = 1; i <= pdfDocument.getNumberOfPages(); i++) {
	            PdfPage page = pdfDocument.getPage(i);
	            // change page size
	            Rectangle mediaBox = page.getMediaBox();
	            Rectangle newMediaBox = new Rectangle(mediaBox.getLeft() - margin, mediaBox.getBottom() - margin,
	                    mediaBox.getWidth() + margin * 2, mediaBox.getHeight() + margin * 2);
	            page.setMediaBox(newMediaBox);
	            // add border
	            PdfCanvas over = new PdfCanvas(page);
	            over.setStrokeColor(ColorConstants.RED);
	            over.rectangle(mediaBox.getLeft(), mediaBox.getBottom(), mediaBox.getWidth(), mediaBox.getHeight());
	            over.stroke();
	            // 짝수페이지 뒤집기
	            if (i % 2 == 0) {
	                page.setRotation(180);
	            }
	        }
	        pdfDocument.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

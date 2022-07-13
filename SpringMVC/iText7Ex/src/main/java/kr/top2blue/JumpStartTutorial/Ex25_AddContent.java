package kr.top2blue.JumpStartTutorial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

// 폼을 만듭니다.
public class Ex25_AddContent {
	static String srcFileName = "JumpStartTutorial/result/Ex15_UFO.pdf";
	static String destFileName = "JumpStartTutorial/result/Ex25_AddContent.pdf";
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
			
			Document document = new Document(pdfDocument);
	        Rectangle pageSize;
	        PdfCanvas canvas;
	        int n = pdfDocument.getNumberOfPages();
	        for (int i = 1; i <= n; i++) {
	            PdfPage page = pdfDocument.getPage(i);
	            pageSize = page.getPageSize();
	            canvas = new PdfCanvas(page);
	            //Draw header text
	            canvas.beginText().setFontAndSize(PdfFontFactory.createFont(StandardFonts.HELVETICA), 7)
	                    .moveText(pageSize.getWidth() / 2 - 24, pageSize.getHeight() - 10)
	                    .showText("I want to believe")
	                    .endText();
	            // Draw footer line
	            canvas.setStrokeColor(ColorConstants.BLACK)
	                    .setLineWidth(.2f)
	                    .moveTo(pageSize.getWidth() / 2 - 30, 20)
	                    .lineTo(pageSize.getWidth() / 2 + 30, 20).stroke();
	            // Draw page number
	            canvas.beginText().setFontAndSize(PdfFontFactory.createFont(StandardFonts.HELVETICA), 7)
	                    .moveText(pageSize.getWidth() / 2 -5 , 23)
	                    .showText(String.valueOf(i))
	                    .showText(" of ")
	                    .showText(String.valueOf(n))
	                    .endText();
	            // Draw watermark
	            Paragraph p = new Paragraph("CONFIDENTIAL").setFontSize(60);
	            canvas.saveState();
	            PdfExtGState gs1 = new PdfExtGState().setFillOpacity(0.2f);
	            canvas.setExtGState(gs1);
	            document.showTextAligned(p, pageSize.getWidth() / 2, pageSize.getHeight() / 2,
	                    pdfDocument.getPageNumber(page),TextAlignment.CENTER, VerticalAlignment.MIDDLE, 45);
	            canvas.restoreState();
	        }
	        document.close();
	        pdfDocument.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

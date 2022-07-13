package kr.top2blue.JumpStartTutorial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.AffineTransform;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

// 원본을 읽어서 4개의 페이지로 분할해서 다시 저장하기
public class Ex30_TheGoldenGateBridge_N_up {
	static String srcFileName = "src/main/resources/pdf/the_golden_gate_bridge.pdf";
	static String destFileName = "JumpStartTutorial/result/Ex30_TheGoldenGateBridge_N_up.pdf";
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
			PdfDocument pdfDocument = new PdfDocument(pdfWriter); // PdfDocument객체 생성
			PdfDocument sourcePdf = new PdfDocument(new PdfReader(srcFileName)); // 원본읽기
			
			// 원본페이지
	        PdfPage origPage = sourcePdf.getPage(1); // 원본의 1페이지 얻기
	        PdfFormXObject pageCopy = origPage.copyAsFormXObject(pdfDocument); // 페이지 복사

	        // 원본사이즈
	        Rectangle orig = origPage.getPageSize();
	        // N-up page
	        PageSize nUpPageSize = PageSize.A4.rotate();
	        PdfPage page = pdfDocument.addNewPage(nUpPageSize);
	        PdfCanvas canvas = new PdfCanvas(page);

	        // Scale page : 1/4로 축소
	        AffineTransform transformationMatrix = AffineTransform.getScaleInstance(nUpPageSize.getWidth() / orig.getWidth() / 2f, nUpPageSize.getHeight() / orig.getHeight() / 2f);
	        canvas.concatMatrix(transformationMatrix);

	        // Add pages to N-up page
	        canvas.addXObjectAt(pageCopy, 0, orig.getHeight());
	        canvas.addXObjectAt(pageCopy, orig.getWidth(), orig.getHeight());
	        canvas.addXObjectAt(pageCopy, 0, 0);
	        canvas.addXObjectAt(pageCopy, orig.getWidth(), 0);

	        pdfDocument.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

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

// 원본을 읽어서 크기를 변경하여 붙인다.
public class Ex27_TheGoldenGateBridge_Scale_Shrink {
	static String srcFileName = "src/main/resources/pdf/the_golden_gate_bridge.pdf";
	static String destFileName = "JumpStartTutorial/result/Ex27_TheGoldenGateBridge_Scale_Shrink.pdf";
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
			
	        PdfPage origPage = sourcePdf.getPage(1); // 페이지 얻기
	        Rectangle orig = origPage.getPageSizeWithRotation(); // 원본 가로방향 사이즈 얻기

	        // 가로방향 A4사이즈 페이지 추가
	        PdfPage page = pdfDocument.addNewPage(PageSize.A4.rotate());
	        
	        PdfCanvas canvas = new PdfCanvas(page);
	        // 변환행렬 작성
	        AffineTransform transformationMatrix = // 가로세로 비율에 맞게 크기 조절
	        		AffineTransform.getScaleInstance(page.getPageSize().getWidth() / orig.getWidth(), 
	        										 page.getPageSize().getHeight() / orig.getHeight());
	        canvas.concatMatrix(transformationMatrix); // 변환행렬 적용
	        PdfFormXObject pageCopy = origPage.copyAsFormXObject(pdfDocument); // 복사
	        canvas.addXObjectAt(pageCopy, 0, 0); // 캔바스에 붙이기
	        
	        // 원본 붙이기 
	        pdfDocument.addPage(origPage.copyTo(pdfDocument)); // 페이지 붙이기

	        // Add A2 page
	        page = pdfDocument.addNewPage(PageSize.A2.rotate());
	        // Scale original page content using transformation matrix
	        canvas = new PdfCanvas(page);
	        transformationMatrix = 
	        		AffineTransform.getScaleInstance(page.getPageSize().getWidth() / orig.getWidth(), page.getPageSize().getHeight() / orig.getHeight());
	        canvas.concatMatrix(transformationMatrix);
	        canvas.addXObjectAt(pageCopy, 0, 0);

	        sourcePdf.close();
	        pdfDocument.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

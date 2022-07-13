package kr.top2blue.JumpStartTutorial;

import java.io.File;
import java.io.FileNotFoundException;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants;

public class Ex08_GridLines {
	static String destFileName = "JumpStartTutorial/result/Ex08_GridLines.pdf";

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
			pdfWriter = new PdfWriter(destFile);// PdfWriter객체 생성
			PdfDocument pdfDocument = new PdfDocument(pdfWriter); // PdfDocument객체 생성
			PageSize pageSize = PageSize.A4.rotate(); // 가로방향 A4페이지 사이즈
			PdfPage pdfPage = pdfDocument.addNewPage(pageSize); // 페이지 추가
			PdfCanvas canvas = new PdfCanvas(pdfPage); // 콘텐츠 스트림에 데이터를 쓰기 위한 PdfCanvas객체 생성
			// 변환 행열을 지정한다.
			// resources/img/transformation_matrix.png 파일을 참조할것
			// 0,0의 위치를 화면 중앙으로 변경함
			canvas.concatMatrix(1, 0, 0, 1, pageSize.getWidth() / 2, pageSize.getHeight() / 2);
			
			Color grayColor = new DeviceCmyk(0.f, 0.f, 0.f, 0.875f);
	        Color greenColor = new DeviceCmyk(1.f, 0.f, 1.f, 0.176f);
	        Color blueColor = new DeviceCmyk(1.f, 0.156f, 0.f, 0.118f);
	        
	        canvas.setLineWidth(0.5f).setStrokeColor(blueColor);

	        //Draw horizontal grid lines
	        for (int i = -((int) pageSize.getHeight() / 2 - 57); i < ((int) pageSize.getHeight() / 2 - 56); i += 40) {
	            canvas.moveTo(-(pageSize.getWidth() / 2 - 15), i)
	                    .lineTo(pageSize.getWidth() / 2 - 15, i);
	        }
	        //Draw vertical grid lines
	        for (int j = -((int) pageSize.getWidth() / 2 - 61); j < ((int) pageSize.getWidth() / 2 - 60); j += 40) {
	            canvas.moveTo(j, -(pageSize.getHeight() / 2 - 15))
	                    .lineTo(j, pageSize.getHeight() / 2 - 15);
	        }
	        canvas.stroke();

	        //Draw axes
	        canvas.setLineWidth(3).setStrokeColor(grayColor);
	        drawAxes(canvas, pageSize);

	        //Draw plot
	        canvas.setLineWidth(2).setStrokeColor(greenColor)
	                .setLineDash(10, 10, 8)
	                .moveTo(-(pageSize.getWidth() / 2 - 15), -(pageSize.getHeight() / 2 - 15))
	                .lineTo(pageSize.getWidth() / 2 - 15, pageSize.getHeight() / 2 - 15).stroke();
			
			pdfDocument.close(); // Document객체 닫기
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void drawAxes(PdfCanvas canvas, PageSize pageSize) {
		// Draw X axis
		canvas.moveTo(-(pageSize.getWidth() / 2 - 15), 0).lineTo(pageSize.getWidth() / 2 - 15, 0).stroke();

		// Draw X axis arrow
		canvas.setLineJoinStyle(PdfCanvasConstants.LineJoinStyle.ROUND).moveTo(pageSize.getWidth() / 2 - 25, -10)
				.lineTo(pageSize.getWidth() / 2 - 15, 0).lineTo(pageSize.getWidth() / 2 - 25, 10).stroke()
				.setLineJoinStyle(PdfCanvasConstants.LineJoinStyle.MITER);

		// Draw Y axis
		canvas.moveTo(0, -(pageSize.getHeight() / 2 - 15)).lineTo(0, pageSize.getHeight() / 2 - 15).stroke();

		// Draw Y axis arrow
		canvas.saveState().setLineJoinStyle(PdfCanvasConstants.LineJoinStyle.ROUND).moveTo(-10, pageSize.getHeight() / 2 - 25)
				.lineTo(0, pageSize.getHeight() / 2 - 15).lineTo(10, pageSize.getHeight() / 2 - 25).stroke().restoreState();

		// Draw X serif
		for (int i = -((int) pageSize.getWidth() / 2 - 61); i < ((int) pageSize.getWidth() / 2 - 60); i += 40) {
			canvas.moveTo(i, 5).lineTo(i, -5);
		}
		// Draw Y serif
		for (int j = -((int) pageSize.getHeight() / 2 - 57); j < ((int) pageSize.getHeight() / 2 - 56); j += 40) {
			canvas.moveTo(5, j).lineTo(-5, j);
		}
		canvas.stroke();
	}
}

package kr.top2blue.JumpStartTutorial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringTokenizer;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.Property;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;

// Simple table renderer example.
public class Ex15_UFO {
	static String destFileName = "JumpStartTutorial/result/Ex15_UFO.pdf";
	static String DATA = "src/main/resources/data/ufo.csv";

	static String fontFile = "font/NanumGothicCoding-Bold.ttf";

	static PdfFont helvetica = null;
	static PdfFont helveticaBold = null;

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
			helvetica = PdfFontFactory.createFont(StandardFonts.HELVETICA);
			helveticaBold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

			pdfWriter = new PdfWriter(destFile);// PdfWriter객체 생성
			PdfDocument pdfDocument = new PdfDocument(pdfWriter); // PdfDocument객체 생성
			
			// 문서에 이벤트 핸들러 등록 : IEventHandler인터페이스를 구현해서 작성
			// 이벤트 핸들러 MyEventHandler를 추가 합니다.
			// 이 메서드는 이벤트 유형이 PdfDocumentEvent.END_PAGE 발생할 때마다 트리거됩니다. 
			// 즉 새 페이지가 생성되었거나 마지막 페이지에 도달하여 완료되었기 때문에 iText가 페이지에 내용 추가를 완료할 때마다 실행됩니다.
			pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, new MyEventHandler());

			PageSize pageSize = PageSize.A4; // 페이지 사이즈
			Document document = new Document(pdfDocument, pageSize);
			// 제목 추가
			Paragraph paragraph = new Paragraph("List of reported UFO sightings in 20th century")
					.setTextAlignment(TextAlignment.CENTER).setFont(helveticaBold).setFontSize(14);
			document.add(paragraph);

			Table table = new Table(UnitValue.createPercentArray(new float[] { 3, 5, 7, 4 }));
			// 데이터 읽기
			List<String> lineList = Files.readAllLines(Paths.get(DATA));
			// 제목 출력
			process(table, lineList.get(0), helveticaBold, true);
			// 내용 출력
			for (int i = 1; i < lineList.size(); i++) {
				process(table, lineList.get(i), helvetica, false);
			}
			// 테이블 추가
			document.add(table);

			document.close(); // Document객체 닫기
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 내용을 추가해 주는 메서드
	public static void process(Table table, String line, PdfFont font, boolean isHeader) {
		StringTokenizer tokenizer = new StringTokenizer(line, ";");
		while (tokenizer.hasMoreTokens()) {
			if (isHeader) {
				table.addHeaderCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(font)).setFontSize(9)
						.setBorder(new SolidBorder(ColorConstants.BLACK, 0.5f)));
			} else {
				table.addCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(font)).setFontSize(9)
						.setBorder(new SolidBorder(ColorConstants.BLACK, 0.5f)));
			}
		}
	}
	// IEventHandler 인터페이스 구현
	// IEventHandler은 단일 메소드를 가진 인터페이스를 구현합니다.
	static class MyEventHandler implements IEventHandler {

		public void handleEvent(Event event) {
			PdfDocumentEvent docEvent = (PdfDocumentEvent) event; // 인수로 넘어온 이벤트 형변경
			PdfDocument pdfDoc = docEvent.getDocument(); // 이벤트로 부터 문서 얻기
			PdfPage page = docEvent.getPage(); // 이벤트로 부터 페이지 얻기
			int pageNumber = pdfDoc.getPageNumber(page); // 페이지 번호 얻기
			Rectangle pageSize = page.getPageSize(); // 페이지 사이즈 얻기
			
			// 출력을 위해 PdfCanvas객체 생성
			PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);

			// 배경색 지정
			Color limeColor = new DeviceCmyk(0.208f, 0, 0.584f, 0);
			Color blueColor = new DeviceCmyk(0.445f, 0.0546f, 0, 0.0667f);
			// 홀수 페이지와 짝수 페이지의 배경색을 다르게 지정
			pdfCanvas.saveState().setFillColor(pageNumber % 2 == 1 ? limeColor : blueColor)
					.rectangle(pageSize.getLeft(), pageSize.getBottom(), pageSize.getWidth(), pageSize.getHeight())
					.fill().restoreState();

			// 머릿말과 꼬릿말을 달아준다.
			pdfCanvas.beginText().setFontAndSize(helvetica, 9)
					.moveText(pageSize.getWidth() / 2 - 60, pageSize.getTop() - 20).showText("THE TRUTH IS OUT THERE") // 머리말
					.moveText(60, -pageSize.getTop() + 30).showText(String.valueOf(pageNumber)).endText(); // 꼬릿말에 페이지 번호

			// 워터마크 추가
			Canvas canvas = new Canvas(pdfCanvas, page.getPageSize());
			canvas.setFontColor(ColorConstants.WHITE);
			canvas.setProperty(Property.FONT_SIZE, UnitValue.createPointValue(60));
			canvas.setProperty(Property.FONT, helveticaBold);
			canvas.showTextAligned(new Paragraph("CONFIDENTIAL"), 298, 421, pdfDoc.getPageNumber(page),
					TextAlignment.CENTER, VerticalAlignment.MIDDLE, 45);
			canvas.close();
			
			pdfCanvas.release();
		}
	}
}

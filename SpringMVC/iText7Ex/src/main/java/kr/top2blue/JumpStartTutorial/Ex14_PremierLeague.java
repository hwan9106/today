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
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;
// Simple table renderer example.
public class Ex14_PremierLeague {
	static String destFileName = "JumpStartTutorial/result/Ex14_PremierLeague.pdf";
	static String DATA = "src/main/resources/data/premier_league.csv";

	static String fontFile = "font/NanumGothicCoding-Bold.ttf";
	// CMYK 색 모형(Cyan Magenta Yellow Key(Black))은 감산 혼합의 색 모형이다. 
	// 마젠타, 시안, 노랑, 검정을 원색으로 한다.
	static Color greenColor = new DeviceCmyk(0.78f, 0, 0.81f, 0.21f);
	static Color yellowColor = new DeviceCmyk(0, 0, 0.76f, 0.01f);
	static Color redColor = new DeviceCmyk(0, 0.76f, 0.86f, 0.01f);
	static Color blueColor = new DeviceCmyk(0.28f, 0.11f, 0, 0);
    
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
			PageSize pageSize = new PageSize(842, 680); // 페이지 사이즈
			Document document = new Document(pdfDocument, pageSize);
			// 폰트 준비
	        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
	        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
	        // 지정 폭으로 테이블 생성
	        Table table = new Table(UnitValue.createPercentArray(new float[]{1.5f, 7, 2, 2, 2, 2, 3, 4, 4, 2}));
	        // 테이블 중앙 정렬
	        table.setTextAlignment(TextAlignment.CENTER).setHorizontalAlignment(HorizontalAlignment.CENTER);
	        // 데이터 읽기
	        List<String> lineList = Files.readAllLines(Paths.get(DATA));
	        // 제목 출력
	        process(table, lineList.get(0), bold, true);
	        // 내용 출력
	        for(int i=1;i<lineList.size();i++) {
	        	process(table, lineList.get(i), font, false);
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
        StringTokenizer tokenizer = new StringTokenizer(line, ";"); // 문자열 토큰으로 분리
        int columnNumber = 0;
        while (tokenizer.hasMoreTokens()) {
            if (isHeader) { // 제목이라면
                Cell cell = new Cell().add(new Paragraph(tokenizer.nextToken())); // 셀에 내용 추가
                cell.setNextRenderer(new RoundedCornersCellRenderer(cell)); // 셀의 모양을 지정 CellRenderer클래스를 상속받아 구현
                cell.setPadding(5).setBorder(null); // 패딩과 보더 지정
                table.addHeaderCell(cell); // 제목으로 추가
            } else { // 내용이라면
                columnNumber++; // 열 카운트
                Cell cell = new Cell().add(new Paragraph(tokenizer.nextToken())); // 셀에 내용 추가
                cell.setFont(font).setBorder(new SolidBorder(ColorConstants.BLACK, 0.5f)); // 폰트와 테투리 지정
                switch (columnNumber) { // 4, 5, 6열은 배경색 변경
                    case 4:
                        cell.setBackgroundColor(greenColor);
                        break;
                    case 5:
                        cell.setBackgroundColor(yellowColor);
                        break;
                    case 6:
                        cell.setBackgroundColor(redColor);
                        break;
                    default:
                        cell.setBackgroundColor(blueColor);
                        break;
                }
                table.addCell(cell); // 테이블에 셀추가
            }
        }
    }

    // CellRenderer클래스를 상속받아 셀 그리기를 구현한 클래스
    private static class RoundedCornersCellRenderer extends CellRenderer {
        public RoundedCornersCellRenderer(Cell modelElement) {
            super(modelElement);
        }
        // drawBorder메서드 오버라이딩 : 상단 둥근 모서리 구현
        @Override
        public void drawBorder(DrawContext drawContext) {
            Rectangle rectangle = getOccupiedAreaBBox();
            float llx = rectangle.getX() + 1;
            float lly = rectangle.getY() + 1;
            float urx = rectangle.getX() + getOccupiedAreaBBox().getWidth() - 1;
            float ury = rectangle.getY() + getOccupiedAreaBBox().getHeight() - 1;
            PdfCanvas canvas = drawContext.getCanvas();
            float r = 4;
            float b = 0.4477f;
            canvas.moveTo(llx, lly).lineTo(urx, lly).lineTo(urx, ury - r)
                    .curveTo(urx, ury - r * b, urx - r * b, ury, urx - r, ury)
                    .lineTo(llx + r, ury)
                    .curveTo(llx + r * b, ury, llx, ury - r * b, llx, ury - r)
                    .lineTo(llx, lly).stroke();
            super.drawBorder(drawContext);
        }
    }
}

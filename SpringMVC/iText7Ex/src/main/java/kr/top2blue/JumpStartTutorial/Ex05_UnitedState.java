package kr.top2blue.JumpStartTutorial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

public class Ex05_UnitedState {
	static String destFileName = "JumpStartTutorial/result/Ex05_UnitedState.pdf";
	// ;으로 구분된 데이터 파일
	public static final String DATA = "src/main/resources/data/united_states.csv";
    static String fontFile = "font/NanumGothicCoding.ttf";

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
			Document document = new Document(pdfDocument,PageSize.A4.rotate()); // 가로 방향 Document객체 생성
			// setMargins(float topMargin, float rightMargin, float bottomMargin, float leftMargin)
			document.setMargins(30, 20, 30, 20);
			// 폰트 생성
			// PdfFont font = PdfFontFactory.createFont(fontFile, PdfEncodings.IDENTITY_H);
	        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
	        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
	        // 테이블 생성
	        Table table = new Table(UnitValue.createPercentArray(new float[]{4, 1, 3, 4, 3, 3, 3, 3, 1})).useAllAvailableWidth();
	        // 데이터 읽기
	        BufferedReader br = new BufferedReader(new FileReader(DATA));
	        String line = br.readLine(); // 1줄을 읽어
	        process(table, line, bold, true);  // 제목으로 사용
	        while ((line = br.readLine()) != null) { // 나머지 줄은
	            process(table, line, font, false);//  데이터로 사용
	        }
	        br.close();
	        document.add(table); // 테이블 추가
	        
			document.close(); // Document객체 닫기
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void process(Table table, String line, PdfFont font, boolean isHeader) {
        StringTokenizer tokenizer = new StringTokenizer(line, ";"); // ;으로 구분하여 토큰으로 분리
        while (tokenizer.hasMoreTokens()) { // 토큰 반복
            if (isHeader) { // 제목이라면
            	// 1개의 셀을 만들어 테이블에 추가한다.
                table.addHeaderCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(font)));
            } else { // 데이터라면 
            	// 1개의 셀을 만들어 테이블에 추가한다.
                table.addCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(font)));
            }
        }
    }

}

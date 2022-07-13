package kr.top2blue.JumpStartTutorial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;

import vo.HanjaVO2;

public class Ex06_ThousandCharacters {
	static String destFileName = "JumpStartTutorial/result/Ex06_ThousandCharacters.pdf";
	public static final String DATA = "src/main/resources/json/hanja2.json";
    static String fontFile1 = "font/NanumGothicCoding.ttf";
    static String fontFile2 = "font/stkaiti.ttf";

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
	        PdfFont korFont = PdfFontFactory.createFont(fontFile1, PdfEncodings.IDENTITY_H);
	        PdfFont hanjaFont = PdfFontFactory.createFont(fontFile2, PdfEncodings.IDENTITY_H);
	        // 테이블 생성
	        Table table = new Table(UnitValue.createPercentArray(new float[]{1, 2, 3, 15})).useAllAvailableWidth();
	        // 데이터 읽기
	        List<HanjaVO2>  list = readJSON();
	        // 헤더 만들기
	        table.addHeaderCell(makeCell("번호", korFont, 14f, ColorConstants.BLUE, TextAlignment.CENTER));
	        table.addHeaderCell(makeCell("한자", korFont, 14f, ColorConstants.BLUE, TextAlignment.CENTER));
	        table.addHeaderCell(makeCell("한글", korFont, 14f, ColorConstants.BLUE, TextAlignment.CENTER));
	        table.addHeaderCell(makeCell("설명", korFont, 14f, ColorConstants.BLUE, TextAlignment.CENTER));
	        // 내용 만들기
	        for(HanjaVO2 vo : list) {
	        	table.addCell(makeCell(String.format("%3s", vo.getIdx()+""),korFont, TextAlignment.CENTER));
	        	table.addCell(makeCell(vo.getH(), hanjaFont, 15f, TextAlignment.CENTER));
	        	table.addCell(makeCell(vo.getK(), korFont, 12f, TextAlignment.CENTER));
	        	table.addCell(makeCell(vo.getM1(),korFont, 8f,ColorConstants.BLACK,  TextAlignment.LEFT, VerticalAlignment.MIDDLE));
	        }
	        document.add(table); // 테이블 추가
	        
			document.close(); // Document객체 닫기
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// Cell을 만들어 리턴하는 메서드들
	// (내용,폰트,폰트크기,폰트컬러,가로정렬,세로정렬)
	private static Cell makeCell(String text, PdfFont korFont,TextAlignment alignment ) {
		return new Cell().add(new Paragraph(text).setFont(korFont)).setTextAlignment(alignment);
	}
	private static Cell makeCell(String text, PdfFont korFont,float fontSize, TextAlignment alignment ) {
		return new Cell().add(new Paragraph(text).setFont(korFont).setFontSize(fontSize)).setTextAlignment(alignment);
	}
	private static Cell makeCell(String text, PdfFont korFont,float fontSize,Color color, TextAlignment alignment ) {
		return new Cell().add(new Paragraph(text).setFont(korFont).setFontSize(fontSize).setFontColor(color)).setTextAlignment(alignment);
	}
	private static Cell makeCell(String text, PdfFont korFont,float fontSize,Color color, TextAlignment alignment, VerticalAlignment verticalAlignment) {
		return new Cell().add(new Paragraph(text).setFont(korFont)
												 .setFontSize(fontSize)
												 .setFontColor(color)
							  ).setTextAlignment(alignment).setVerticalAlignment(verticalAlignment);
	}
	
	// JSON파일 읽기
	private static List<HanjaVO2> readJSON() {
		Gson gson = new Gson();
		List<HanjaVO2> list = null;
		try(FileReader fr= new FileReader(DATA);) {
			list = gson.fromJson(fr, new TypeToken<List<HanjaVO2>>() {}.getType());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return list;
	}
}

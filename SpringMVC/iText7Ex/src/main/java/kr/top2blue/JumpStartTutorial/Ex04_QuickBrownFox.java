package kr.top2blue.JumpStartTutorial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

public class Ex04_QuickBrownFox {
	static String destFileName = "JumpStartTutorial/result/Ex04_QuickBrownFox.pdf";
    static String DOG = "src/main/resources/img/dog.bmp";
    static String FOX = "src/main/resources/img/fox.bmp";
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
			Document document = new Document(pdfDocument); // Document객체 생성
			// 폰트 생성
			PdfFont font = PdfFontFactory.createFont(fontFile, PdfEncodings.IDENTITY_H);
			// 이미지읽기
	        Image fox = new Image(ImageDataFactory.create(FOX));
	        Image dog = new Image(ImageDataFactory.create(DOG));
	        // 이미지 텍스트 혼합한 문단 작성
	        Paragraph paragraph = new Paragraph("The quick brown ")
	                .add(fox)
	                .add(" jumps over the lazy ")
	                .add(dog);
	        
	        document.add(paragraph); // 문단 추가

	        Paragraph paragraph2 = new Paragraph("빠른 부라운이 ")
	        		.setFont(font)
	        		.setFontSize(10f)
	        		.setFontColor(ColorConstants.MAGENTA)
	        		.add(fox)
	        		.add(" 게으른 강아지를 뛰어 넘습니다. ")
	        		.add(dog);
	        
	        document.add(paragraph2); // 문단 추가
	        
	        
	        
			document.close(); // Document객체 닫기
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

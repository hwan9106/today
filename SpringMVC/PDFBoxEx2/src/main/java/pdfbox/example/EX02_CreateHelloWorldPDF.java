package pdfbox.example;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class EX02_CreateHelloWorldPDF {
	public static void main(String[] args) {
		String destFileName = "pdf_example/EX02_CreateHelloWorldPDF.pdf"; //저장 경로 지정
		try (PDDocument document = new PDDocument()) {// 문서작성
			PDPage blankPage = new PDPage(PDRectangle.A4); // A4 크기의 페이지 작성
			document.addPage(blankPage); // 페이지 추가
			
			// 페이지 크기 구하기
			PDRectangle pageSize = blankPage.getMediaBox(); 
			float pageWidth = pageSize.getWidth(); // 폭
			float pageHeight = pageSize.getHeight(); // 높이
			System.out.println(pageSize);
			System.out.println(pageWidth + ", " + pageHeight);
			
			// 텍스트 출력
			PDPageContentStream contentStream = new PDPageContentStream(document, blankPage);
			contentStream.beginText();
			contentStream.setFont(PDType1Font.HELVETICA, 12);
			contentStream.newLineAtOffset(100, 700);
			contentStream.showText("Hello World!!!");
			contentStream.endText();
			contentStream.close();
			
			document.save(destFileName); // 저장
			System.out.println(destFileName + "PDF 작성완료");
		} catch (IOException e) {
			e.printStackTrace();
		}
		chromeView(destFileName); // 작성된 문서보기
	}

	// 저장된 문서 크롬으로 보기
	private static void chromeView(String destFileName) {
		String chrome = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
		try {
			File file = new File(destFileName); // 파일 객체 생성
			new ProcessBuilder(chrome, file.getAbsolutePath()).start(); // 프로세스 시작
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

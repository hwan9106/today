package pdfbox.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.text.PDFTextStripper;

public class UpdatePage2 {

	public static void main(String[] args) {
		String srcFileName = "pdf_example/EX02_CreateHelloWorldPDF.pdf";
		String destFileName = "pdf_example/UpdatePage.pdf";
		try (
				PDDocument srcDocument = PDDocument.load(new File(srcFileName)); //문서읽기
				PDDocument descDocument = new PDDocument(); // 문서 만들기
			) {
			int numberOfPages = srcDocument.getNumberOfPages(); // 전체 페이지수 얻기
			System.out.println("전체페이지 : " + numberOfPages + "페이지");
			
			// 1페이지 읽어 수정하고 추가
			PDPage page = srcDocument.getPage(0); // 1페이지 얻기
			descDocument.addPage(page); // 페이지 추가
            // 폰트 변경
            InputStream in  = new FileInputStream("font/NanumGothicCoding.ttf");
			PDType0Font font = PDType0Font.load(descDocument, in);
			
			float fontSize = 12; // 폰트 크기

			PDFTextStripper pdfStripper = new PDFTextStripper();
			String text = pdfStripper.getText(srcDocument);
			System.out.println(text);
			// 내용 추가하기 
			PDPageContentStream contentStream = new PDPageContentStream(descDocument, page);
			contentStream.beginText();
			contentStream.setFont(font, fontSize);
			contentStream.newLineAtOffset(55, 720);
			contentStream.showText(text.substring(0, text.length()-2));
			text = "새로운 줄이 추가 될까?";
			contentStream.newLineAtOffset(0,-fontSize);
			contentStream.showText(text);
			contentStream.endText();
			System.out.println("내용 1줄 추가");
			contentStream.close();
			
			descDocument.save(destFileName); // 저장
			ChromeView.view(destFileName); // 보기
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

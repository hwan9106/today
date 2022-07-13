package pdfbox.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

public class EX13_UpdatePage {
	// 여백
	private static float MARGIN_X = 40;
	private static float MARGIN_Y = 40;

	public static void main(String[] args) {
		String srcFileName = "pdf_example/EX12_CreateMultiPage.pdf";
		String destFileName = "pdf_example/EX13_UpdatePage.pdf";
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

			PDPageContentStream contentStream = new PDPageContentStream(descDocument, page);
			String message = "글자를 출력해보자.";
			PDRectangle mediaBox = page.getMediaBox();
			// 좌우 여백을 뺀 폭
			float width = mediaBox.getWidth() - 2 * MARGIN_X;
			float height = mediaBox.getHeight() - 2 * MARGIN_Y;
			float startY = mediaBox.getUpperRightY() - MARGIN_Y;// 첫번째 줄 Y값 위치
			startY = PDFBoxUtil.addLine(contentStream, font, fontSize, width, MARGIN_X, startY, message, 1);
			startY = PDFBoxUtil.addLine(contentStream, font, fontSize, width, MARGIN_X, startY, message, 2);
			startY = PDFBoxUtil.addLine(contentStream, font, fontSize, width, MARGIN_X, startY, message, 3);
			contentStream.close();
			
			// 2페이지 읽어 수정 없이 추가
			descDocument.addPage(srcDocument.getPage(1)); 
			
			// 3페이지를 수정하고 다시 추가
			page = srcDocument.getPage(2);
			contentStream = new PDPageContentStream(descDocument, page);
			contentStream.addRect(MARGIN_X, MARGIN_Y, width, height); // 여백을 뺸 영역지정
			contentStream.stroke(); // 그리기
			String text = Files.readString(Paths.get("src/main/resources/NationalAnthem2.txt"));
			startY = PDFBoxUtil.addParagraph(contentStream, font, fontSize, width, MARGIN_X, startY, text, 0);
			descDocument.addPage(page);
			contentStream.close();
			
			descDocument.save(destFileName); // 저장
			
			ChromeView.view(destFileName); // 보기
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package pdfbox.example;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class EX18_BackgroundImage {
	private static float MARGIN_X = 40;
	private static float MARGIN_Y = 40;
	
	public static void main(String[] args) {
		String destFileName = "pdf_example/EX18_BackgroundImage.pdf";
		String imgFileName = "src/main/resources/background.jpg";
		try (PDDocument document = new PDDocument();) {
			// 페이지 만들기
			PDPage page = new PDPage(PDRectangle.LETTER);
			// 배경이미지 로드
			PDImageXObject pdImage = PDImageXObject.createFromFile(imgFileName, document);
			PDPageContentStream contentStream = new PDPageContentStream(document, page);
			// 종이 전체에 배경 이미지 그리기
			PDRectangle mediaBox = page.getMediaBox();
			contentStream.drawImage(pdImage, mediaBox.getLowerLeftX(), mediaBox.getLowerLeftY(),
								    mediaBox.getUpperRightX(), mediaBox.getUpperRightY());
			contentStream.addRect(MARGIN_X, MARGIN_Y, mediaBox.getUpperRightX()-MARGIN_X*2, mediaBox.getUpperRightY()-MARGIN_Y*2);
			// contentStream.fill(); // 채우기
			contentStream.stroke(); // 외곽선그리기
			
			contentStream.close();
			document.addPage(page);

			document.save(destFileName); // 저장
			System.out.println("PDF에 배경 그림 출력 완료");
			ChromeView.view(destFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

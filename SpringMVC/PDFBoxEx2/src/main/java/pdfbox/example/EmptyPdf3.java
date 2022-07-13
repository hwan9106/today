package pdfbox.example;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class EmptyPdf3 {

	private static final float FONT_SIZE = 12;
	private static final float LEADING = -1.5f * FONT_SIZE;
	private static PDFont FONT;
	// 여백
	private static float MARGIN_X = 40;
	private static float MARGIN_Y = 40;

	public static void main(String[] args) {
		String destFileName = "pdf_example/EmptyPdf2.pdf";
		try (PDDocument document = new PDDocument()) {// 문서작성
			PDPage page = new PDPage(PDRectangle.A4); // 페이지 작성

			System.out.println("페이지 크기");
			PDRectangle mediaBox = page.getMediaBox();
			System.out.println(mediaBox);
			// 좌우 여백을 뺀 폭
			float width = mediaBox.getWidth() - 2 * MARGIN_X;
			float height = mediaBox.getHeight() - 2 * MARGIN_Y;

			float lowerLeftX = mediaBox.getLowerLeftX();
			float upperRightX = mediaBox.getUpperRightX();
			float lowerLeftY = mediaBox.getLowerLeftY();
			float upperRightY = mediaBox.getUpperRightY();

			// 시작위치
			float startX = MARGIN_X;
			float startY = MARGIN_Y;

			// 폰트 변경
			InputStream in = new FileInputStream("font/NanumGothicCoding.ttf");
			FONT = PDType0Font.load(document, in);

			PDPageContentStream contentStream = new PDPageContentStream(document, page);
			contentStream.setFont(FONT, FONT_SIZE);

			// 배경이미지 로드
			PDImageXObject pdImage = PDImageXObject.createFromFile("src/main/resources/background.jpg", document);
			// 종이 전체에 배경 이미지 그리기
			contentStream.drawImage(pdImage, lowerLeftX, lowerLeftY, upperRightX, upperRightY);

			// 사각형 그리기
			System.out.println("사각형 그리기");
			// contentStream.setNonStrokingColor(Color.lightGray);
			System.out.println(String.format("%f, %f, %f,%f", startX, startY, width, height));
			contentStream.addRect(startX, startY, width, height);
			// contentStream.fill(); // 채우기
			contentStream.stroke(); // 외곽선그리기

			String message = "글자를 출력해보자.";
			
			/*
			contentStream.beginText();
			// 처음위치 계산 : 0,0 으로부터 상대좌표값 계산
			float size = FONT_SIZE * FONT.getStringWidth(message) / 1000;
			float free = (width - size) / 2;
			startX = MARGIN_X + free;
			startY = upperRightY - MARGIN_Y + LEADING;
			// 처음 위치로 이동
			contentStream.newLineAtOffset(startX, startY);
			// 문자 출력
			contentStream.showText(message);
			// 5줄 출력 : x좌표는 바뀌지 않고 y좌표값만 줄어든다.
			for (int i = 0; i < 5; i++) {
				contentStream.newLineAtOffset(0, LEADING);
				contentStream.showText(message);
			}
			contentStream.endText();
			*/
			startX = MARGIN_X;
			startY = mediaBox.getUpperRightY() - MARGIN_Y + LEADING;

			for(int i=1;i<=10;i++) {
				addChunk(contentStream, width, startX, startY, message+i, i%3+1);
				startY += LEADING;
			}
						
			contentStream.close();
			document.addPage(page); // 페이지 추가
			document.save(destFileName); // 저장
			System.out.println(destFileName + "PDF 작성완료");

			ChromeView.view(destFileName);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void addChunk(PDPageContentStream contentStream, float width, float startX, float startY, String message, int align)
			throws IOException {

		float size = FONT_SIZE * FONT.getStringWidth(message) / 1000;
		float free = 0;
		switch (align) {
		case 2:
			free = (width - size) / 2;
			break;
		case 3:
			free = width - size;
			break;
		}
		startX = MARGIN_X + free;
		contentStream.beginText();
		contentStream.newLineAtOffset(startX, startY); // 위치로 이동
		contentStream.showText(message);// 문자 출력
		contentStream.endText();
	}
}

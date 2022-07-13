package pdfbox.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

public class EX06_CreateTextAlign {
	// 여백
	private static float MARGIN_X = 40;
	private static float MARGIN_Y = 40;
	
	public static void main(String[] args) {
		String destFileName = "pdf_example/EX06_CreateTextAlign.pdf";
		try (PDDocument document = new PDDocument()) {// 문서작성
			PDPage blankPage = new PDPage(PDRectangle.A4); // A4 크기의 페이지 작성
			document.addPage(blankPage); // 페이지 추가
            // 폰트 변경
            InputStream in  = new FileInputStream("font/NanumGothicCoding.ttf");
			PDType0Font korfont = PDType0Font.load(document, in);
			float fontSize = 12; // 폰트 크기
			float leading = -1.5f * fontSize; // 행간 계산
			
			PDPageContentStream contentStream = new PDPageContentStream(document, blankPage);
			contentStream.setFont(korfont, fontSize);
			contentStream.setLeading(-leading); // 텍스트 행간 설정 
			String message = "글자를 출력해보자.";
			
			PDRectangle mediaBox = blankPage.getMediaBox();
			System.out.println(mediaBox);
			// 좌우 여백을 뺀 폭
			float width = mediaBox.getWidth() - 2 * MARGIN_X;
			float height = mediaBox.getHeight() - 2 * MARGIN_Y;

			// 사각형그리기
			contentStream.addRect(MARGIN_X, MARGIN_Y, width, height); // 여백을 뺸 영역지정
			contentStream.stroke(); // 그리기
			
			// 왼쪽정렬
			contentStream.beginText();
			// 시작위치
			float startX = mediaBox.getLowerLeftX() + MARGIN_X;
			float startY = mediaBox.getUpperRightY() - MARGIN_Y + leading;
			System.out.println("첫번째 좌표 : " + startX + ", " + startY);
			contentStream.newLineAtOffset(startX, startY);
			contentStream.showText(message);
			contentStream.endText();
			
			// 가운데 정렬
			contentStream.beginText();
			float size = fontSize * korfont.getStringWidth(message) / 1000;
			float free = (width - size) / 2;
			startX = MARGIN_X + free; 
			startY += leading;
			System.out.println("두번째 좌표 : " + startX + ", " + startY);
			contentStream.newLineAtOffset(startX, startY);
			contentStream.showText(message);
			contentStream.endText();
			
			// 오른쪽 정렬
			contentStream.beginText();
			size = fontSize * korfont.getStringWidth(message) / 1000;
			free = width - size;
			startX = MARGIN_X + free; 
			startY += leading;
			System.out.println("세번째 좌표 : " + startX + ", " + startY);
			contentStream.newLineAtOffset(startX, startY);
			contentStream.showText(message);
			contentStream.endText();
			
			// 폰트 변경해서 출력해보자
			fontSize = 20; // 폰트 크기
			leading = -1.5f * fontSize; // 행간 계산
			contentStream.setFont(korfont, fontSize);
			message = "나는 제목";
			
			// 오른쪽 정렬
			contentStream.beginText();
			size = fontSize * korfont.getStringWidth(message) / 1000;
			free = width - size;
			startX = MARGIN_X + free; 
			startY += leading;
			System.out.println("세번째 좌표 : " + startX + ", " + startY);
			contentStream.newLineAtOffset(startX, startY);
			contentStream.showText(message);
			contentStream.endText();
			
			// 가운데 정렬
			contentStream.beginText();
			size = fontSize * korfont.getStringWidth(message) / 1000;
			free = (width - size)/2;
			startX = MARGIN_X + free; 
			startY += leading;
			System.out.println("네번째 좌표 : " + startX + ", " + startY);
			contentStream.newLineAtOffset(startX, startY);
			contentStream.showText(message);
			contentStream.endText();

			// 왼쪽정렬
			contentStream.beginText();
			// 시작위치
			startX = MARGIN_X;
			startY += leading;
			System.out.println("다섯번째 좌표 : " + startX + ", " + startY);
			contentStream.newLineAtOffset(startX, startY);
			contentStream.showText(message);
			contentStream.endText();
			
			contentStream.close();
			document.save(destFileName); // 저장
			System.out.println(destFileName + "PDF 작성완료");
		} catch (IOException e) {
			e.printStackTrace();
		}
		ChromeView.view(destFileName); // 작성된 문서보기
	}

}

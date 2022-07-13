package pdfbox.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

public class EX08_CreateParagraph {
	// 여백
	private static float MARGIN_X = 40;
	private static float MARGIN_Y = 40;
	
	public static void main(String[] args) {
		String destFileName = "pdf_example/EX08_CreateParagraph.pdf";
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
			String message = Files.readString(Paths.get("src/main/resources/NationalAnthem2.txt"));
			
			PDRectangle mediaBox = blankPage.getMediaBox();
			// 좌우 여백을 뺀 폭과 높이
			float width = mediaBox.getWidth() - 2 * MARGIN_X;
			float height = mediaBox.getHeight() - 2 * MARGIN_Y;

			// 사각형그리기
			contentStream.addRect(MARGIN_X, MARGIN_Y, width, height); // 여백을 뺸 영역지정
			contentStream.stroke(); // 그리기
			
			float startY = mediaBox.getUpperRightY() - MARGIN_Y; // 첫번째 줄 Y값 위치
			startY = addLine(contentStream, korfont, fontSize, width, startY, message, 1);

			contentStream.close();
			document.save(destFileName); // 저장
			System.out.println(destFileName + "PDF 작성완료");
		} catch (IOException e) {
			e.printStackTrace();
		}
		ChromeView.view(destFileName); // 작성된 문서보기
	}
	/**
	 * 지정 Y좌표값을 행에 원하는 문자열을 정렬하여 출력하기
	 * @Param PDPageContentStream contentStream : PDPageContentStream 객체
	 * @Param PDFont font : 폰트 객체
	 * @Param float fontSize : 폰트크기
	 * @Param float width : 출력할 라인의 넓이
	 * @Param float startY : 출력할 Y좌표
	 * @Param String message : 출력할 내용
	 * @Param int align : 정렬 방식 (1-왼쪽 정렬,2-가운데 정렬, 3-오른쪽정렬, 이외-왼쪽 정렬)
	 * 
	 * @Return : Y 좌표값
	 */
	private static float addLine(PDPageContentStream contentStream, PDFont font,float fontSize, float width, float startY, String message,int align)
			throws IOException {
		// 폰트지정
		contentStream.setFont(font, fontSize);
		float size = fontSize * font.getStringWidth(message) / 1000; // 글자의 길이 계산
		float leading = -1.5f * fontSize; // 행간 계산
		
		contentStream.beginText();
		// 정렬 방식
		float free = 0;
		switch (align) {
		case 2:
			free = (width - size) / 2;
			break;
		case 3:
			free = width - size;
			break;
		default:
			free = 0;
			break;
		}
		float startX = MARGIN_X + free;
		startY += leading;
		System.out.println("좌표 : " + startX + ", " + startY);
		contentStream.newLineAtOffset(startX, startY);
		contentStream.showText(message);
		contentStream.endText();
		return startY;
	}
}

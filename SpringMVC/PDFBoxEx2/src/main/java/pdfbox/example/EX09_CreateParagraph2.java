package pdfbox.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

public class EX09_CreateParagraph2 {
	// 여백
	private static float MARGIN_X = 40;
	private static float MARGIN_Y = 40;

	public static void main(String[] args) {
		String destFileName = "pdf_example/EX09_CreateParagraph2.pdf";
		try (PDDocument document = new PDDocument()) {// 문서작성
			PDPage blankPage = new PDPage(PDRectangle.A4); // A4 크기의 페이지 작성
			document.addPage(blankPage); // 페이지 추가

			// 좌우 여백을 뺀 폭과 높이
			PDRectangle mediaBox = blankPage.getMediaBox();
			float width = mediaBox.getWidth() - 2 * MARGIN_X;
			float height = mediaBox.getHeight() - 2 * MARGIN_Y;

			// 폰트 변경
			InputStream in = new FileInputStream("font/NanumGothicCoding.ttf");
			PDType0Font korfont = PDType0Font.load(document, in);
			float fontSize = 12; // 폰트 크기
			float leading = -1.5f * fontSize; // 행간 계산

			// 긴 문장 읽기
			String text = Files.readString(Paths.get("src/main/resources/NationalAnthem2.txt"));
			System.out.println(text);
			
			PDPageContentStream contentStream = new PDPageContentStream(document, blankPage);
			contentStream.setFont(korfont, fontSize);
			contentStream.setLeading(-leading); // 텍스트 행간 설정 
			
			// 사각형그리기
			contentStream.addRect(MARGIN_X, MARGIN_Y, width, height); // 여백을 뺸 영역지정
			contentStream.stroke(); // 그리기
			
			// 1. 긴 문장을 폭에 맞추어 잘라내야 한다.
			List<String> lines = parseLines(korfont, fontSize, text, width);
			for (String line : lines) {
				System.out.println(line);
			}

			float startY = mediaBox.getUpperRightY() - MARGIN_Y; // 첫번째 줄 Y값 위치
			// PDF로 출력
			for (String line : lines) {
				startY = addLine(contentStream, korfont, fontSize, width, startY, line, 1);
			}
			
			startY += leading; // 빈 줄 삽입
			
			// 폰트변경
			fontSize = 18; // 폰트 크기
			leading = -1.5f * fontSize; // 행간 계산
			
			// 1. 긴 문장을 폭에 맞추어 잘라내야 한다.
			lines = parseLines(korfont, fontSize, text, width);
			for (String line : lines) {
				System.out.println(line);
			}

			// PDF로 출력
			for (String line : lines) {
				startY = addLine(contentStream, korfont, fontSize, width, startY, line, 1);
			}
			
			contentStream.close();
			document.save(destFileName); // 저장
			System.out.println(destFileName + "PDF 작성완료");
			
			ChromeView.view(destFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 긴 문장을 폰트의 크기와 폭에 맞추어 잘라내야 한다.
	private static List<String> parseLines(PDFont font, float fontSize, String text, float width) throws IOException {
		List<String> lines = new ArrayList<String>();
		int lastSpace = -1; // 마지막 공백 위치
		while (text.length() > 0) { // 문자열 끝까지 반복
			// 다음위치의 공백을 찾는다.
			int spaceIndex = text.indexOf(' ', lastSpace + 1); 
			// 공백을 찾지 못하면  문자열 전체 길이를 인덱스로 같는다.
			if (spaceIndex < 0) 
				spaceIndex = text.length();
			// 공백까지 문자열을 찾는다.
			String subString = text.substring(0, spaceIndex);
			// 잘라낸 문자열의 폭을 계산한다.
			float size = fontSize * font.getStringWidth(subString) / 1000;
			if (size > width) { // 문자열의 폭이 전체복보다 크다면
				if (lastSpace < 0) { // 마지막 공백이 없다면
					lastSpace = spaceIndex; // 마지막 공백을 현재 공백 위치로 가진다.
				}
				subString = text.substring(0, lastSpace); // 문자열 잘라내기
				lines.add(subString); // 리스트에 추가
				text = text.substring(lastSpace).trim(); // 나머지 문자열만 취한다.
				lastSpace = -1; // 마지막 공백을 다시 -1로 초기화
			} else if (spaceIndex == text.length()) { // 공백의 위치가 문자열의 길이와 같다면
				lines.add(text); // 나머지를 모두 리스트에 널고
				text = ""; // 문자열을 지운다.
			} else {
				lastSpace = spaceIndex; // 폭이 아직 남았다면 마지막 위치를 공백의 위치로 변경
			}
		}
		return lines; // 리스트를 리턴한다.
	}
	
	// 1줄 출력하는 메서드
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

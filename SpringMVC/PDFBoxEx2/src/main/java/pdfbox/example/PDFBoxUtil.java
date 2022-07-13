package pdfbox.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;

public class PDFBoxUtil {
	// 정렬방식에 0을 추가해 보자. 0은 양쪽정렬!!!!
	public static float addParagraph( PDPageContentStream contentStream, PDFont font, float fontSize, float width, float marginX, float startY, String text, int align) throws IOException {
		List<String> lines = parseLines(font, fontSize, text, width);
		for (String line : lines) {
			float charSpacing = 0; // 글자간격
			if(align==0) { // 중앙 정렬이라면 
	                if (line.length() > 1) { // 글자가 있을때
	                    float size = fontSize * font.getStringWidth(line) / 1000; // 폭 계산
	                    float free = width - size; // 공백 계산
	                    if (free > 0 && !lines.get(lines.size() - 1).equals(line)) { // 공백이 있으며 마지막 줄이 아니라면
	                        charSpacing = free / (line.length() - 1); // 공백을 길이로 나눠 글자 간격 계산
	                    }
	                }
			}
			contentStream.setCharacterSpacing(charSpacing); // 글자간격 설정
			startY = addLine(contentStream, font, fontSize, width, marginX, startY, line, align); // 출력
		}
		return startY;
	}
	
	// 긴 문장을 폰트의 크기와 폭에 맞추어 잘라내야 한다.
	public static List<String> parseLines(PDFont font, float fontSize, String text, float width) throws IOException {
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
	public static float addLine(PDPageContentStream contentStream, PDFont font,float fontSize, float width,float marginX, float startY, String message, int align)
			throws IOException {
		// 폰트지정
		contentStream.setFont(font, fontSize);
		float size = fontSize * font.getStringWidth(message) / 1000; // 글자의 길이 계산
		float leading = -1.5f * fontSize; // 행간 계산
		contentStream.setLeading(leading);
		
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
		float startX = marginX + free;
		startY += leading;
		// System.out.println("좌표 : " + startX + ", " + startY);
		contentStream.newLineAtOffset(startX, startY);
		contentStream.showText(message);
		contentStream.endText();
		return startY;
	}
}

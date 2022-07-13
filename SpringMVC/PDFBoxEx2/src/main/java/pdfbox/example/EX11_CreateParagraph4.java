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
import org.apache.pdfbox.pdmodel.font.PDType0Font;

public class EX11_CreateParagraph4 {
	// 여백
	private static float MARGIN_X = 40;
	private static float MARGIN_Y = 40;

	public static void main(String[] args) {
		String destFileName = "pdf_example/EX11_CreateParagraph4.pdf";
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
			
			PDPageContentStream contentStream = new PDPageContentStream(document, blankPage);
			contentStream.setFont(korfont, fontSize);
			contentStream.setLeading(-leading); // 텍스트 행간 설정 
			
			// 사각형그리기
			contentStream.addRect(MARGIN_X, MARGIN_Y, width, height); // 여백을 뺸 영역지정
			contentStream.stroke(); // 그리기
			
			float startY = mediaBox.getUpperRightY() - MARGIN_Y; // 첫번째 줄 Y값 위치

			// 1줄 출력
			startY = PDFBoxUtil.addLine(contentStream, korfont, fontSize, width, MARGIN_X, startY, "제목1", 1);
			startY = PDFBoxUtil.addLine(contentStream, korfont, fontSize, width, MARGIN_X, startY, "제목2", 2);
			startY = PDFBoxUtil.addLine(contentStream, korfont, fontSize, width, MARGIN_X, startY, "제목3", 3);
			
			startY += leading;
			// 문단 출력
			// 왼쪽 정렬
			startY = PDFBoxUtil.addParagraph(contentStream, korfont, fontSize, width, MARGIN_X, startY, text, 1);
			startY += leading; // 빈 줄 삽입
			
			// 폰트변경
			fontSize = 10; // 폰트 크기
			leading = -1.5f * fontSize; // 행간 계산
			
			// 가운데 정렬
			startY = PDFBoxUtil.addParagraph(contentStream, korfont, fontSize, width, MARGIN_X, startY, text, 2);
			startY += leading; // 빈 줄 삽입
			
			// 양쪽 정렬
			startY = PDFBoxUtil.addParagraph(contentStream, korfont, fontSize, width, MARGIN_X, startY, text, 0);
			startY += leading; // 빈 줄 삽입
			
			// 오른쪽 정렬
			startY = PDFBoxUtil.addParagraph(contentStream, korfont, fontSize, width, MARGIN_X, startY, text, 3);
			startY += leading; // 빈 줄 삽입
			
			contentStream.close();
			document.save(destFileName); // 저장
			
			ChromeView.view(destFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

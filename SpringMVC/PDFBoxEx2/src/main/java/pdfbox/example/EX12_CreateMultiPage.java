package pdfbox.example;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

public class EX12_CreateMultiPage {
	// 여백
	private static float MARGIN_X = 40;
	private static float MARGIN_Y = 40;

	public static void main(String[] args) {
		String destFileName = "pdf_example/EX12_CreateMultiPage.pdf";
		try (PDDocument document = new PDDocument()) {// 문서작성
			for(int i=0;i<4;i++) {
				PDPage blankPage = makePage(document, PDRectangle.A4, MARGIN_X, MARGIN_Y);
				document.addPage(blankPage); // 페이지 추가
			}
			document.save(destFileName); // 저장
			ChromeView.view(destFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 지정한 크기의 문서를 만들어 여백을 제외한 부분에 테두리를 그리거
	private static PDPage makePage(PDDocument document, PDRectangle rectangle, float marginX, float marginY)
			throws IOException {
		PDPage page = new PDPage(rectangle);
		PDRectangle mediaBox = page.getMediaBox();
		float width = mediaBox.getWidth() - 2 * MARGIN_X;
		float height = mediaBox.getHeight() - 2 * MARGIN_Y;
		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		contentStream.addRect(marginX, marginY, width, height); // 여백을 뺸 영역지정
		contentStream.stroke(); // 그리기
		contentStream.close();
		return page;
	}
}

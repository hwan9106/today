package pdfbox.example;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class EX17_DrawImage {

	public static void main(String[] args) {
		String destFileName = "pdf_example/EX17_DrawImage.pdf";
		String imgFileName = "src/main/resources/dog.jpg";
		try (PDDocument document = new PDDocument();) {
			// 페이지 만들기
			PDPage page = new PDPage(PDRectangle.LETTER);
			// 그림 읽기
			PDImageXObject pdImage = PDImageXObject.createFromFile(imgFileName, document);
			PDPageContentStream contents = new PDPageContentStream(document, page);
			// 그림 출력
			contents.drawImage(pdImage, 15, 550); // 출력위치만 지정
			contents.drawImage(pdImage, 315, 550, 100, 100); // 폭과 높이 지정
			contents.drawImage(pdImage, 15, 350);
			contents.drawImage(pdImage, 415, 450, -100, -100); // 음수면 뒤집기 가능
			contents.close();

			document.addPage(page);

			document.save(destFileName); // 저장
			System.out.println("PDF에 그림 출력 완료");
			ChromeView.view(destFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

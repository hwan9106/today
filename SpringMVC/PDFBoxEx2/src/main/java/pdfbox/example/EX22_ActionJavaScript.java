package pdfbox.example;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionJavaScript;

public class EX22_ActionJavaScript {
	public static void main(String[] args) {
		String destFileName = "pdf_example/EX22_ActionJavaScript.pdf";
		String imgFileName = "src/main/resources/dog.jpg";
		try (PDDocument document = new PDDocument();) {// 문서읽기
			// 페이지 만들기
			PDPage page = new PDPage();

			// 오픈시 액션지정!!!
			String javaScript = "app.alert({cMsg: '연습용', nIcon: 3, nType: 0, cTitle: 'PDFBox!!'});";
			PDActionJavaScript PDAjavascript = new PDActionJavaScript(javaScript);
			document.getDocumentCatalog().setOpenAction(PDAjavascript);
			
			// 그림 읽기
			PDImageXObject pdImage = PDImageXObject.createFromFile(imgFileName, document);
			PDPageContentStream contents = new PDPageContentStream(document, page);
			// 그림 출력
			contents.drawImage(pdImage, 15, 550);
			contents.close();

			document.addPage(page);
			document.save(destFileName); // 저장
			
			System.out.println("저장 완료");
			ChromeView.view(destFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

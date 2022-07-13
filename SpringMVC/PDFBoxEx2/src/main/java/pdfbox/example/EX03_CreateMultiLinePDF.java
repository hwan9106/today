package pdfbox.example;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class EX03_CreateMultiLinePDF {
	public static void main(String[] args) {
		String destFileName = "pdf_example/EX03_CreateMultiLinePDF.pdf";
		try (PDDocument document = new PDDocument()) {// 문서작성
			PDPage blankPage = new PDPage(PDRectangle.A4); // A4 크기의 페이지 작성
			document.addPage(blankPage); // 페이지 추가
			
			// 텍스트 여러줄 출력
			PDPageContentStream contentStream = new PDPageContentStream(document, blankPage);
			contentStream.setLeading(18.5f); // 텍스트 행간 설정 
			contentStream.beginText();
			contentStream.setFont(PDType1Font.HELVETICA, 12);
			contentStream.newLineAtOffset(100, 700);
            for(int i=0;i<10;i++) {
            	contentStream.showText(String.format("%02d. ", i + 1) + "Hello World!!!");
            	contentStream.newLine();
            }
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

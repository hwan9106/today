package pdfbox.example;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

public class EX19_DrawLine {
	public static void main(String[] args) {
		String destFileName = "pdf_example/EX19_DrawLine.pdf";
		float fontSize = 12;
		try (PDDocument document = new PDDocument()) {// 문서작성
			PDPage page = new PDPage(PDRectangle.A4); // 페이지 작성
		    
			System.out.println("페이지 크기");
			PDRectangle mediaBox = page.getMediaBox();
			System.out.println(mediaBox);
			// 여백
            float marginY = 33;
            float marginX = 33;
            // 좌우 여백을 뺀 폭
            float width = mediaBox.getWidth() - 2 * marginX;
            float height = mediaBox.getHeight() - 2 * marginY;
            
			float lowerLeftX = mediaBox.getLowerLeftX();
			float upperRightX = mediaBox.getUpperRightX();
			float lowerLeftY = mediaBox.getLowerLeftY();
	        float upperRightY = mediaBox.getUpperRightY();
	        System.out.println(lowerLeftX + " ~ " + upperRightX);
	        System.out.println(lowerLeftY + " ~ " + upperRightY);
	        
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // 수평선그리기
			for(int h = (int) marginY;h<upperRightY-marginY;h+=fontSize*2) {
				contentStream.moveTo(marginX, h); // 시작점 이동
				contentStream.lineTo(marginX+width, h); // 끝점이동
			}
			
			// 수직선그리기
			for(int w = (int) marginX;w<upperRightX-marginX;w+=fontSize*2) {
				contentStream.moveTo(w, marginY);
				contentStream.lineTo(w, marginY+height);
			}
			
			// 대각선 그리기
			contentStream.moveTo(marginX, marginY);
			contentStream.lineTo(width+marginX, height+marginY);
			contentStream.moveTo(marginX, height+marginY);
			contentStream.lineTo(width+marginX, marginY);
			
			contentStream.stroke(); // 그리기
			contentStream.close();
			document.addPage(page); // 페이지 추가
			document.save(destFileName); // 저장
			System.out.println(destFileName + "PDF 작성완료");
			
			ChromeView.view(destFileName);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

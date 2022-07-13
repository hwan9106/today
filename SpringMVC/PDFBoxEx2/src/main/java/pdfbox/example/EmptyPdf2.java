package pdfbox.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class EmptyPdf2  {
    
	private static final float FONT_SIZE = 12;
    
	public static void main(String[] args) {
		String destFileName = "pdf_example/EmptyPdf2.pdf";
		float fontSize = 12;
		try (PDDocument document = new PDDocument()) {// 문서작성
			PDPage page = new PDPage(PDRectangle.A4); // 페이지 작성
		    
			System.out.println("페이지 크기");
			PDRectangle mediaBox = page.getMediaBox();
			System.out.println(mediaBox);
			// 여백
            float marginY = 40;
            float marginX = 40;
            // 좌우 여백을 뺀 폭
            float width = mediaBox.getWidth() - 2 * marginX;
            float height = mediaBox.getHeight() - 2 * marginY;
            
			float lowerLeftX = mediaBox.getLowerLeftX();
			float upperRightX = mediaBox.getUpperRightX();
			float lowerLeftY = mediaBox.getLowerLeftY();
	        float upperRightY = mediaBox.getUpperRightY();
	        System.out.println(lowerLeftX + " ~ " + upperRightX);
	        System.out.println(lowerLeftY + " ~ " + upperRightY);
	        
	        // 시작위치
	        float startX = mediaBox.getLowerLeftX() + marginX;
            float startY = mediaBox.getLowerLeftY() + marginY;
            
	        // 폰트 변경
            InputStream in  = new FileInputStream("font/NanumGothicCoding.ttf");
            PDFont font = PDType0Font.load(document, in);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // 배경이미지 로드
            PDImageXObject pdImage = PDImageXObject.createFromFile("src/main/resources/background.jpg", document);
            // 종이 전체에 배경 이미지  그리기
	        contentStream.drawImage(pdImage, lowerLeftX, lowerLeftY, upperRightX, upperRightY);
	        
            // 사각형 그리기
            System.out.println("사각형 그리기");
            System.out.println(String.format("%f, %f, %f,%f", startX, startY, width, height));
            contentStream.addRect(startX, startY, width, height);
            // contentStream.fill(); // 채우기
            // contentStream.stroke();  // 외곽선그리기
			
			// 수평선그리기
			for(int h = (int) marginY;h<upperRightY-marginY;h+=fontSize) {
				contentStream.moveTo(marginX, h);
				contentStream.lineTo(marginX+width, h);
			}
			
			// 수직선그리기
			for(int w = (int) marginX;w<upperRightX-marginX;w+=fontSize) {
				contentStream.moveTo(w, marginY);
				contentStream.lineTo(w, marginY+height);
			}
			
			contentStream.stroke();
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

package pdfbox.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class EmptyPdf  {
    
	private static final float FONT_SIZE = 12;
    private static final float LEADING = -1.5f * FONT_SIZE;
    private static PDFont FONT;   
    
	public static void main(String[] args) {
		String destFileName = "pdf_example/EmptyPdf.pdf";
		try (PDDocument document = new PDDocument()) {// 문서작성
			PDPage page = new PDPage(PDRectangle.A4); // 페이지 작성
			
			System.out.println("페이지 크기");
			PDRectangle mediaBox = page.getMediaBox();
			System.out.println(mediaBox);
			float lowerLeftX = mediaBox.getLowerLeftX();
			float upperRightX = mediaBox.getUpperRightX();
			float lowerLeftY = mediaBox.getLowerLeftY();
	        float upperRightY = mediaBox.getUpperRightY();
	        System.out.println(lowerLeftX + " ~ " + upperRightX);
	        System.out.println(lowerLeftY + " ~ " + upperRightY);
	        
	        // 폰트 변경
            FONT = PDType1Font.HELVETICA; // 영문
            InputStream in  = new FileInputStream("font/NanumGothicCoding.ttf");
            FONT = PDType0Font.load(document, in);
            System.out.println("FONT.getStringWidth(\"ABC123\") : " + FONT.getStringWidth("ABC123"));
            float size = FONT.getStringWidth("ABC123") / 1000;
            System.out.println(size);
            System.out.println(size*FONT_SIZE);
            
			document.addPage(page); // 페이지 추가
			document.save(destFileName); // 저장
			System.out.println(destFileName + "PDF 작성완료");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

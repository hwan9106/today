package pdfbox.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

public class EX05_CreateMultiPositionPDF {
	public static void main(String[] args) {
		String destFileName = "pdf_example/EX05_CreateMultiPositionPDF.pdf";
		try (PDDocument document = new PDDocument()) {// 문서작성
			PDPage blankPage = new PDPage(PDRectangle.A4); // A4 크기의 페이지 작성
			document.addPage(blankPage); // 페이지 추가
			
			PDRectangle pageSize = blankPage.getMediaBox(); 
			float pageWidth = pageSize.getWidth(); // 폭
			float pageHeight = pageSize.getHeight(); // 높이
			System.out.println(pageSize);
			System.out.println(pageWidth + ", " + pageHeight);
			
            // 폰트 변경
            InputStream in  = new FileInputStream("font/NanumGothicCoding.ttf");
			PDType0Font korfont = PDType0Font.load(document, in);
			int fontWidth = 12, fontHeight = 12;
			PDPageContentStream contentStream = new PDPageContentStream(document, blankPage);
			
			// 선그리기
			contentStream.moveTo(0,0);// 선 그리기 시작 위치로 이동합니다.
			contentStream.lineTo(pageWidth,pageHeight);// 끝 위치를 지정합니다.
			contentStream.stroke();// 선을 그립니다.
			contentStream.moveTo(0, pageHeight);
			contentStream.lineTo(pageWidth,0);
			contentStream.stroke();// 선을 그립니다.
			
			contentStream.setFont( korfont,10);
			contentStream.beginText();
			
			// PDFBox는 1사분면 좌표계를 사용합니다. 즉, 좌측하단이 (0,0) 입니다.
			showText(contentStream, 0, pageHeight-fontHeight, "좌상");
			showText(contentStream, pageWidth-fontWidth*2, 0, "우상");
			showText(contentStream, 0, -(pageHeight-fontHeight)+5, "우하");
			showText(contentStream, -(pageWidth-fontWidth*2), 0, "좌하");
			showText(contentStream, pageWidth/2-fontWidth, pageHeight/2-fontHeight/2, "가운데");
			
			contentStream.endText();
			contentStream.close();
			
			document.save(destFileName); // 저장
			System.out.println(destFileName + "PDF 작성완료");
		} catch (IOException e) {
			e.printStackTrace();
		}
		ChromeView.view(destFileName); // 작성된 문서보기
	}
	private static void showText(PDPageContentStream contentStream, float tx, float ty, String message) {
		try {
			// 상대 좌표이다.
			contentStream.newLineAtOffset(tx, ty);
			contentStream.showText(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

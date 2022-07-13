package pdfbox.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class EX04_CreateKoreanMultiLinePDF {
	public static void main(String[] args) {
		String destFileName = "pdf_example/EX04_CreateKoreanMultiLinePDF.pdf";
		try (PDDocument document = new PDDocument()) {// 문서작성
			PDPage blankPage = new PDPage(PDRectangle.A4); // A4 크기의 페이지 작성
			document.addPage(blankPage); // 페이지 추가
			
			// 한글 출력 출력
			PDPageContentStream contentStream = new PDPageContentStream(document, blankPage);
			contentStream.setLeading(18.5f); // 텍스트 행간 설정 
			contentStream.beginText();
			contentStream.setFont(PDType1Font.HELVETICA, 12);
			contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Hello World!!!");
            contentStream.newLine();
            
            // 한글 폰트로 변경(지정 폰트 파일이 있어야 한다.)
            InputStream in  = new FileInputStream("font/NanumGothicCoding.ttf");
			PDType0Font korfont = PDType0Font.load(document, in);
			contentStream.setFont(korfont, 12);
			
			// 폰트 변경을 하지 않으면 이부분 에러 발생
            contentStream.showText("반갑다 PDFBox!!");
            contentStream.newLine();
            
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

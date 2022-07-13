package pdfbox.example;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class EX16_ReadText {

	public static void main(String[] args) {
		String destFileName = "pdf_example/EX15_RemovePage.pdf";
		try ( PDDocument document = PDDocument.load(new File(destFileName)); ) {
			int numberOfPages = document.getNumberOfPages(); // 전체 페이지수 얻기
			System.out.println("전체페이지 : " + numberOfPages + "페이지\n");
			// 텍스트를 분리해 낸다.
			PDFTextStripper pdfStripper = new PDFTextStripper();
			String text = pdfStripper.getText(document);
			System.out.println("읽은 내용");
			System.out.println("------------------------------------------------------");
			System.out.println(text);
			System.out.println("------------------------------------------------------");

			System.out.println("\nPDF 페이지 내용 읽기 완료");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

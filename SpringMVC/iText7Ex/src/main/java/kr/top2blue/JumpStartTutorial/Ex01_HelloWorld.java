package kr.top2blue.JumpStartTutorial;

import java.io.File;
import java.io.FileNotFoundException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class Ex01_HelloWorld {
	static String destFileName = "JumpStartTutorial/result/Ex01_HelloWorld.pdf"; 
	public static void main(String[] args) {
		makePdf(destFileName);
		PDFView.viewChrome(destFileName);
	}
	
	private static void makePdf(String fileName) {
		// 지정 파일의 상위 폴더가 없으면 만들어 준다.
		File destFile = new File(fileName);
		destFile.getParentFile().mkdirs();
		// PDF 문서 만들기
		PdfWriter pdfWriter = null;
		try {
			pdfWriter = new PdfWriter(destFile);// PdfWriter객체 생성
			PdfDocument pdfDocument = new PdfDocument(pdfWriter); // PdfDocument객체 생성 
			Document document = new Document(pdfDocument); // Document객체 생성 
			document.add(new Paragraph("Hello World!!!")); // 문단 추가
			document.close(); // Document객체 닫기 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}

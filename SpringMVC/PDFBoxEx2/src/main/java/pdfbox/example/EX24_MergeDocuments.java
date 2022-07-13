package pdfbox.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.pdfbox.multipdf.PDFMergerUtility;

public class EX24_MergeDocuments {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		String destFileName = "pdf_example/EX24_MergeDocuments.pdf";
		PDFMergerUtility PDFmerger = new PDFMergerUtility();
		PDFmerger.setDestinationFileName(destFileName);
		try {
			for (int i = 1; i <= 4; i++) {
				PDFmerger.addSource(new File("pdf_example/sample_" + String.format("%02d", i) + ".pdf"));
			}
			PDFmerger.mergeDocuments();
			ChromeView.view(destFileName);
			System.out.println("PDF파일 합치기 완료");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

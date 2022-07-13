package kr.top2blue.JumpStartTutorial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.forms.PdfPageFormCopier;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

// 2개의 파일의 폼을 합쳐보자
public class Ex35_Combine_Forms {
	static String srcFileName1 = "src/main/resources/pdf/subscribe.pdf";
	static String srcFileName2 = "src/main/resources/pdf/state.pdf";
	static String destFileName = "JumpStartTutorial/result/Ex35_Combine_Forms.pdf";

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
			PdfDocument[] sources = new PdfDocument[] { 
						new PdfDocument(new PdfReader(srcFileName1)),
						new PdfDocument(new PdfReader(srcFileName2)) 
					};
			
			PdfPageFormCopier formCopier = new PdfPageFormCopier();
	        for (PdfDocument sourcePdfDocument : sources) {
	            sourcePdfDocument.copyPagesTo(1, sourcePdfDocument.getNumberOfPages(), pdfDocument, formCopier);
	            sourcePdfDocument.close();
	        }
	        
			pdfDocument.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

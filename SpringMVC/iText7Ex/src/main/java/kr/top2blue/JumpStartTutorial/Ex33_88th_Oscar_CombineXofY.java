package kr.top2blue.JumpStartTutorial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.utils.PdfMerger;

// 2개의 파일의 원하는 페이지만 합친다.
public class Ex33_88th_Oscar_CombineXofY {
	static String srcFileName1 = "src/main/resources/pdf/88th_reminder_list.pdf";
	static String srcFileName2 = "src/main/resources/pdf/88th_noms_announcement.pdf";
	static String destFileName = "JumpStartTutorial/result/Ex33_88th_Oscar_CombineXofY.pdf";

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
			PdfDocument sourcePdf1 = new PdfDocument(new PdfReader(srcFileName1)); // 원본읽기
			PdfDocument sourcePdf2 = new PdfDocument(new PdfReader(srcFileName2)); // 원본읽기
			
			// PdfMerger 객체 생성해서 합치기
			PdfMerger merger = new PdfMerger(pdfDocument);
			merger.merge(sourcePdf1, Arrays.asList(1, 5, 7, 1)); // (원본, 결합할페이지번호 리스트)
			merger.merge(sourcePdf2, Arrays.asList(1, 15));
			
			sourcePdf1.close();
			sourcePdf2.close();
	        pdfDocument.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

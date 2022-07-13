package kr.top2blue.JumpStartTutorial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

public class Ex10_NationalAnthem {
	static String destFileName = "JumpStartTutorial/result/Ex10_NationalAnthem.pdf";
	static String DATA = "src/main/resources/data/NationalAnthem.txt";
	static String fontFile = "font/NanumGothicCoding.ttf";
	
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
			PageSize pageSize = PageSize.A4; // A4페이지 사이즈
			PdfPage pdfPage = pdfDocument.addNewPage(pageSize); // 페이지 추가
			PdfCanvas canvas = new PdfCanvas(pdfPage); // 콘텐츠 스트림에 데이터를 쓰기 위한 PdfCanvas객체 생성
			List<String> text = Files.readAllLines(Paths.get(DATA));

			canvas.concatMatrix(1, 0, 0, 1, 0, pageSize.getHeight());
			canvas.beginText().setFontAndSize(PdfFontFactory.createFont(fontFile, PdfEncodings.IDENTITY_H), 10)
					.setLeading(14 * 1.2f).moveText(70, -40);
			for (String s : text) {
				canvas.newlineShowText(s);
			}
			canvas.endText();

			pdfDocument.close(); // Document객체 닫기
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package kr.top2blue.JumpStartTutorial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

public class Ex11_StarWarsCrawl {
	static String destFileName = "JumpStartTutorial/result/Ex11_StarWarsCrawl.pdf";
	static String DATA = "src/main/resources/data/StarWars.txt";

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

			int maxStringWidth = 0;
	        for (String fragment : text) {
	            if (fragment.length() > maxStringWidth) maxStringWidth = fragment.length();
	        }
			//Set black background
	        canvas.rectangle(0, 0, pageSize.getWidth(), pageSize.getHeight())
	                .setColor(ColorConstants.BLACK, true)
	                .fill();

	        //Replace the origin of the coordinate system to the top left corner
	        canvas.concatMatrix(1, 0, 0, 1, 0, pageSize.getHeight());
	        Color yellowColor = new DeviceCmyk(0.f, 0.0537f, 0.769f, 0.051f);
	        float lineHeight = 5;
	        float yOffset = -40;
	        canvas.beginText()
	            .setFontAndSize(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD), 1)
	            .setColor(yellowColor, true);
	        for (int j = 0; j < text.size(); j++) {
	            String line = text.get(j);
	            float xOffset = pageSize.getWidth() / 2 - 45 - 8 * j;
	            float fontSizeCoeff = 6 + j;
	            float lineSpacing = (lineHeight + j) * j / 1.5f;
	            int stringWidth = line.length();
	            for (int i = 0; i < stringWidth; i++) {
	                float angle = (maxStringWidth / 2 - i) / 2f;
	                float charXOffset = (4 + (float) j / 2) * i;
	                canvas.setTextMatrix(fontSizeCoeff, 0,
	                        angle, fontSizeCoeff / 1.5f,
	                        xOffset + charXOffset, yOffset - lineSpacing)
	                    .showText(String.valueOf(line.charAt(i)));
	            }
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

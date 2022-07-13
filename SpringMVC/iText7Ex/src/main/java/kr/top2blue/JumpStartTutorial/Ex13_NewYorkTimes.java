package kr.top2blue.JumpStartTutorial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.ColumnDocumentRenderer;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

public class Ex13_NewYorkTimes {
	static String destFileName = "JumpStartTutorial/result/Ex13_NewYorkTimes.pdf";
	static String APPLE_IMG = "src/main/resources/img/ny_times_apple.jpg";
	static String APPLE_TXT = "src/main/resources/data/ny_times_apple.txt";
	static String FACEBOOK_IMG = "src/main/resources/img/ny_times_fb.jpg";
	static String FACEBOOK_TXT = "src/main/resources/data/ny_times_fb.txt";
	static String INST_IMG = "src/main/resources/img/ny_times_inst.jpg";
	static String INST_TXT = "src/main/resources/data/ny_times_inst.txt";

	static String fontFile = "font/NanumGothicCoding-Bold.ttf";
	static PdfFont timesNewRoman = null;
	static PdfFont timesNewRomanBold = null;

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
			timesNewRoman = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
			timesNewRomanBold = PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);

			pdfWriter = new PdfWriter(destFile);// PdfWriter객체 생성
			PdfDocument pdfDocument = new PdfDocument(pdfWriter); // PdfDocument객체 생성
			PageSize pageSize = PageSize.A5; // A5페이지 사이즈
			Document document = new Document(pdfDocument, pageSize);

			// 다단 문서를 만들어 보자!!!
	        float offSet = 36; // 여백
	        float columnWidth = (pageSize.getWidth() - offSet * 2 + 10) / 3; // 단의 폭을 계산
	        float columnHeight = pageSize.getHeight() - offSet * 2; // 높이 계산

	        // 단의 폭을 지정한 배열 
	        Rectangle[] columns = {	
	        		new Rectangle(offSet - 5, offSet, columnWidth, columnHeight),
	                new Rectangle(offSet + columnWidth, offSet, columnWidth, columnHeight),
	                new Rectangle(offSet + columnWidth * 2 + 5, offSet, columnWidth, columnHeight)
	                };
	        // 다단 문서 생성
	        document.setRenderer(new ColumnDocumentRenderer(document, columns));
	        // 내용 추가
	        Image apple = new Image(ImageDataFactory.create(APPLE_IMG)).setWidth(columnWidth);
	        String articleApple = Files.readString(Paths.get(APPLE_TXT));
	        addArticle(document, "Apple Encryption Engineers, if Ordered to Unlock iPhone, Might Resist", "By JOHN MARKOFF MARCH 18, 2016", apple, articleApple);
	        Image facebook = new Image(ImageDataFactory.create(FACEBOOK_IMG)).setWidth(columnWidth);
	        String articleFB = Files.readString(Paths.get(FACEBOOK_TXT));
	        addArticle(document, "With \"Smog Jog\" Through Beijing, Zuckerberg Stirs Debate on Air Pollution", "By PAUL MOZUR MARCH 18, 2016", facebook, articleFB);
	        Image inst = new Image(ImageDataFactory.create(INST_IMG)).setWidth(columnWidth);
	        String articleInstagram = Files.readString(Paths.get(INST_TXT));
	        addArticle(document, "Instagram May Change Your Feed, Personalizing It With an Algorithm","By MIKE ISAAC MARCH 15, 2016", inst, articleInstagram);
			
			pdfDocument.close(); // Document객체 닫기
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 내용을 추가해주는 메서드
	public static void addArticle(Document doc, String title, String author, Image img, String text){
		doc.add(new Paragraph(title).setFont(timesNewRomanBold).setFontSize(14)); // 제목 추가
		doc.add(img);// 이미지 추가
		doc.add(new Paragraph().setFont(timesNewRoman).setFontSize(7).setFontColor(ColorConstants.GRAY).add(author)); // 이미지 하단 작가 추가
		doc.add(new Paragraph().setFont(timesNewRoman).setFontSize(10).add(text)); // 내용 추가
	}

}

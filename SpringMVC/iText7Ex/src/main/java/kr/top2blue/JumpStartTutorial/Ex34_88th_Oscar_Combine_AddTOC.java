package kr.top2blue.JumpStartTutorial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfArray;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfNumber;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.canvas.draw.DottedLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.Property;
import com.itextpdf.layout.property.TabAlignment;
import com.itextpdf.layout.property.TextAlignment;

// 2개의 파일의 원하는 페이지만 합치면서 목차를 추가해 보자
public class Ex34_88th_Oscar_Combine_AddTOC {
	static String srcFileName1 = "src/main/resources/pdf/88th_reminder_list.pdf";
	static String srcFileName2 = "src/main/resources/pdf/oscars_movies_checklist_2016.pdf";
	static String destFileName = "JumpStartTutorial/result/Ex34_88th_Oscar_Combine_AddTOC.pdf";
	
	// 목차로 사용할 정렬을 지원하는 맵
	public static final Map<String, Integer> TheRevenantNominations = new TreeMap<String, Integer>();
    static {
        TheRevenantNominations.put("Performance by an actor in a leading role", 4);
        TheRevenantNominations.put("Performance by an actor in a supporting role", 4);
        TheRevenantNominations.put("Achievement in cinematography", 4);
        TheRevenantNominations.put("Achievement in costume design", 5);
        TheRevenantNominations.put("Achievement in directing", 5);
        TheRevenantNominations.put("Achievement in film editing", 6);
        TheRevenantNominations.put("Achievement in makeup and hairstyling", 7);
        TheRevenantNominations.put("Best motion picture of the year", 8);
        TheRevenantNominations.put("Achievement in production design", 8);
        TheRevenantNominations.put("Achievement in sound editing", 9);
        TheRevenantNominations.put("Achievement in sound mixing", 9);
        TheRevenantNominations.put("Achievement in visual effects", 10);
    }

	public static void main(String[] args) {
		makePdf(destFileName);
		PDFView.viewChrome(destFileName);
	}

	@SuppressWarnings("resource")
	private static void makePdf(String fileName) {
		// 지정 파일의 상위 폴더가 없으면 만들어 준다.
		File destFile = new File(fileName);
		destFile.getParentFile().mkdirs();
		// PDF 문서 만들기
		PdfWriter pdfWriter = null;

		try {
			pdfWriter = new PdfWriter(destFile);// PdfWriter객체 생성
			PdfDocument pdfDocument = new PdfDocument(pdfWriter); // PdfDocument객체 생성
			// 문서 생성해서 제목을 붙인다.
			Document document = new Document(pdfDocument);
	        document.add(new Paragraph(new Text("The Revenant nominations list"))
	            .setTextAlignment(TextAlignment.CENTER));
	        // 원본 읽기
			PdfDocument sourcePdf1 = new PdfDocument(new PdfReader(srcFileName1)); // 원본읽기
			// 맵 반복
	        for (Map.Entry<String, Integer> entry : TheRevenantNominations.entrySet()) {
	            // Copy page
	            PdfPage page  = sourcePdf1.getPage(entry.getValue()).copyTo(pdfDocument); // 원본에서 맵에 저장된 페이지를 복사
	            pdfDocument.addPage(page); // 페이지 추가

	            //페이비 번호를 만든다
	            Text text = new Text(String.format("Page %d", pdfDocument.getNumberOfPages() - 1));
	            text.setBackgroundColor(ColorConstants.WHITE);
	            // 페이지 우측 상단에 새로운 페이지 번호를 넣는다.
	            document.add(new Paragraph(text).setFixedPosition(pdfDocument.getNumberOfPages(), 549, 742, 100));

	            // 목차를 만든다.
	            String destinationKey = "p" + (pdfDocument.getNumberOfPages() - 1);
	            PdfArray destinationArray = new PdfArray();
	            destinationArray.add(page.getPdfObject());
	            destinationArray.add(PdfName.XYZ);
	            destinationArray.add(new PdfNumber(0));
	            destinationArray.add(new PdfNumber(page.getMediaBox().getHeight()));
	            destinationArray.add(new PdfNumber(1));
	            pdfDocument.addNamedDestination(destinationKey, destinationArray);

	            // 클릭시 이동할 북마크를 설정한다.
	            Paragraph paragraph = new Paragraph(); // 문단작성
	            paragraph.addTabStops(new TabStop(540, TabAlignment.RIGHT, new DottedLine())); // 지정위치까지 선그리기 탭 설정
	            paragraph.add(entry.getKey()); 	// 맵에서 키값을 얻어오기
	            paragraph.add(new Tab());		// 탭 추가
	            paragraph.add(String.valueOf(pdfDocument.getNumberOfPages() - 1)); // 페이지 번호 추가
	            paragraph.setProperty(Property.ACTION, PdfAction.createGoTo(destinationKey)); // 액션 추가
	            document.add(paragraph);
	        }		
	        sourcePdf1.close();
	        
	        // 원본 읽기
	     	PdfDocument sourcePdf2 = new PdfDocument(new PdfReader(srcFileName2)); // 원본읽기
	     	// 두번째 파일에서 1페이지를 얻어 문서에 추가
	     	PdfPage page  = sourcePdf2.getPage(1).copyTo(pdfDocument);
	     	pdfDocument.addPage(page);

	        // 추가한 페이지에 대한 목차와 액션을 단다.
	        PdfArray destinationArray = new PdfArray();
	        destinationArray.add(page.getPdfObject());
	        destinationArray.add(PdfName.XYZ);
	        destinationArray.add(new PdfNumber(0));
	        destinationArray.add(new PdfNumber(page.getMediaBox().getHeight()));
	        destinationArray.add(new PdfNumber(1));
	        pdfDocument.addNamedDestination("checklist", destinationArray);

	        Paragraph p = new Paragraph();
	        p.addTabStops(new TabStop(540, TabAlignment.RIGHT, new DottedLine()));
	        p.add("Oscars\u00ae 2016 Movie Checklist");
	        p.add(new Tab());
	        p.add(String.valueOf(pdfDocument.getNumberOfPages() - 1));
	        p.setProperty(Property.ACTION, PdfAction.createGoTo("checklist"));
	        document.add(p);
	        
	        sourcePdf2.close();
	        
	        pdfDocument.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package pdfbox.example;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;

public class EX21_DocumentInformation {
	public static void main(String[] args) {
		String destFileName = "pdf_example/EX21_DocumentInformation.pdf";
		try (PDDocument document = new PDDocument();) {// 문서생성
			document.addPage(new PDPage()); // 페이지 추가
			// 속성 설정
			PDDocumentInformation pdd = document.getDocumentInformation();
			pdd.setAuthor("한사람");
			pdd.setTitle("연습용 문서 작성");
			pdd.setCreator("PDF Examples");
			pdd.setSubject("연습용 파일");
			Calendar date = Calendar.getInstance();
			date.set(2021, 1, 5);
			pdd.setCreationDate(date);
			date.set(2021, 10, 18);
			pdd.setModificationDate(date);
			pdd.setKeywords("sample, first example, my pdf");
			document.save(destFileName); // 저장
			System.out.println("PDF 파일 속성 저장 완료");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try (PDDocument document = PDDocument.load(new File(destFileName));) {// 문서읽기
			System.out.println("PDF 읽기완료!!");
			System.out.println("PDF 파일속성!!");
			PDDocumentInformation pdd = document.getDocumentInformation();
			System.out.println("Author : " + pdd.getAuthor());
			System.out.println("Title : " + pdd.getTitle());
			System.out.println("Creator : " + pdd.getCreator());
			System.out.println("Subject : " + pdd.getSubject());
			System.out.println("CreationDate : " + sdf.format(pdd.getCreationDate().getTime()));
			System.out.println("ModificationDate : " + sdf.format(pdd.getModificationDate().getTime()));
			System.out.println("Keywords : " + pdd.getKeywords());
		} catch (IOException e) {
			e.printStackTrace();
		}
		ChromeView.view(destFileName);
	}
}

package pdfbox.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.apache.pdfbox.pdmodel.PDDocument;

public class EX15_RemovePage {

	public static void main(String[] args) {
		String srcFileName = "pdf_example/EX14_AddPage.pdf";
		String destFileName = "pdf_example/EX15_RemovePage.pdf";
		// 파일 복사
		try {
			Files.copy(new File(srcFileName).toPath(), new File(destFileName).toPath(),StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try ( PDDocument document = PDDocument.load(new File(destFileName)); ) {
			int numberOfPages = document.getNumberOfPages(); // 전체 페이지수 얻기
			System.out.println("전체페이지 : " + numberOfPages + "페이지");
			
			// 페이지 삭제
			document.removePage(numberOfPages-1); // 페이지 삭제(index 0부터 시작)
			document.removePage(1); // 페이지 삭제(index 0부터 시작)
			
			numberOfPages = document.getNumberOfPages(); // 전체 페이지수 얻기
			System.out.println("전체페이지 : " + numberOfPages + "페이지");
			
			document.save(destFileName); // 저장
			
			ChromeView.view(destFileName); // 보기
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

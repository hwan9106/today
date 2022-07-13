package pdfbox.example;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class EX01_CreateEmptyPdf  {
	public static void main(String[] args) {
		String destFileName = "pdf_example/EX01_CreateEmptyPdf.pdf";
		try (PDDocument document = new PDDocument()) {// 문서작성
			PDPage blankPage = new PDPage(); // 페이지 작성
			document.addPage(blankPage); // 페이지 추가
			document.save(destFileName); // 저장
			System.out.println(destFileName + "PDF 작성완료");
		} catch (IOException e) {
			e.printStackTrace();
		}
		chromeView(destFileName); // 작성된 문서보기
	}

	// 저장된 문서 크롬으로 보기
	private static void chromeView(String destFileName) {
		String chrome = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
		try {
			File file = new File(destFileName); // 파일 객체 생성
			new ProcessBuilder(chrome, file.getAbsolutePath()).start(); // 프로세스 시작
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

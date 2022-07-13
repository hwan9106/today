package pdfbox.example;

import java.io.File;
import java.io.IOException;

public class ChromeView {
	public static void view(String destFileName) {
		String chrome = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
		try {
			File file = new File(destFileName); // 파일 객체 생성
			new ProcessBuilder(chrome, file.getAbsolutePath()).start(); // 프로세스 시작
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}

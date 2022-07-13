package kr.top2blue.JumpStartTutorial;

import java.io.File;
import java.io.IOException;

public class PDFView {
	public static void viewChrome(String destFileName) {
		String chrome = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
		try {
			File file = new File(destFileName); // 파일 객체 생성
			new ProcessBuilder(chrome, file.getAbsolutePath()).start(); // 프로세스 시작
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	public static void viewAcrobat(String destFileName) {
		String chrome = "C:\\Program Files (x86)\\Adobe\\Acrobat DC\\Acrobat\\Acrobat.exe";
		try {
			File file = new File(destFileName); // 파일 객체 생성
			new ProcessBuilder(chrome, file.getAbsolutePath()).start(); // 프로세스 시작
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}

package kr.green.poi;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class Ex01 {
	public static void main(String[] args) {
		HSSFWorkbook workbook = null;
		String filename = "Ex01.xlsx";
		// 저장할 파일 지정
		try (FileOutputStream stream = new FileOutputStream(filename)) {
			// 1. 엑셀문서 만들기
			workbook = new HSSFWorkbook();
			// Workbook안에 시트 생성.
			Sheet sheet = workbook.createSheet("Sheet1");
			// Sheet에서 행 취득
			Row row = sheet.getRow(0);
			if (row == null) {
				row = sheet.createRow(0);
			}
			// 행으로부터 열 취득
			Cell cell = row.getCell(0);
			if (cell == null) {
				cell = row.createCell(0);
			}
			// 셀에 데이터 작성
			cell.setCellValue("Hello World!!!");
			
			// 열크기 자동설정
			sheet.autoSizeColumn(0);
			
			// 문서 저장
			workbook.write(stream);
			System.out.println( filename + "파일 저장 완료");
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			try {
				if(workbook!=null) workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

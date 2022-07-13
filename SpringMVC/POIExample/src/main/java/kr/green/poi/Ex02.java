package kr.green.poi;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class Ex02 {
	public static void main(String[] args) {
		HSSFWorkbook workbook = null;
		String filename = "Ex02.xlsx";
		// 저장할 파일 지정
		try (FileOutputStream stream = new FileOutputStream(filename)) {
			// 1. 엑셀문서 만들기
			workbook = new HSSFWorkbook();
			// Workbook안에 시트 생성.
			Sheet sheet = workbook.createSheet("Sheet1");
			sheet.setDefaultColumnWidth(10);
			sheet.setDefaultRowHeightInPoints(30);
			for (int i = 0; i < 10; i++) { // 10줄
				// Sheet에서 행 취득
				Row row = sheet.getRow(i);
				if (row == null) {
					row = sheet.createRow(i);
				}
				for (int j = 0; j < 10; j++) { // 10칸
					// 행으로부터 열 취득
					Cell cell = row.getCell(j);
					if (cell == null) {
						cell = row.createCell(j);
					}
					// 셀에 데이터 작성
					cell.setCellValue((i+1)*(j+1));
				}
				// 가로합계 지정
				Cell cell = row.getCell(10);
				if (cell == null) {
					cell = row.createCell(10);
				}
				cell.setCellFormula("sum(A" + (i+1) +":J" + (i+1) + ")");
			}
			// 세로합계 지정
			Row row = sheet.getRow(10);
			if (row == null) {
				row = sheet.createRow(10);
			}
			for (int i = 0; i < 10; i++) { // 10칸
				// 행으로부터 열 취득
				Cell cell = row.getCell(i);
				if (cell == null) {
					cell = row.createCell(i);
				}
				// 셀에 데이터 작성
				cell.setCellFormula("sum(" + (char)('A'+i)  + "1:" + (char)('A'+i) + "10)");
			}
			// 문서 저장
			workbook.write(stream);
			System.out.println(filename + "파일 저장 완료");
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			try {
				if (workbook != null)
					workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

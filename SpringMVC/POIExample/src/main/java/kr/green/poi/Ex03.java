package kr.green.poi;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Ex03 {
	public static void main(String[] args) {
		XSSFWorkbook workbook = null;
		String filename = "Ex03.xlsx";
		// 1. 엑셀문서 읽기
		try (FileInputStream stream = new FileInputStream(filename)) {
			workbook = new XSSFWorkbook(stream);
			// 2. 시트 얻기
			Sheet sheet = workbook.getSheetAt(0);
			// 3. 행얻기
			Row row = sheet.getRow(0);
			if (row == null) {
				row = sheet.createRow(0);
			}
			// 열얻기
			Cell cell = row.getCell(0);
			if (cell == null) {
				cell = row.createCell(0);
			}
			// String값읽기
			System.out.println("값 : " + cell.getStringCellValue());
			// 숫자값읽기
			cell = row.getCell(1);
			System.out.println("값 : " + cell.getNumericCellValue());
			// 논리값읽기
			cell = row.getCell(2);
			System.out.println("값 : " + cell.getBooleanCellValue());
			
			// 타입 판단해서 읽기
			for(int i=0;i<3;i++) {
				cell = row.getCell(i);
				System.out.print("cell.getCellType() : "  + cell.getCellType() + "(값 : " );
			    switch (cell.getCellType()) {
			        case BOOLEAN:
			            System.out.print(cell.getBooleanCellValue());
			            break;
			        case NUMERIC:
			            System.out.print(cell.getNumericCellValue());
			            break;
			        case STRING:
			            System.out.print(cell.getRichStringCellValue());
			            break;
			    }
			    System.out.println(")");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

package kr.green.poi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class POIEx02 {
	public static void main(String... args) {
		new POIEx02();
	}

	public POIEx02() {
		// String version = "xls";
		String version = "xlsx";
		// Workbook 취득.
		Workbook workbook = getWorkbook("Test2.xlsx", version);
		// Workbook안에 시트 취득.
		Sheet sheet = workbook.getSheetAt(0);
		// Sheet에서 셀 취득 후 데이터 설정
		// 1월 오전
		getCell(sheet, 1, 2).setCellValue(-5);
		// 1월 오후
		getCell(sheet, 1, 3).setCellValue(0);
		// 2월 오전
		getCell(sheet, 2, 2).setCellValue(-10);
		// 2월 오후
		getCell(sheet, 2, 3).setCellValue(-5);
		// 3월 오전
		getCell(sheet, 3, 2).setCellValue(0);
		// 3월 오후
		getCell(sheet, 3, 3).setCellValue(2);
		// 4월 오전
		getCell(sheet, 4, 2).setCellValue(4);
		// 4월 오후
		getCell(sheet, 4, 3).setCellValue(10);
		// 5월 오전
		getCell(sheet, 5, 2).setCellValue(10);
		// 5월 오후
		getCell(sheet, 5, 3).setCellValue(15);
		// 6월 오전
		getCell(sheet, 6, 2).setCellValue(18);
		// 6월 오후
		getCell(sheet, 6, 3).setCellValue(25);
		// 7월 오전
		getCell(sheet, 7, 2).setCellValue(23);
		// 7월 오후
		getCell(sheet, 7, 3).setCellValue(28);
		// 8월 오전
		getCell(sheet, 8, 2).setCellValue(25);
		// 8월 오후
		getCell(sheet, 8, 3).setCellValue(31);
		// 9월 오전
		getCell(sheet, 9, 2).setCellValue(25);
		// 9월 오후
		getCell(sheet, 9, 3).setCellValue(29);
		// 10월 오전
		getCell(sheet, 10, 2).setCellValue(15);
		// 10월 오후
		getCell(sheet, 10, 3).setCellValue(25);
		// 11월 오전
		getCell(sheet, 11, 2).setCellValue(11);
		// 11월 오후
		getCell(sheet, 11, 3).setCellValue(17);
		// 12월 오전
		getCell(sheet, 12, 2).setCellValue(5);
		// 12월 오후
		getCell(sheet, 12, 3).setCellValue(9);
		// 함수값 재 설정
		for (int i = 1; i <= 12; i++) {
			getCell(sheet, i, 1).setCellFormula(String.format("AVERAGE(C%d:D%d)", i + 1, i + 1));
		}
		writeExcel(workbook, "result." + version);
	}

	// Workbook 읽어드리기
	public Workbook getWorkbook(String filename, String version) {
		try (FileInputStream stream = new FileInputStream(filename)) {
			//표준 xls 버젼
			if ("xls".equals(version)) {
				return new HSSFWorkbook(stream);
				//확장 xlsx 버젼
			} else if ("xlsx".equals(version)) {
				return new XSSFWorkbook(stream);
			}
			throw new NoClassDefFoundError();
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}

	// Sheet로 부터 Row를 취득, 생성하기
	public Row getRow(Sheet sheet, int rownum) {
		Row row = sheet.getRow(rownum);
		if (row == null) {
			row = sheet.createRow(rownum);
		}
		return row;
	}

	// Row로 부터 Cell를 취득, 생성하기
	public Cell getCell(Row row, int cellnum) {
		Cell cell = row.getCell(cellnum);
		if (cell == null) {
			cell = row.createCell(cellnum);
		}
		return cell;
	}

	public Cell getCell(Sheet sheet, int rownum, int cellnum) {
		Row row = getRow(sheet, rownum);
		return getCell(row, cellnum);
	}

	public void writeExcel(Workbook workbook, String filepath) {
		try (FileOutputStream stream = new FileOutputStream(filepath)) {
			workbook.write(stream);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}

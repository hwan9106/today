package kr.green.poi;

import java.io.FileOutputStream;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

public class POIEx01 {
	public static void main(String[] args) {
		// String version = "xls";
		String version = "xlsx";
		// Workbook 생성.
		try {
			Workbook workbook = createWorkbook(version);
			// Workbook안에 시트 생성.
			Sheet sheet = workbook.createSheet("Test Sheet");
			// Sheet에서 셀 취득
			Cell cell = getCell(sheet, 0, 0);
			// 셀에 데이터 작성
			cell.setCellValue("TEST Result");
			cell = getCell(sheet, 0, 1);
			cell.setCellValue(100);
			cell = getCell(sheet, 0, 2);
			cell.setCellValue(Calendar.getInstance().getTime());
			// 셀에 데이터 포멧 지정
			CellStyle style = workbook.createCellStyle();
			// 날짜 포멧
			style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
			// 정렬 포멧
			style.setAlignment(HorizontalAlignment.CENTER);
			style.setVerticalAlignment(VerticalAlignment.TOP);
			// 셀 색지정
			style.setFillBackgroundColor(IndexedColors.GOLD.index);
			// 폰트 설정
			Font font = workbook.createFont();
			font.setColor(IndexedColors.RED.index);
			cell.setCellStyle(style);
			// 셀 너비 자동 지정
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			cell = getCell(sheet, 1, 0);
			cell.setCellValue(1);
			cell = getCell(sheet, 1, 1);
			cell.setCellValue(2);
			cell = getCell(sheet, 1, 2);
			// 함수식
			cell.setCellFormula("SUM(A2:B2)");
			writeExcel(workbook, "test." + version);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Workbook 생성
	public static Workbook createWorkbook(String version) {
		// 표준 xls 버젼
		if ("xls".equals(version)) {
			return new HSSFWorkbook();
			// 확장 xlsx 버젼
		} else if ("xlsx".equals(version)) {
			return new HSSFWorkbook();
		}
		throw new NoClassDefFoundError();
	}

	// Sheet로 부터 Row를 취득, 생성하기
	public static Row getRow(Sheet sheet, int rownum) {
		Row row = sheet.getRow(rownum);
		if (row == null) {
			row = sheet.createRow(rownum);
		}
		return row;
	}

	// Row로 부터 Cell를 취득, 생성하기
	public static Cell getCell(Row row, int cellnum) {
		Cell cell = row.getCell(cellnum);
		if (cell == null) {
			cell = row.createCell(cellnum);
		}
		return cell;
	}

	public static Cell getCell(Sheet sheet, int rownum, int cellnum) {
		Row row = getRow(sheet, rownum);
		return getCell(row, cellnum);
	}

	public static void writeExcel(Workbook workbook, String filepath) {
		try (FileOutputStream stream = new FileOutputStream(filepath)) {
			workbook.write(stream);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}

package kr.top2blue.JumpStartTutorial;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.PdfPageFormCopier;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

// 1개의 폼을 읽어 데이터를 채우고 채워진 데이터 폼을 문서에 추가한다.
public class Ex36_FillOutAndMergeForms {
	static String srcFileName = "src/main/resources/pdf/state.pdf";
	static String DATA = "src/main/resources/data/united_states.csv";
	static String destFileName = "JumpStartTutorial/result/Ex36_FillOutAndMergeForms.pdf";

	public static void main(String[] args) {
		makePdf(destFileName);
		PDFView.viewChrome(destFileName);
	}

	private static void makePdf(String fileName) {
		// 지정 파일의 상위 폴더가 없으면 만들어 준다.
		File destFile = new File(fileName);
		destFile.getParentFile().mkdirs();
		// PDF 문서 만들기
		PdfWriter pdfWriter = null;

		try {
			pdfWriter = new PdfWriter(destFile);// PdfWriter객체 생성
			PdfDocument pdfDocument = new PdfDocument(pdfWriter); // PdfDocument객체 생성
			// PdfPageFormCopier객체 생성
			PdfPageFormCopier formCopier = new PdfPageFormCopier();
			// 모든 데이터 리스트로 읽기
			List<String> lines = Files.readAllLines(Paths.get(DATA));
			for (int i = 1; i < lines.size(); i++) { // 첫줄은 제외하고
				String line = lines.get(i); // 1줄얻기
				// 원본 폼파일을 읽어 문서 객체 생성
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				PdfDocument sourcePdfDocument = new PdfDocument(new PdfReader(srcFileName), new PdfWriter(baos));
				// 폼의 name속성의 이름을 변경 : 뒤에 숫자 붙임
				PdfAcroForm form = PdfAcroForm.getAcroForm(sourcePdfDocument, true);
				form.renameField("name", "name_" + i);
				form.renameField("abbr", "abbr_" + i);
				form.renameField("capital", "capital_" + i);
				form.renameField("city", "city_" + i);
				form.renameField("population", "population_" + i);
				form.renameField("surface", "surface_" + i);
				form.renameField("timezone1", "timezone1_" + i);
				form.renameField("timezone2", "timezone2_" + i);
				form.renameField("dst", "dst_" + i);
				
				// 데이터를 ;로 구분하여 각가의 폼을 채운다.
	            StringTokenizer tokenizer = new StringTokenizer(line, ";");
	            Map<String, PdfFormField> fields = form.getFormFields();
	            fields.get("name_" + i).setValue(tokenizer.nextToken());
	            fields.get("abbr_" + i).setValue(tokenizer.nextToken());
	            fields.get("capital_" + i).setValue(tokenizer.nextToken());
	            fields.get("city_" + i).setValue(tokenizer.nextToken());
	            fields.get("population_" + i).setValue(tokenizer.nextToken());
	            fields.get("surface_" + i).setValue(tokenizer.nextToken());
	            fields.get("timezone1_" + i).setValue(tokenizer.nextToken());
	            fields.get("timezone2_" + i).setValue(tokenizer.nextToken());
	            fields.get("dst_" + i).setValue(tokenizer.nextToken());
	            sourcePdfDocument.close();
	            // 채워진 폼으로 문서 다시 작성
	            sourcePdfDocument = new PdfDocument(new PdfReader(new ByteArrayInputStream(baos.toByteArray())));

	            // 채워진 폼을 출력할 문서에 복사한다.
	            sourcePdfDocument.copyPagesTo(1, sourcePdfDocument.getNumberOfPages(), pdfDocument, formCopier);
	            sourcePdfDocument.close();
			}

			pdfDocument.close();
			// 50페이지 분량의 문서가 작성됨
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

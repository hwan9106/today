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
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

// 1개의 폼을 읽어 일반 모드 와 스마트 모드로 데이터를 채우고 채워진 데이터 폼을 문서에 추가한다. -- 생성된 파일의 용량을 보세요 
/*
일반 모드 와 스마트 모드 의 차이점은 무엇인가요 ?
우리는에 작성 형태의 페이지를 복사 할 때 PdfDocument의 작업 정상 모드는 PdfDocument이 추가되고 다른 문서에 완전히 무관의 경우 
각 문서를 처리합니다. 이 경우 문서가 관련되어 있기 때문에 결과 문서가 부풀려집니다. 문서는 모두 동일한 템플릿을 공유합니다. 
해당 템플릿은 미국의 주 수만큼 PDF 문서에 추가됩니다. 이 경우 결과는 약 12MB의 파일입니다.

우리는에 작성 형태의 페이지를 복사 할 때 PdfDocument의 작업 스마트 모드는 PdfDocument각 문서의 자원을 비교하는 데 시간이 걸릴 것입니다. 
두 개의 개별 문서가 동일한 리소스(예: 템플릿)를 공유하는 경우 해당 리소스는 새 파일에 한 번만 복사됩니다. 
이 경우 결과는 365KB로 제한될 수 있습니다.

12MBytes 및 365KBytes 파일은 모두 PDF 뷰어에서 열거나 인쇄할 때 정확히 동일하게 보이지만 
365KBytes 파일이 12MBytes 파일보다 선호된다는 것은 말할 필요도 없습니다.
 */
public class Ex37_FillOutFlattenAndMergeForms {
	static String srcFileName = "src/main/resources/pdf/state.pdf";
	static String DATA = "src/main/resources/data/united_states.csv";
	static String destFileName1 = "JumpStartTutorial/result/Ex37_FillOutFlattenAndMergeForms.pdf";
	static String destFileName2 = "JumpStartTutorial/result/Ex37_FillOutFlattenAndSmartMergeForms.pdf";

	public static void main(String[] args) {
		makePdf(destFileName1, destFileName2);
		PDFView.viewChrome(destFileName1);
		PDFView.viewChrome(destFileName2);
	}

	@SuppressWarnings("resource")
	private static void makePdf(String fileName1, String fileName2) {
		// 지정 파일의 상위 폴더가 없으면 만들어 준다.
		File destFile1 = new File(fileName1);
		destFile1.getParentFile().mkdirs();
		
		File destFile2 = new File(fileName1);
		destFile2.getParentFile().mkdirs();
		
		// PDF 문서 만들기

		try {
			PdfDocument destPdfDocument = new PdfDocument(new PdfWriter(fileName1)); // 일반모드
	        PdfDocument destPdfDocumentSmartMode = new PdfDocument(new PdfWriter(fileName2).setSmartMode(true)); // 스마트 모드

			// 모든 데이터 리스트로 읽기
			List<String> lines = Files.readAllLines(Paths.get(DATA));
			for (int i = 1; i < lines.size(); i++) { // 첫줄은 제외하고
				String line = lines.get(i); // 1줄얻기
				// 원본 폼파일을 읽어 문서 객체 생성
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				PdfDocument sourcePdfDocument = new PdfDocument(new PdfReader(srcFileName), new PdfWriter(baos));
				// 필드를 읽는다.
	            PdfAcroForm form = PdfAcroForm.getAcroForm(sourcePdfDocument, true);
	            StringTokenizer tokenizer = new StringTokenizer(line, ";");
	            Map<String, PdfFormField> fields = form.getFormFields();
	            // 필드에 내용을 채운다.
	            fields.get("name").setValue(tokenizer.nextToken());
	            fields.get("abbr").setValue(tokenizer.nextToken());
	            fields.get("capital").setValue(tokenizer.nextToken());
	            fields.get("city").setValue(tokenizer.nextToken());
	            fields.get("population").setValue(tokenizer.nextToken());
	            fields.get("surface").setValue(tokenizer.nextToken());
	            fields.get("timezone1").setValue(tokenizer.nextToken());
	            fields.get("timezone2").setValue(tokenizer.nextToken());
	            fields.get("dst").setValue(tokenizer.nextToken());

	            // 필드를 병합한다.
	            form.flattenFields();
	            sourcePdfDocument.close();
	            sourcePdfDocument = new PdfDocument(new PdfReader(new ByteArrayInputStream(baos.toByteArray())));

	            // 페이지 복사
	            sourcePdfDocument.copyPagesTo(1, sourcePdfDocument.getNumberOfPages(), destPdfDocument, null); // 일반모드
	            sourcePdfDocument.copyPagesTo(1, sourcePdfDocument.getNumberOfPages(), destPdfDocumentSmartMode, null); // 스마트 모드

	            sourcePdfDocument.close();
			}

			destPdfDocument.close();
	        destPdfDocumentSmartMode.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

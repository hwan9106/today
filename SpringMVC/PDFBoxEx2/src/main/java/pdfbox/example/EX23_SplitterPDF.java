package pdfbox.example;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

public class EX23_SplitterPDF {
	public static void main(String[] args) {
		String srcFileName = "pdf_example/sample.pdf";
		try (PDDocument document = PDDocument.load(new File(srcFileName));) {// 문서읽기
			// PDF문서 분리하기
			Splitter splitter = new Splitter();
			List<PDDocument> Pages = splitter.split(document);
			Iterator<PDDocument> iterator = Pages.listIterator();

			int i = 1;
			while (iterator.hasNext()) {
				PDDocument pd = iterator.next();
				pd.save("pdf_example/sample_" + String.format("%02d", i++) + ".pdf");
			}
			System.out.println("PDF파일 페이지별 저장 완료!!!");
			ChromeView.view("pdf_example/sample_01.pdf");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

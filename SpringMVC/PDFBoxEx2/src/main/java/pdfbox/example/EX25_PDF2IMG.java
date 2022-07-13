package pdfbox.example;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

public class EX25_PDF2IMG {
	public static void main(String[] args) {
		String srcFileName = "pdf_example/sample.pdf";
		try (PDDocument document = PDDocument.load(new File(srcFileName));) {
			int numberOfPages = document.getNumberOfPages();
			PDFRenderer renderer = new PDFRenderer(document);
			for(int i=0;i<numberOfPages;i++) {
				BufferedImage image = renderer.renderImage(i);
				ImageIO.write(image, "JPEG", new File("pdf_example/sample_" + String.format("%02d", i+1) + ".jpg"));
			}
			System.out.println("PDF를 그림 으로 저장 완료");
			// 저장된 그림 보기
			ChromeView.view("pdf_example/sample_01.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

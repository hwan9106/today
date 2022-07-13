package pdfbox.example;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationSquareCircle;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
import org.apache.pdfbox.util.Matrix;

public class EX20_DrawShape {
	public static void main(String[] args) {
		String destFileName = "pdf_example/EX20_DrawShape.pdf";
		try (PDDocument document = new PDDocument()) {// 문서작성
			PDPage page = new PDPage(PDRectangle.A4); // 페이지 작성

			System.out.println("페이지 크기");
			PDRectangle mediaBox = page.getMediaBox();
			System.out.println(mediaBox);
			// 여백
			float marginY = 33;
			float marginX = 33;
			// 좌우 여백을 뺀 폭
			float width = mediaBox.getWidth() - 2 * marginX;
			float height = mediaBox.getHeight() - 2 * marginY;

			float lowerLeftX = mediaBox.getLowerLeftX();
			float upperRightX = mediaBox.getUpperRightX();
			float lowerLeftY = mediaBox.getLowerLeftY();
			float upperRightY = mediaBox.getUpperRightY();
			System.out.println(lowerLeftX + " ~ " + upperRightX);
			System.out.println(lowerLeftY + " ~ " + upperRightY);

			PDPageContentStream contentStream = new PDPageContentStream(document, page);
			// 사각형 그리기
			contentStream.setStrokingColor(Color.RED);
			contentStream.setNonStrokingColor(Color.CYAN);
			contentStream.addRect(200, 650, 100, 100);
			contentStream.fillAndStroke();
			contentStream.addRect(210, 660, 80, 80);
			contentStream.stroke();

			// 다각형 그리기
			float x[] = new float[] { marginX, marginX, marginX + width };
			float y[] = new float[] { marginY, marginY + height, marginY };
			// Deprecated.
			// contentStream.drawPolygon(x, y);
			for (int i = 0; i < x.length; i++) {
				if (i == 0) {
					contentStream.moveTo(x[i], y[i]);
				} else {
					contentStream.lineTo(x[i], y[i]);
				}
			}
			contentStream.closePath();
			contentStream.fill();

			// 곡선 그리기
			contentStream.transform(new Matrix(AffineTransform.getTranslateInstance(100, 400)));
			contentStream.setStrokingColor(Color.red);
			contentStream.setLineWidth(5f);
			contentStream.moveTo(1, 1);
			contentStream.curveTo1(111, 075, 200, 200);
			contentStream.curveTo2(352, 124, 80, 80);
			contentStream.stroke();

			List<PDAnnotation> annotations = page.getAnnotations();
			PDColor red = new PDColor(new float[] { 1, 0, 0 }, PDDeviceRGB.INSTANCE);
			PDColor blue = new PDColor(new float[] { 0, 0, 1 }, PDDeviceRGB.INSTANCE);
			PDColor black = new PDColor(new float[] { 0, 0, 0 }, PDDeviceRGB.INSTANCE);
			PDBorderStyleDictionary borderThin = new PDBorderStyleDictionary();
			borderThin.setWidth(3f);
			PDAnnotationSquareCircle circle = new PDAnnotationSquareCircle(PDAnnotationSquareCircle.SUB_TYPE_CIRCLE);
			PDRectangle position = new PDRectangle(marginX, marginY, 100, 100);
			circle.setRectangle(position);
			circle.setColor(blue);
			circle.setInteriorColor(red);
			circle.setBorderStyle(borderThin);
			annotations.add(circle);
			PDAnnotationSquareCircle circle2 = new PDAnnotationSquareCircle(PDAnnotationSquareCircle.SUB_TYPE_CIRCLE);
			PDRectangle position2 = new PDRectangle(200, 200, 100, 100);
			PDBorderStyleDictionary borderThin2 = new PDBorderStyleDictionary();
			borderThin2.setWidth(6f);
			circle2.setRectangle(position2);
			circle2.setColor(black);
			// circle2.setInteriorColor(blue);
			circle2.setBorderStyle(borderThin2);
			annotations.add(circle2);

			contentStream.close();
			document.addPage(page); // 페이지 추가
			document.save(destFileName); // 저장
			System.out.println(destFileName + "PDF 작성완료");

			ChromeView.view(destFileName);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

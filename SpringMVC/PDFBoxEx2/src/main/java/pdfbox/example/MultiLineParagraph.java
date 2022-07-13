package pdfbox.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

public class MultiLineParagraph {

    // private static final PDFont FONT = PDType1Font.HELVETICA; 
	static PDFont FONT;   
    private static final float FONT_SIZE = 12;
    private static final float LEADING = -1.5f * FONT_SIZE;
    public static void main(String... args)  {
    	String destFileName = "pdf_example/MultiLineParagraph.pdf";
        try (final PDDocument doc = new PDDocument()){

            PDPage page = new PDPage();
            doc.addPage(page);
            
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            String  text = Files.readString(Paths.get("src/main/resources/NationalAnthem2.txt"));

            // 폰트 변경
            // FONT = PDType1Font.HELVETICA; // 영문
            InputStream in  = new FileInputStream("font/NanumGothicCoding.ttf");
            FONT = PDType0Font.load(doc, in);
			contentStream.setFont(FONT, FONT_SIZE);
			
            PDRectangle mediaBox = page.getMediaBox();
            // 여백
            float marginY = 80;
            float marginX = 60;
            // 좌우 여백을 뺀 폭
            float width = mediaBox.getWidth() - 2 * marginX;
            // 시작 위치 계산
            float startX = mediaBox.getLowerLeftX() + marginX;
            float startY = mediaBox.getUpperRightY() - marginY;

            contentStream.beginText();
            addParagraph(contentStream, width, startX, startY, text, true);
            addParagraph(contentStream, width, 0, -FONT_SIZE, text);
            addParagraph(contentStream, width, 0, -FONT_SIZE, text, false);
            addParagraph(contentStream, width, 0, -FONT_SIZE, text, true);
            contentStream.endText();

            contentStream.close();

            doc.save(new File(destFileName));
        } catch (IOException e){
            System.err.println("Exception while trying to create pdf document - " + e);
        }
        ChromeView.view(destFileName);
    }

    private static void addParagraph(PDPageContentStream contentStream, float width, float sx,
                                      float sy, String text) throws IOException {
        addParagraph(contentStream, width, sx, sy, text, false);
    }

    private static void addParagraph(PDPageContentStream contentStream, float width, float sx,
                                      float sy, String text, boolean justify) throws IOException {
        List<String> lines = parseLines(text, width);
        contentStream.setFont(FONT, FONT_SIZE);
        contentStream.newLineAtOffset(sx, sy);
        for (String line: lines) {
            float charSpacing = 0;
            if (justify){
                if (line.length() > 1) {
                    float size = FONT_SIZE * FONT.getStringWidth(line) / 1000;
                    float free = width - size;
                    if (free > 0 && !lines.get(lines.size() - 1).equals(line)) {
                        charSpacing = free / (line.length() - 1);
                    }
                }
            }
            contentStream.setCharacterSpacing(charSpacing);
            contentStream.showText(line);
            contentStream.newLineAtOffset(0, LEADING);
        }
    }

    private static List<String> parseLines(String text, float width) throws IOException {
        List<String> lines = new ArrayList<String>();
        int lastSpace = -1;
        while (text.length() > 0) {
            int spaceIndex = text.indexOf(' ', lastSpace + 1);
            if (spaceIndex < 0)
                spaceIndex = text.length();
            String subString = text.substring(0, spaceIndex);
            float size = FONT_SIZE * FONT.getStringWidth(subString) / 1000;
            if (size > width) {
                if (lastSpace < 0){
                    lastSpace = spaceIndex;
                }
                subString = text.substring(0, lastSpace);
                lines.add(subString);
                text = text.substring(lastSpace).trim();
                lastSpace = -1;
            } else if (spaceIndex == text.length()) {
                lines.add(text);
                text = "";
            } else {
                lastSpace = spaceIndex;
            }
        }
        return lines;
    }
}
package kr.top2blue.JumpStartTutorial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfButtonFormField;
import com.itextpdf.forms.fields.PdfChoiceFormField;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.forms.fields.PdfTextFormField;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

// 폼을 만듭니다.
public class Ex21_CreateAndFillForm {
	static String destFileName = "JumpStartTutorial/result/Ex21_CreateAndFillForm.pdf";
	static String fontFile = "font/NanumGothicCoding-Bold.ttf";
	static PdfFont korFont = null;

	public static void main(String[] args) {
		makePdf(destFileName);
		PDFView.viewChrome(destFileName); 
		// PDFView.viewAcrobat(destFileName); // 아크로벳 리더로 봐야 함
	}

	private static void makePdf(String fileName) {
		// 지정 파일의 상위 폴더가 없으면 만들어 준다.
		File destFile = new File(fileName);
		destFile.getParentFile().mkdirs();
		// PDF 문서 만들기
		PdfWriter pdfWriter = null;

		try {
			korFont = PdfFontFactory.createFont(fontFile, PdfEncodings.IDENTITY_H);
			
			pdfWriter = new PdfWriter(destFile);// PdfWriter객체 생성
			PdfDocument pdfDocument = new PdfDocument(pdfWriter); // PdfDocument객체 생성

			Document document = new Document(pdfDocument);
			document.setFont(korFont).setFontSize(10f);
			
			PdfAcroForm form = addAcroForm(document);
			
			// 폼에 내용을 채운다
			Map<String, PdfFormField> fields = form.getFormFields();
	        fields.get("name").setValue("제임스 본드");
	        fields.get("language").setValue("Java");
	        fields.get("experience1").setValue("Off");
	        fields.get("experience2").setValue("Yes");
	        fields.get("experience3").setValue("Yes");
	        fields.get("shift").setValue("6.30 am - 2.30 pm");
	        fields.get("info").setValue("제가 MI6 요원이 되었을 때 저는 38세였습니다.");
			
			document.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static PdfAcroForm addAcroForm(Document doc) {
		// 제목 지정
        Paragraph title = new Paragraph("문서 양식")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(18);
        doc.add(title);
        doc.add(new Paragraph("이     름 : ").setFontSize(10));
        doc.add(new Paragraph("사용 언어 :      Java         Python       C/C++        JavaScript        Spring").setFontSize(10));
        doc.add(new Paragraph("경     험 :      cooking      driving      software development").setFontSize(10));
        doc.add(new Paragraph("시간 선택 : ").setFontSize(10));
        doc.add(new Paragraph("추가 사항 : ").setFontSize(10));

        // PdfAcroForm 객체 생성
        PdfAcroForm form = PdfAcroForm.getAcroForm(doc.getPdfDocument(), true);

        // 문자입력이 가능한 PdfTextFormField객체 생성
        PdfTextFormField nameField = PdfTextFormField.createText(doc.getPdfDocument(), new Rectangle(99, 748, 425, 15), "name", "");
        nameField.setFont(korFont); 
        form.addField(nameField);

        // Create radio buttons
        PdfButtonFormField group = PdfFormField.createRadioGroup(doc.getPdfDocument(), "language", "");
        PdfFormField.createRadioButton(doc.getPdfDocument(), new Rectangle(100, 725, 12, 12), group, "Java");
        PdfFormField.createRadioButton(doc.getPdfDocument(), new Rectangle(168, 725, 12, 12), group, "Python");
        PdfFormField.createRadioButton(doc.getPdfDocument(), new Rectangle(235, 725, 12, 12), group, "C/C++");
        PdfFormField.createRadioButton(doc.getPdfDocument(), new Rectangle(300, 725, 12, 12), group, "JavaScript");
        PdfFormField.createRadioButton(doc.getPdfDocument(), new Rectangle(390, 725, 12, 12), group, "Spring");
        form.addField(group);

        // Create checkboxes
        for (int i = 0; i < 3; i++) {
            PdfButtonFormField checkField = PdfFormField.createCheckBox(doc.getPdfDocument(), new Rectangle(100 + i * 69, 701, 15, 15),
                    "experience".concat(String.valueOf(i+1)), "Off", PdfFormField.TYPE_CHECK);
            form.addField(checkField);
        }

        // Create combobox
        String[] options = {"Any", "6.30 am - 2.30 pm", "1.30 pm - 9.30 pm"};
        PdfChoiceFormField choiceField = PdfFormField.createComboBox(doc.getPdfDocument(), new Rectangle(100, 676, 110, 15),
                "shift", "Any", options);
        form.addField(choiceField);

        // Create multiline text field
        PdfTextFormField infoField = PdfTextFormField.createMultilineText(doc.getPdfDocument(),
                new Rectangle(100, 625, 425, 40), "info", "");
        infoField.setFont(korFont); 
        form.addField(infoField);

        // Create push button field
        PdfButtonFormField button = PdfFormField.createPushButton(doc.getPdfDocument(),
                new Rectangle(479, 594, 45, 15), "reset", "Reset");
        button.setAction(PdfAction.createResetForm(new String[] {"name", "language", "experience1", "experience2", "experience3", "shift", "info"}, 0));
        form.addField(button);

        return form;

    }
}

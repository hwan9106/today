package pdfbox.example;
/*
PDF 내의 측정 단위 는 전통적인 그래픽 산업 측정 단위인 포인트 입니다. Adobe는 다음 정의를 사용합니다.
1 pt = 1/72 inch

1인치는 정확히 25.4mm로 정의되므로(정말로!) 공식을 사용하여 포인트에서 mm로 변환할 수 있습니다.
mm = pt * 25.4 / 72
pt = mm / 25.4 * 72
*/
public class PtMmConverter {
	public static void main(String[] args) {
		// 595.27563, 841.8898
		
		System.out.println(mm2pt(210));
		System.out.println(mm2pt(297));

		System.out.println(pt2mm(595.27563) );
		System.out.println(pt2mm(841.8898) );

		System.out.println(pt2mm(mm2pt(210)));
		System.out.println(pt2mm(mm2pt(297)));
	}
	// point를 mm로 변환
	public static double pt2mm(double pt) {
		return pt * 25.4 / 72;
	}
	// mm를 point로 변환
	public static double mm2pt(double mm) {
		return mm / 25.4 * 72;
	}
}

package kr.human.di.vo;

import kr.human.di.app.MathCheat;

public class Student02 {
	private int id;
	private MathCheat mathCheat;
	
	public void setId(int id) {
		this.id=id;
	}
	public void setMathCheat(MathCheat mathCheat) {
		this.mathCheat = mathCheat;
	}
	
	public void cheating() {
		
		System.out.println("MY ID is: " + id);
		mathCheat.mathCheating();
	}
}

package kr.human.di.vo;

public class Student01 {
	private int id;
    private String studentName;
 
    public void setId(int id) { 
    	this.id = id; 
    }
 
    public void setStudentName(String studentName)
    {
        this.studentName = studentName;
    }
 
    public void displayInfo()
    {
        System.out.println("Student Name is " + studentName
                           + " and Roll Number is " + id);
    }
}

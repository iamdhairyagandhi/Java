package onlineTest;

import java.io.Serializable;
import java.util.HashSet;

public class Student implements Serializable {
	protected String name;
	protected HashSet<Exam> examsTaken;
	
	public Student(String name) {
		this.name = name;
		this.examsTaken = new HashSet<Exam>();
	}
	
	public void addExam(Exam exam) {
		examsTaken.add(exam);
	}
	
	public int hashCode() {
		return name.hashCode();
	}
}
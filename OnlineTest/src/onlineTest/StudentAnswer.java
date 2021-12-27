package onlineTest;

import java.io.Serializable;

public class StudentAnswer implements Serializable{
	public String[] answerOther;
	public boolean answerTF;

	public StudentAnswer(boolean studentAnswer) {
		this.answerTF = studentAnswer;
	}

	public StudentAnswer(String[] studentAnswer) {
		this.answerOther = studentAnswer;

	}

	public String toString() {
		if (answerOther == null) {
			return "" + answerTF;
		}
		String response = "Student's answer: ";
		for (String s : answerOther) {
			response += s + " ";
		}
		return response;
	}
}

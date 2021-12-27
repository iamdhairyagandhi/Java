package onlineTest;

import java.io.Serializable;

public class TrueFalse extends Question implements Serializable {
	protected boolean answer;


	public TrueFalse(int questionNumber, String text, double points, boolean answer) {

		super(questionNumber, text, points, answer);
		this.answer = answer;
	
	}

	public int getQuestionNumber() {
		return super.getQuestionNumber();
	}

	public double getPoints() {
		return super.getPoints();
	}

	public boolean getAnswer() {
		return this.answer;
	}
	
	public static boolean isAnsweredCorrectly(StudentAnswer stdAns, boolean ans) {
		return stdAns.answerTF == ans;
	}
	
	@Override
	public int getIdentifier() {
	return 1;
	}
	
	@Override
	public String getCorrectAnswer() {
		if(answer) {
			return super.getCorrectAnswer() + "True";	
		} else {
			return super.getCorrectAnswer() + "False";
		}
	}
	
	@Override
	public String toString() {
		if (answer == true) {
			return super.toString() + "Correct Answer: True";
		} else {
			return super.toString() + "Correct Answer: False";
		}
	}

	public int compareTo(TrueFalse obj) {

		if (super.getQuestionNumber() > obj.getQuestionNumber()) {
			return 1;

		} else if (super.getQuestionNumber() == obj.getQuestionNumber()) {
			return 0;

		}
		return -1;

	}

}

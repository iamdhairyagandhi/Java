package onlineTest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class MultipleChoiceQuestion extends Question implements Serializable{

	protected String[] answer;

	public MultipleChoiceQuestion(int questionNumber, String text, double points, String[] answer) {
		super(questionNumber, text, points, answer);
		
		this.answer = answer;
	}

	public int getQuestionNumber() {
		return super.getQuestionNumber();
	}

	public double getPoints() {
		return super.getPoints();
	}

	public ArrayList<String> getAnswer() {
		ArrayList<String> getAnswer = new ArrayList<String>();
		for (int i = 0; i < answer.length; i++) {
			getAnswer.add(answer[i]);
		}
		return getAnswer;
	}

	@Override
	public int getIdentifier() {
		return 2;
	}

	@Override
	public String getCorrectAnswer() {
		String addToAnswer = super.getCorrectAnswer();
		addToAnswer += "[";
		for (int i = 0; i < answer.length; i++) {
			addToAnswer += answer[i];
			if (i < answer.length - 1) {
				addToAnswer += ", ";
			}
		}
		addToAnswer += "]";
		return addToAnswer;
	}

	@Override
	public String toString() {
		ArrayList<String> mcqString = new ArrayList<String>();
		for (int i = 0; i < answer.length; i++) {
			mcqString.add(answer[i]);
		}
		Collections.sort(mcqString);

		return super.toString() + "Correct Answer: " + mcqString;
	}

	public int compareTo(MultipleChoiceQuestion obj) {
		if (super.getQuestionNumber() > obj.getQuestionNumber()) {
			return 1;
		} else if (super.getQuestionNumber() == obj.getQuestionNumber()) {
			return 0;
		}
		return -1;

	}

	public static boolean isAnsweredCorrectly(StudentAnswer stdAns, String[] ans) {
		boolean isCorrect=true;
		if (ans.length == stdAns.answerOther.length) {
			for (int i = 0; i < ans.length; i++) {
				if (!ans[i].equals(stdAns.answerOther[i])) {
					isCorrect = false;
					break;
				}
			}
		} else {
			isCorrect = false;

		}
		return isCorrect;
	}
}

package onlineTest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FillInBlankQuestion extends Question implements Serializable{
	protected String[] answer;
	static int answeredCorrect;

	public FillInBlankQuestion(int questionNumber, String text, double points, String[] answer) {

		super(questionNumber, text, points, answer);
		this.answer=answer;
		answeredCorrect=0;
		
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
	
	public static boolean isAnsweredCorrectly(StudentAnswer stdAns, String[] ans) {
		boolean isCorrect = true;
		int counter = 0;
		for (int i = 0; i < ans.length; i++) {
			for (int j = 0; j <  stdAns.answerOther.length; j++) {
				if (ans[i].equalsIgnoreCase(stdAns.answerOther[j])) {				
					counter++;
				}
			}
		}
		answeredCorrect = counter;
		return isCorrect;
	}
	
	@Override
	public int getIdentifier() {
	return 3;
	}
	
	@Override
	public String getCorrectAnswer() {
		String addToAnswer = super.getCorrectAnswer();
		//addToAnswer+="[";
	/*	for(int i = 0; i < answer.length; i++) {
			addToAnswer+=answer[i];
			if(i < answer.length - 1) {
				addToAnswer+=", ";
			}
		}*/
		List<String> wordList = Arrays.asList(answer); 
		Collections.sort(wordList);
		addToAnswer+= wordList.toString();
	//	addToAnswer+="]";
		return addToAnswer;
	}

	@Override
	public String toString() {

		List<String> fitbString = Arrays.asList(this.correctAnswerOther); 
		Collections.sort(fitbString);
/*
		ArrayList<String> flag = new ArrayList<String>();
		for (int i = 0; i < super.correctAnswerOther.length; i++) {
			flag.add(answer[i]);
		}
		Collections.sort(flag);
	*/	
		for(String x:fitbString) {
			System.out.println(x);
		}
		return super.toString() + "Correct Answer: " + fitbString.toString();
	}

	public int compareTo(FillInBlankQuestion obj) {

		if (super.getQuestionNumber() > obj.getQuestionNumber()) {

			return 1;

		} else if (super.getQuestionNumber() == obj.getQuestionNumber()) {

			return 0;

		}

		return -1;

	}

}

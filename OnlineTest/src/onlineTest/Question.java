package onlineTest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Question implements Comparable<Question>, Serializable{

	protected int questionNumber;
	protected double points;
	protected String text;

	protected HashMap<Student, StudentAnswer> sAnswers = new HashMap<Student, StudentAnswer>();
	private HashMap<Student, Double> sScores = new HashMap<Student, Double>();

	 String[] correctAnswerOther;
	 boolean correctAnswerTF;

	public Question(int questionNumber, String text, double points, boolean correctAnswerTF) {

		this.questionNumber = questionNumber;
		this.text = text;
		this.points = points;
		this.correctAnswerTF = correctAnswerTF;
	}

	public Question(int questionNumber, String text, double points, String[] correctAnswerOther) {

		this.questionNumber = questionNumber;
		this.text = text;
		this.points = points;
		this.correctAnswerOther = correctAnswerOther;
	}

	public int getIdentifier() {
		return 0;
	}

	public int getQuestionNumber() {

		return this.questionNumber;
	}

	public double getPoints() {

		return this.points;
	}

	public double getScore(Student s) {

		return sScores.get(s);
	}

	public void addStudentAnswer(Student s, StudentAnswer stdAns) {
		sAnswers.put(s, stdAns);
	//	sAnswers.put(s, stdAns);
		sScores.put(s, score(stdAns));
	}

	public double score(StudentAnswer stdAns) {
		boolean answeredCorrectly = true;
		//int answeredCorrect = 0;

		if (getIdentifier() == 1) {
			answeredCorrectly = TrueFalse.isAnsweredCorrectly(stdAns, correctAnswerTF);
		} else if (getIdentifier() == 2) {
			answeredCorrectly = MultipleChoiceQuestion.isAnsweredCorrectly(stdAns, correctAnswerOther);
		} else if (getIdentifier() == 3) {
			answeredCorrectly = FillInBlankQuestion.isAnsweredCorrectly(stdAns, correctAnswerOther);
		}

		double pointsAwarded = 0;
		if (answeredCorrectly) {
			if (getIdentifier() == 3) {
				pointsAwarded = (points * FillInBlankQuestion.answeredCorrect / correctAnswerOther.length);
			} else {
				pointsAwarded = points;
			}
		}
		return pointsAwarded;
	}

	public String getCorrectAnswer() {
		String correctAnswer = "";
		return correctAnswer += "Question Text: " + text + "\nPoints: " + points + "\nCorrect Answer: ";
	}

	public String toString() {

		String results = "";
		results = "Question Text" + this.text + "\n";
		results += "Points:" + this.points + "\n";
		return results;

	}

	public int compareTo(Question obj) {

		if (this.questionNumber > obj.questionNumber) {
			return 1;
		} else if (this.questionNumber == obj.questionNumber) {
			return 0;
		}
		return -1;

	}

}

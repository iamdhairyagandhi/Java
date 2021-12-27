package onlineTest;

import java.io.Serializable;
import java.util.*;

public class Exam implements Serializable{
	private int id;
	private String examTitle;
	protected double totalValue;

	protected TreeMap<Integer, Question> questions;
	private HashMap<Student, Double> givenGrades;

	public Exam(int id, String examTitle) {
		this.id = id;
		this.examTitle = examTitle;

		this.questions = new TreeMap<Integer, Question>();
		this.givenGrades = new LinkedHashMap<Student, Double>();
	}

	public void addQuestion(Question question) {
		Question oldQuestion = questions.get(question.questionNumber);

		if (oldQuestion == null) {
			totalValue += question.points;
		} else {
			totalValue -= oldQuestion.points;
			totalValue += question.points;
		}
		questions.put(question.questionNumber, question);
	}

	public String getKey() {
		String key = "";
		Set<Integer> keys = questions.keySet();

		int counter = 0;
		for (Integer k : keys) {
			key+= questions.get(k).getCorrectAnswer();
			if (counter < keys.size() - 1) {
				key+="\n";
			}
		}
		return key;
	}

	public int getHashCode() {
		return id;
	}

	public void answerQuestion(Student student, Integer questionNumber, StudentAnswer stdAns) {
		Question question = questions.get(questionNumber);
		question.addStudentAnswer(student, stdAns);
	}

	public double getStudentGrade(Student s) {
		gradeStudent(s);
		return givenGrades.get(s);
	}

	public void gradeStudent(Student s) {
		double points = 0;
		for (Integer q : questions.keySet()) {
			points += questions.get(q).getScore(s);
		}
		givenGrades.put(s, points);
	}

	public String gradingReportBuilder(Student s) {
		gradeStudent(s);
		String gradingReport="";
		for(Integer i : questions.keySet()) {
			gradingReport += "Question #" + i + " " +
			questions.get(i).getScore(s) +" points out of " + 
			questions.get(i).points + "\n";			
		}
		return gradingReport+="Final Score: " + givenGrades.get(s) + 
				" out of " + totalValue;
	
	}
	
}

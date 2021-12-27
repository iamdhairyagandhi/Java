package onlineTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class SystemManager implements Manager,  Serializable {

	protected HashMap<Integer, Exam> exams = new LinkedHashMap<>();
	protected TreeMap<String, Student> students = new TreeMap<>();
	protected Student[] studentsArr1 ;
	protected ArrayList<Student> studentsArr = new ArrayList<Student>();

	protected String[] letterGrades = { "A", "B", "C", "D", "F" };
	protected double[] cutoffGrades = { 90, 80, 70, 60, 0 };

	@Override
	public boolean addExam(int examId, String title) {
		boolean flag = true;
		if (exams.get(examId) != null) {
			flag = false;
		}
		exams.put(examId, new Exam(examId, title));
		return flag;
	}

	@Override
	public void addTrueFalseQuestion(int examId, int questionNumber, String text, double points, boolean answer) {

		TrueFalse question = new TrueFalse(questionNumber, text, points, answer);
		exams.get(examId).addQuestion(question);
	}

	@Override
	public void addMultipleChoiceQuestion(int examId, int questionNumber, String text, double points, String[] answer) {
		MultipleChoiceQuestion question = new MultipleChoiceQuestion(questionNumber, text, points, answer);
		exams.get(examId).addQuestion(question);
	}

	@Override
	public void addFillInTheBlanksQuestion(int examId, int questionNumber, String text, double points,
			String[] answer) {
		FillInBlankQuestion question = new FillInBlankQuestion(questionNumber, text, points, answer);
		exams.get(examId).addQuestion(question);

	}

	@Override
	public String getKey(int examId) {
		return exams.get(examId).getKey();
	}

//	public void  getResponseTF() {

//	}

//	public void getResponseMCQFIB() {

//	}

	@Override
	public boolean addStudent(String name) {

	Set<String> studentId = students.keySet();
		//Student[] studentDirectory = new Student[studentId.size()];
		boolean flag = true;

		if (students.get(name) != null) {
			flag = false;
		}
		students.put(name, new Student(name));

		//int counter = 0;
		for (String s : studentId) {
			studentsArr.add(students.get(s));
//			studentDirectory[counter++] = students.get(s);
		}
	//	this.studentsArr1 = studentDirectory;
		return flag;
	
	}

	@Override
	public void answerTrueFalseQuestion(String studentName, int examId, int questionNumber, boolean answer) {
		StudentAnswer stdAns = new StudentAnswer(answer);
		exams.get(examId).answerQuestion(students.get(studentName), questionNumber, stdAns);
	}

	@Override
	public void answerMultipleChoiceQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		StudentAnswer stdAns = new StudentAnswer(answer);
		exams.get(examId).answerQuestion(students.get(studentName), questionNumber, stdAns);
	}

	@Override
	public void answerFillInTheBlanksQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		StudentAnswer stdAns = new StudentAnswer(answer);
		exams.get(examId).answerQuestion(students.get(studentName), questionNumber, stdAns);
	}

	@Override
	public double getExamScore(String studentName, int examId) {
		return exams.get(examId).getStudentGrade(students.get(studentName));
	}

	@Override
	public String getGradingReport(String studentName, int examId) {
		return exams.get(examId).gradingReportBuilder(students.get(studentName));
	}

	@Override
	public void setLetterGradesCutoffs(String[] letterGrades, double[] cutoffs) {
		this.letterGrades = letterGrades;
		this.cutoffGrades = cutoffs;

	}

	@Override
	public double getCourseNumericGrade(String studentName) {
		double average = 0;
		double numberExams = 0;
		Student s = students.get(studentName);
		for (Integer ex : exams.keySet()) {
			average += exams.get(ex).getStudentGrade(s) / exams.get(ex).totalValue;
			numberExams++;
		}
		return average / numberExams * 100;
	}

	@Override
	public String getCourseLetterGrade(String studentName) {
		Student s = students.get(studentName);
		double numericGrade = getCourseNumericGrade(studentName);
		for (int i = 0; i < cutoffGrades.length; i++) {
			if (numericGrade >= cutoffGrades[i]) {
				return letterGrades[i];
			}
		}
		return letterGrades[letterGrades.length - 1];
	}

	@Override
	public String getCourseGrades() {
		String courseGrades = "";
		for (String i : students.keySet()) {
			courseGrades += i + " " + getCourseNumericGrade(i) + " " + getCourseLetterGrade(i) + "\n";
		}
		return courseGrades;
	}

	@Override
	public double getMaxScore(int examId) {
		Exam exam = exams.get(examId);

		double maxScore = exam.getStudentGrade(studentsArr.get(0));
		for (int i = 1; i < studentsArr.size(); i++) {
			double newScore = exam.getStudentGrade(studentsArr.get(i));
			if (newScore > maxScore) {
				maxScore = newScore;
			}
		}
		return maxScore;
	}

	@Override
	public double getMinScore(int examId) {
		Exam exam = exams.get(examId);

		double minScore = exam.getStudentGrade(studentsArr.get(0));
		for (int i = 1; i < studentsArr.size(); i++) {
			double newScore = exam.getStudentGrade(studentsArr.get(i));
			if (newScore < minScore) {
				minScore = newScore;
			}
		}
		return minScore;
	}

	@Override
	public double getAverageScore(int examId) {
		Exam exam = exams.get(examId);
		Set<Student> stdName  = new HashSet<Student>();
		for (Student student : studentsArr) {
			stdName.add(student);
		}
		ArrayList<Student> arr = new ArrayList<Student>(stdName);
		
		double sum = 0;
	//	int temp = studentsArr.size();
		for (int i = 0; i < arr.size(); i++) {
			sum += exam.getStudentGrade(arr.get(i));
		}	
		return (sum /(double) arr.size());
	}

	@Override
	public void saveManager(Manager manager, String fileName) {
		try {
			FileOutputStream stream = new FileOutputStream(fileName);
			ObjectOutputStream objectStream = new ObjectOutputStream(stream);
			objectStream.writeObject(manager);
			objectStream.close();
		} catch(IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Manager restoreManager(String fileName) {
		Manager readManager = null;
		try {
			FileInputStream stream = new FileInputStream(fileName);
			ObjectInputStream objectStream = new ObjectInputStream(stream);
			readManager = (Manager) objectStream.readObject();
			objectStream.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return readManager;
	}
	
	public String toString() {
		String result = "";
		for (int i = 0; i < exams.keySet().size(); i++) {
			result+=exams.get(i).getKey();
		}
		return result;
	}
}

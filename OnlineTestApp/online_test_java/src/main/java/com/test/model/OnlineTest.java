package com.test.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class OnlineTest {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String question;
	private String correctAnswer;
	private String userAnswer;
	
	public OnlineTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OnlineTest(String question, String correctAnswer) {
		super();
		this.question = question;
		this.correctAnswer = correctAnswer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}
	
}

package com.feedback.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)
	private int overallRate;
	
	@Column(nullable=false)
	private int recommendRate;
	
	@Column(nullable=false)
	private String comment;
	
	public Feedback() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getOverallRate() {
		return overallRate;
	}

	public void setOverallRate(int overallRate) {
		this.overallRate = overallRate;
	}

	public int getRecommendRate() {
		return recommendRate;
	}

	public void setRecommendRate(int recommendRate) {
		this.recommendRate = recommendRate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}

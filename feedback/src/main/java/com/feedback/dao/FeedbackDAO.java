package com.feedback.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.feedback.model.Feedback;

@Repository
public interface FeedbackDAO extends JpaRepository<Feedback, Integer> {

	@Query("SELECT comment FROM Feedback")
	public List<String> getComment();
	
	@Query("SELECT ROUND(AVG(recommendRate),2) FROM Feedback")
	public double avgRecommendRate();
	
	@Query("SELECT ROUND(AVG(overallRate),2) FROM Feedback")
	public double avgOverallRate();
}

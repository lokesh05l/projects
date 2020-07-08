package com.feedback.service;

import java.util.List;

import com.feedback.exception.BusinessException;
import com.feedback.model.Feedback;

public interface FeedbackService {

	public Feedback createFeedback(Feedback feedback) throws BusinessException;
	public List<Feedback> getAllFeedbacks() throws BusinessException;
	public Feedback updateFeedback(Feedback feedback) throws BusinessException;
	public void deleteFeedbackById(int id) throws BusinessException;
	public List<String> getComment() throws BusinessException;
	public double avgRecommendRate() throws BusinessException;
	public double avgOverallRate() throws BusinessException;
}

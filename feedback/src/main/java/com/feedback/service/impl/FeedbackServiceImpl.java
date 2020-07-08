package com.feedback.service.impl;

import java.util.List;

import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import com.feedback.dao.FeedbackDAO;
import com.feedback.exception.BusinessException;
import com.feedback.model.Feedback;
import com.feedback.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackDAO dao;
	
	@Override
	public Feedback createFeedback(Feedback feedback) throws BusinessException{
		
		Feedback f=null;
		if(isValidName(feedback.getName()))
		{
			if(isValidEmail(feedback.getEmail()))
			{
				if(feedback.getOverallRate()>0 && feedback.getOverallRate()<6 && feedback.getRecommendRate()>0 && feedback.getRecommendRate()<6)
				{
					f=dao.save(feedback);
				}
				else
					throw new BusinessException("Please enter the rating between 1 and 5");
			}
			else
				throw new BusinessException("Please enter a valid email");
		}
		else
			throw new BusinessException("Please enter a valid name");
		return f;
	}

	@Override
	public List<Feedback> getAllFeedbacks() throws BusinessException {
		List<Feedback> feedbacks=dao.findAll();
		if(feedbacks.isEmpty())
			throw new BusinessException("No feedbacks yet");
		else
			return feedbacks;
	}

	@Override
	public Feedback updateFeedback(Feedback feedback) throws BusinessException {
		
		if(dao.existsById(feedback.getId()))
			return dao.save(feedback);
		else
			throw new BusinessException("No data found for the id no."+feedback.getId());
	}
	
	@Override
	public void deleteFeedbackById(int id) throws BusinessException {
		if(id<0)
			throw new BusinessException("Please enter a valid id");
		else
		{	try {
				dao.deleteById(id);
			} catch (EmptyResultDataAccessException e)
			{
				throw new BusinessException("No feedback found for the id no."+id);
			}
		}
	}
	
	@Override
	public List<String> getComment() throws BusinessException
	{
		List<String> comment= dao.getComment();
		if(comment.isEmpty())
			throw new BusinessException("No comments yet");
		else
			return comment;
	}
	
	private boolean isValidName(String name)
	{
		String regex="[a-zA-Z]{3,}";
		if (name.matches(regex))
			return true;
		else
			return false;
	}
	
	private boolean isValidEmail(String email)
	{
		String regex="[\\.a-zA-Z0-9]{1,}+@[a-z]+\\.[a-z]{2,4}";
		if(email.matches(regex))
			return true;
		else
			return false;
	}

	@Override
	public double avgRecommendRate() throws BusinessException {
		
		double avg;
		try {
			avg=dao.avgRecommendRate();
			return avg;
		}
		catch(AopInvocationException e){
			throw new BusinessException("No values of now");
		}
	}
	
	@Override
	public double avgOverallRate() throws BusinessException {
		
		double avg;
		try {
			avg=dao.avgOverallRate();
			return avg;
		}
		catch(AopInvocationException e){
			throw new BusinessException("No values of now");
		}
	}
}

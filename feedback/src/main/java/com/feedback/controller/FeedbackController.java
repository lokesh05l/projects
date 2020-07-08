package com.feedback.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.feedback.exception.BusinessException;
import com.feedback.model.Feedback;
import com.feedback.service.FeedbackService;

@RestController
public class FeedbackController {

	@Autowired
	private FeedbackService service;
	
	private MultiValueMap<String, String> map;
	
	@PostMapping("/feedback")
	public ResponseEntity<String> createFeedback(@RequestBody Feedback feedback) {

		try {
			service.createFeedback(feedback);
			return new ResponseEntity<String>("Thank you for your feedback", HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
	}

	@GetMapping("/feedbacks")
	public ResponseEntity<List<Feedback>> getAllFeedbacks() {
		try {
			return new ResponseEntity<List<Feedback>>(service.getAllFeedbacks(),HttpStatus.OK);
		} catch(BusinessException e)
		{
			map=new LinkedMultiValueMap<>();
			map.add("errorMessage", e.getMessage());
			return new ResponseEntity<List<Feedback>>(null, map, HttpStatus.NOT_FOUND);	
		}
		
	}
	
	@PutMapping("/feedback")
	public ResponseEntity<String> updateFeedback(@RequestBody Feedback feedback) {
		try {
			service.updateFeedback(feedback);
			return new ResponseEntity<String>("Updated Successfully",HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/feedback/{id}")
	public ResponseEntity<String> deleteFeedbackById (@PathVariable("id") int id) {
		try {
			service.deleteFeedbackById(id);
			return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
		} catch (BusinessException e)
		{
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/feedback/comment")
	public ResponseEntity<List<String>> getComment()
	{
		try {
			List<String> comment=service.getComment();
			return new ResponseEntity<List<String>>(comment, HttpStatus.OK);
		}catch (BusinessException e)
		{
			List<String> comment=Arrays.asList(e.getMessage());
			return new ResponseEntity<List<String>>(comment, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/feedback/avgRecommendRate")
	public ResponseEntity<String> avgRecommendRate() {
	
		try {
			double avg=service.avgRecommendRate();
			return new ResponseEntity<String>((Double.toString(avg)),HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/feedback/avgOverallRate")
	public ResponseEntity<String> avgOverallRate() {
		
		try {
			double avg=service.avgOverallRate();
			return new ResponseEntity<String>((Double.toString(avg)),HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		
	}
}

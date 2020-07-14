package com.test.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.OnlineTest;
import com.test.service.OnlineTestService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OnlineTestController {
	
	@Autowired
	private OnlineTestService service;
	
	@PutMapping("/quiz/{id}")
	public OnlineTest updateUserAnswer(@PathVariable("id")int id, @Valid @RequestBody OnlineTest question) {
		OnlineTest quiz=service.getQuizById(id);
		quiz.setUserAnswer(question.getUserAnswer());
		return service.updateUserAnswer(quiz);
	}
	
	@GetMapping("/quizzes")
	public List<OnlineTest> getAllQuesAns() {

		return service.getAllQuesAns();
	}
	
	@GetMapping("/quiz/{id}")
	public OnlineTest getQuizById(@PathVariable("id")int id) {

		return service.getQuizById(id);
	}
	
}

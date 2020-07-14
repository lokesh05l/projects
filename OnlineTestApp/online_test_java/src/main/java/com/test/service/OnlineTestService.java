package com.test.service;

import java.util.List;

import com.test.model.OnlineTest;

public interface OnlineTestService {
	
	public OnlineTest updateUserAnswer(OnlineTest question);
	public List<OnlineTest> getAllQuesAns();
	public OnlineTest getQuizById(int id);
	
}

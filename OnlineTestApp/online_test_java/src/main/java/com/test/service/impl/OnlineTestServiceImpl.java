package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.OnlineTestDAO;
import com.test.model.OnlineTest;
import com.test.service.OnlineTestService;

@Service
public class OnlineTestServiceImpl implements OnlineTestService {

	@Autowired
	private OnlineTestDAO dao;
	
	@Override
	public OnlineTest updateUserAnswer(OnlineTest question) {
	
		return dao.save(question);
	}

	@Override
	public List<OnlineTest> getAllQuesAns() {
	
		return dao.findAll();
	}
	
	@Override
	public OnlineTest getQuizById(int id)
	{
		return dao.findById(id).get();
	}
	
}

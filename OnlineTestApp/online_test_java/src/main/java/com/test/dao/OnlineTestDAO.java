package com.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.model.OnlineTest;

@Repository
public interface OnlineTestDAO extends JpaRepository<OnlineTest, Integer> {

	
}

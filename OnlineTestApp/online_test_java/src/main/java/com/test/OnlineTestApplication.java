package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.test.dao.OnlineTestDAO;
import com.test.model.OnlineTest;

@SpringBootApplication
public class OnlineTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineTestApplication.class, args);
	}
}

@Component
class OnlineTestCommandLineRunner implements CommandLineRunner{

	@Autowired
	private OnlineTestDAO dao;
	
	@Override
	public void run(String... args) throws Exception {
		
		OnlineTest question1=new OnlineTest();
		question1.setQuestion("The length of the bridge, which a train 130 metres long and travelling at 45 km/hr can cross in 30 seconds, is ____ metres.");
		question1.setCorrectAnswer("245");
		dao.save(question1);
		
		OnlineTest question2=new OnlineTest();
		question2.setQuestion("Out of 7 consonants and 4 vowels, how many words of 3 consonants and 2 vowels can be formed?");
		question2.setCorrectAnswer("25200");
		dao.save(question2);
		
		OnlineTest question3=new OnlineTest();
		question3.setQuestion("A, B and C can do a piece of work in 20, 30 and 60 days respectively. In how many days can A do the work if he is assisted by B and C on every third day?");
		question3.setCorrectAnswer("15");
		dao.save(question3);
		
		OnlineTest question4=new OnlineTest();
		question4.setQuestion("The cost price of 20 articles is the same as the selling price of x articles. If the profit is 25%, then the value of x is ____");
		question4.setCorrectAnswer("16");
		dao.save(question4);
		
		OnlineTest question5=new OnlineTest();
		question5.setQuestion("Let N be the greatest number that will divide 1305, 4665 and 6905, leaving the same remainder in each case. Then sum of the digits in N is:");
		question5.setCorrectAnswer("4");
		dao.save(question5);
		
	}
	


}
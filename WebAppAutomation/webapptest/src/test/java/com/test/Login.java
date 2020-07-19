package com.test;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class Login {
	
	WebDriver driver;
	
	@BeforeTest
	public void openBrowser() {
		driver=new ChromeDriver();
    	System.out.println("INSIDE STEP - OPENING BROWSER");
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(5000, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println("BROWSER OPENED SUCCESSFULLY\n");
	}
  
	@BeforeTest(dependsOnMethods ="openBrowser")
	public void openLoginPage(){
		System.out.println("INSIDE STEP - OPEN LOGIN/REGISTRATION PAGE");
		String actualUrl="http://automationpractice.com/index.php";
		driver.get(actualUrl);
		driver.findElement(By.linkText("Sign in")).click();
		System.out.println("LOGIN/REGISTRATION PAGE OPENED SUCCESSFULY\n");
	}
	
	@Test(priority=2,dataProvider="GetCredentials")
	public void validLogin(String email,String password) throws InterruptedException
	{
		System.out.println("INSIDE STEP - LOGIN");
		System.out.println("APPROACH: VALID CUSTOMER'S EMAIL AND PASSWORD");
		System.out.println("ENTERING EMAIL ADDRESS");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(email);
		Thread.sleep(1000);
		System.out.println("ENTERING PASSWORD");
		driver.findElement(By.name("passwd")).clear();
		driver.findElement(By.name("passwd")).sendKeys(password);
		Thread.sleep(1000);
		driver.findElement(By.name("SubmitLogin")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		System.out.println("LOGIN SUCCESSFULL\nGOING TO SIGN OUT\n");
		driver.findElement(By.linkText("Sign out")).click();
	}
	
	@Test(priority=1,dataProvider="GetCredentials")
	public void invalidLogin(String email,String password,String approach) throws InterruptedException
	{
		System.out.println("INSIDE STEP - LOGIN");
		System.out.println("APPROACH: "+approach);
		System.out.println("ENTERING EMAIL ADDRESS");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(email);
		Thread.sleep(1000);
		System.out.println("ENTERING PASSWORD");
		driver.findElement(By.name("passwd")).clear();
		driver.findElement(By.name("passwd")).sendKeys(password);
		Thread.sleep(1000);
		driver.findElement(By.name("SubmitLogin")).sendKeys(Keys.ENTER);
		String errorMessage=driver.findElement(By.xpath("//div[@id='center_column']//div[1]//ol//li")).getText();
		System.out.println(errorMessage.toUpperCase()+"\n");
		Thread.sleep(3000);
	}
	
	@DataProvider(name="GetCredentials")
	public Object[][] getCredentials(Method method)
	{
		if(method.getName().equals("validLogin"))
			return new Object[][]{
			{"nithya123@yahoo.com","123456"}
		};
		else
		{
			return new Object[][] {
				{"","","BLANK EMAIL WITH BLANK PASSWORD"},
				{"lokesh123@gmail.com","","VALID/INVALID EMAIL WITH BLANK PASSWORD"},
				{"","123456","BLANK EMAIL WITH VALID/INVALID PASSWORD"},
				{"sgcjhbb@gmail.com","dxxcfcgjhvk","INVALID EMAIL WITH INVALID PASSWORD"},
				{"lokesh123@gmail.com","SADCCXSA","VALID EMAIL WITH INVALID PASSWORD"}
			};
		}
	}
	
	@AfterTest
	public void closeBrowser()
	{
		driver.close();
		System.out.println("BROWSER CLOSED");
	}

}

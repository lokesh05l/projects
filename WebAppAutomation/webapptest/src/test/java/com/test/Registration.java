package com.test;

import org.testng.annotations.Test;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

public class Registration {
  
	WebDriver driver=new ChromeDriver();
	
	@BeforeTest
	public void openBrowser() {
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
	
	@Test(dataProvider="GetEmail")
	//@Parameters("email")
	public void register(String email) throws InterruptedException {
		
		System.out.println("INSIDE STEP - REGISTRATION");
		System.out.println("AUTHETICATING E-MAIL ADDRESS");
		driver.findElement(By.name("email_create")).sendKeys(email);
		Thread.sleep(1000);
		driver.findElement(By.name("SubmitCreate")).click();
		Thread.sleep(3000);
		String expectedURL="http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation";
		Assert.assertEquals(driver.getCurrentUrl(), expectedURL, "AUTHENTICATION FAILED!!");
		
		System.out.println("AUTHENTICATION SUCCESSFULL\nFILLING REGISTRATION FORM");
		driver.findElement(By.id("id_gender1")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("customer_firstname")).sendKeys("Lokesh");
		Thread.sleep(1000);
		driver.findElement(By.id("customer_lastname")).sendKeys("Lavakumar");
		Thread.sleep(1000);
		driver.findElement(By.id("passwd")).sendKeys("123456");
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("days"))).selectByValue("15");	
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("months"))).selectByValue("5");
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("years"))).selectByValue("1999");
		Thread.sleep(1000);
		driver.findElement(By.name("newsletter")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("optin")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("company")).sendKeys("BNP");
		Thread.sleep(1000);
		driver.findElement(By.id("address1")).sendKeys("11/6, Thiruvalluvar Street");
		Thread.sleep(1000);
		driver.findElement(By.id("address2")).sendKeys("Lokesh Nivas, Vandalur");
		Thread.sleep(1000);
		driver.findElement(By.id("city")).sendKeys("Chennai");
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("id_state"))).selectByVisibleText("Hawaii");
		Thread.sleep(1000);
		driver.findElement(By.id("postcode")).sendKeys("60008");
		new Select(driver.findElement(By.id("id_country"))).selectByVisibleText("United States");
		Thread.sleep(1000);
		driver.findElement(By.id("other")).sendKeys("I am a gameChanger");
		Thread.sleep(1000);
		driver.findElement(By.id("phone")).sendKeys("1234567890");
		Thread.sleep(1000);
		driver.findElement(By.id("phone_mobile")).sendKeys("9087622413");
		Thread.sleep(1000);
		driver.findElement(By.id("alias")).clear();
		Thread.sleep(1000);
		driver.findElement(By.id("alias")).sendKeys("Home");
		Thread.sleep(1000);
		driver.findElement(By.id("submitAccount")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		
		expectedURL="http://automationpractice.com/index.php?controller=my-account";
		Assert.assertEquals(driver.getCurrentUrl(), expectedURL,"REGISTRATION FAILED");
		System.out.println("REGISTRATION SUCCESSFULL\nGOING TO SIGN OUT");
		
		driver.findElement(By.linkText("Sign out")).click();
		Thread.sleep(3000);
		expectedURL="http://automationpractice.com/index.php?controller=authentication&back=my-account";
		Assert.assertEquals(driver.getCurrentUrl(), expectedURL,"SIGN OUT FAILED");
		System.out.println("SIGN OUT SUCCESSFULL\nCLOSING THE BROWSER\n");
		
	}
	
	@AfterTest
	public void closeBrowser()
	{
		driver.close();
		System.out.println("BROWSER CLOSED SUCCESSFULLY");
	}
	
	@DataProvider(name="GetEmail")
	public Object[][] getEmail()
	{
		String alphaNumeric="qwertyuioplkjhgfdsazxcvbnm1234567890abcdefghijklmnopqrstuvwxyz";
		int length=7;
		StringBuilder builder =new StringBuilder();
		while(length--!=0) {
			int index=(int)(Math.random()*alphaNumeric.length());
			builder.append(alphaNumeric.charAt(index));
		}
		String email=builder.toString();
		return new Object[][] {{ email+"@boys.com"}};
	}
	
}
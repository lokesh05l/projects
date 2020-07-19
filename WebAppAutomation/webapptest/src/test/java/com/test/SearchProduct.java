package com.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SearchProduct {
	
	WebDriver driver;
	JavascriptExecutor js;
	
	@BeforeTest
	public void openBrowser() {
		driver=new ChromeDriver();
		js=(JavascriptExecutor)driver;
    	System.out.println("INSIDE STEP - OPENING BROWSER");
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(5000, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println("BROWSER OPENED SUCCESSFULLY\n");
	}
	
	@BeforeTest(dependsOnMethods ="openBrowser")
	public void openHomePage(){
		System.out.println("INSIDE STEP - OPEN HOME PAGE");
		String actualUrl="http://automationpractice.com/index.php";
		driver.get(actualUrl);
		System.out.println("HOME PAGE OPENED SUCCESSFULY\n");
	}
	
	@Test
	public void searchProduct() throws InterruptedException
	{
		System.out.println("INSIDE STEP - SEARCH PRODUCT");
		driver.findElement(By.id("search_query_top")).sendKeys("Dress");
		driver.findElement(By.name("submit_search")).click();
		Thread.sleep(3000);
		System.out.println("SEARCHING A PRODUCT");
		Assert.assertEquals(driver.getPageSource().contains("0 results have been found"),false , "SEARCH FAILED");
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,-500)");
		Thread.sleep(1000);
		System.out.println("SEARCH SUCCESSFULL\nCLOSING THE BROWSER\n");
	}
	
	@AfterTest
	public void closeBrowser()
	{
		driver.close();
		System.out.println("BROWSER CLOSED");
	}
	
}

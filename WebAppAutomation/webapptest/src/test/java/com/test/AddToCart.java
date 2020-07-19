package com.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddToCart {
	
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
		System.out.println("SEARCH SUCCESSFULL\n");
	}
	
	@Test(dependsOnMethods = "searchProduct")
	public void AddProductToCart() throws InterruptedException
	{
		driver.findElement(By.xpath("//div[@id=\"center_column\"]//ul//li[2]//div//div[2]//h5//a")).click();	
		Thread.sleep(3000);
		String expectedTitle="Printed Dress - My Store";
		Assert.assertEquals(driver.getTitle(), expectedTitle, "PRODUCT PAGE NOT FOUND");
		System.out.println("ADDING PRODUCT TO CART");
		driver.findElement(By.id("quantity_wanted")).clear();
		driver.findElement(By.id("quantity_wanted")).sendKeys("2");
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("group_1"))).selectByVisibleText("L");
		Thread.sleep(1000);
		driver.findElement(By.name("Submit")).click();
		Thread.sleep(1000);
		Assert.assertEquals(driver.getPageSource().contains("Product successfully added to your shopping cart"), true,"PRODUCT NOT ADDED TO CART");
		System.out.println("PRODUCT SUCCESSFULLY ADDED TO SHOPPING CART\n");
		driver.findElement(By.xpath("//div[@id=\"layer_cart\"]//div[1]//div[2]//div[4]//span//span")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@id=\"columns\"]//div[2]//strong//a")).click();
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,-500)");
		Thread.sleep(1000);
		Thread.sleep(3000);
		
	}
	
	@AfterTest
	public void closeBrowser()
	{
		System.out.println("CLOSING THE BROWSER");
		driver.close();
		System.out.println("BROWSER CLOSED");
	}
}

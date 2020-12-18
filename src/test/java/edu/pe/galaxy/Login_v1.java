package edu.pe.galaxy;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Login_v1 {
  
	static WebDriver driver = null;
	static String url = "http://the-internet.herokuapp.com/login";


	@BeforeTest
	public void lauchBrowser() {
		driver = new ChromeDriver();
	}

	@BeforeClass
	public void setURL() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
	}
	
	@Test
	public void getTitleLogin() {
		String expected = "The Internet";
		Assert.assertEquals(driver.getTitle(), expected, "Title Test Passed");
	}
	
	@Test
	public void doLoginFail() throws InterruptedException {
		String message = "Secure Area";
		String actual = "";
		
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("password")).clear();
		
		try {
			driver.findElement(By.id("username")).sendKeys("tomsmith");
			driver.findElement(By.id("password")).sendKeys("SuperSecretPassword");
			driver.findElement(By.xpath("//*[@id=\"login\"]/button")).submit();
		}finally{
			Thread.sleep(1000);
			actual = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h2")).getText();
			Assert.assertEquals(actual, message);
		}
	}
	
	@Test
	public void doLoginPass() throws InterruptedException {
		String message = "Secure Area";
		String actual = "";
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("password")).clear();
		try {
			driver.findElement(By.id("username")).sendKeys("tomsmith");
			driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
			driver.findElement(By.xpath("//*[@id=\"login\"]/button")).submit();
		}finally{
			Thread.sleep(1000);
			actual = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h2")).getText();
			Assert.assertEquals(actual, message);
		}
	}
	
	@Test
	public void doLogout() {
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/a/i")).click();
	}
	
	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
}

package edu.pe.galaxy;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


public class Login_v2 {
	
	static WebDriver driver = null;
	static String url = "http://the-internet.herokuapp.com/secure";


	@Parameters("BrowserName")
	@BeforeTest
	public void setUp(String BrowserName) {
		if (BrowserName.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		} else if (BrowserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (BrowserName.equalsIgnoreCase("ie")) {
			driver = new InternetExplorerDriver();
		} else if (BrowserName.equalsIgnoreCase("Opera")) {
			driver = new OperaDriver();
		}
	}

	@BeforeClass
	public void setURL() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	@Test
	public void getTitleLogin() {
		String expected = "The Internet";
		AssertJUnit.assertEquals(driver.getTitle(), expected);
	}
	
	/*
	 * @Test public void doLoginFail() throws InterruptedException { String message
	 * = "Login Page"; String actual = "";
	 * driver.findElement(By.id("username")).clear();
	 * driver.findElement(By.id("password")).clear(); try {
	 * driver.findElement(By.id("username")).sendKeys("tomsmith");
	 * driver.findElement(By.id("password")).sendKeys("SuperSecretPassword");
	 * driver.findElement(By.xpath("//*[@id=\"login\"]/button")).submit(); }finally{
	 * Thread.sleep(1000); actual =
	 * driver.findElement(By.xpath("//*[@id=\"content\"]/div/h2")).getText();
	 * AssertJUnit.assertEquals(actual, message); } }
	 */
	
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
			AssertJUnit.assertEquals(actual, message);
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

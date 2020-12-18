package edu.pe.galaxy;

import org.testng.annotations.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class SeleniumGrid_V1 {

	WebDriver driver;
	
	String url = "https://www.google.com.pe/";
	
	String nodeURL = "http://localhost:4444/wd/hub";

	@BeforeTest
	public void Setup() throws MalformedURLException {
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("firefox");
		capability.setPlatform(Platform.LINUX);
		driver = new RemoteWebDriver(new URL(nodeURL), capability);
	}

	@Test
	public void Test_Google_Title() {
		
		driver.get(url);

		driver.findElement(By.name("q")).sendKeys("Selenium Dev");
		
		if (driver.getTitle().contains("Google")) {
			Assert.assertTrue(true, "Pass: Test has been completed");
			System.out.println("Test Completed");
		} else {
			Assert.assertTrue(false, "Failed: Test failed");
			System.out.println("Test Failed");
		}
	}

	@AfterTest
	public void TearDown() {
		driver.quit();
	}

}

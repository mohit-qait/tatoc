package com.qait.automation.tat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
	
	WebDriver driver=null;

	public WebDriver getdriver()
	{
		System.setProperty("webdriver.chrome.driver", "src\\resources\\java\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}
}

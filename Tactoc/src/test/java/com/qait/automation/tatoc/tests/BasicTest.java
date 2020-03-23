package com.qait.automation.tatoc.tests;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qait.automation.tat.Driver;
import com.qait.automation.tatoc.keywords.ActionClass;


public class BasicTest extends Driver
{
	public  WebDriver driver;
	ActionClass ac;
    @BeforeTest
    public void before() {
    	ac = new ActionClass();
    }
	
	@Test(priority=1)
	public void Test1() 
	{
		ac.verificationOfLandingPage("http://10.0.1.86/tatoc","http://10.0.1.86/tatoc/basic/grid/gate","/html/body/div/div[2]/a[1]");
	}
	
	@Test(priority=2)
	public void TestGreen()
	{
		ac.verificationofClickGreen();
	}
	
	@Test(priority=3)
	public void TestMatchColor()
	{
		ac.verificationOfColorMatch();
	}
	
	@Test(priority=4)
	public void TestDragMe()
	{
		ac.verificationOfDragMe();
	}
	
	@Test(priority=5)
	public void TestPopUp()
	{
		ac.verificationOfPopup();
	}
	
	@Test(priority=6)
	public void TestCookie()
	{
		ac.verificationofCookieHandling();
	}
}

package com.qait.automation.tatoc.tests;

import java.sql.SQLException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qait.automation.tat.Driver;
import com.qait.automation.tatoc.keywords.ActionClass;

public class AdvancedTest extends Driver
{
	ActionClass ac;
	
    @BeforeTest
    public void before() {
    	ac = new ActionClass();
    }
    
    @Test(priority=1)
    public void TC01()
    {
    	ac.verificationOfLandingPage("http://10.0.1.86/tatoc","http://10.0.1.86/tatoc/advanced/hover/menu","/html/body/div/div[2]/a[2]");
    }
    
    @Test(priority=2)
    public void TC02()
    {
    	ac.verificationOfHoverMenuPage();
    }
    
    @Test(priority=3)
    public void TC03() throws ClassNotFoundException, SQLException
    {
    	ac.verificationOfQueryGatePage();
    }
}
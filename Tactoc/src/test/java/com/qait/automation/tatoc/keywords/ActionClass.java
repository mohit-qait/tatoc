package com.qait.automation.tatoc.keywords;

import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.qait.automation.tat.Driver;

public class ActionClass extends Driver {
	Driver d = new Driver();
	WebDriver driver;

	public ActionClass() {
		driver = d.getdriver();
	}

	public void getPage(String Url) {
		driver.get(Url);

		Assert.assertEquals(Url, driver.getCurrentUrl());
		Assert.assertTrue(Url.contentEquals(driver.getCurrentUrl()), "[INFO]: You Landed on Wrong Page");
		logMessage("[INFO]: You Landed on Correct Page");
	}

	public void sendKeyF(String xpathstr, String input) {
		getElement(xpathstr).sendKeys(input);
		logMessage("[INFO]: " + input + " has been entered");
	}

	public void clickElement(String xpathstr, URL expectedurl) // if on clicking new page is displayed
	{
		getElement(xpathstr).click();
		assertTrue((expectedurl.toString()).contentEquals(driver.getCurrentUrl()), "[INFO]: Element was not Clicked");
		logMessage("[INFO]: Element was Clicked Successfully");
	}

	public void clickElement(String xpathstr) // if on clicking new page is displayed
	{
		getElement(xpathstr).click();
		logMessage("[INFO]: Element was Clicked Successfully");
	}

	public void clickElement(String xpathstr, String expectedElementPath) // if on clicking an element is displayed
	{
		getElement(xpathstr).click();

		WebElement expectedElement = getElement(expectedElementPath);
		assertTrue(expectedElement.isDisplayed(), "[INFO]: Element was not Clicked");
		logMessage("[INFO]: Element was Clicked Successfully");
	}

	public void hover(String xpathstr, String expectedElementPath) {
		Actions actions = new Actions(driver);

		WebElement target = driver.findElement(By.xpath(xpathstr));
		actions.moveToElement(target).perform();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement expectedElement = getElement(expectedElementPath);
		assertTrue(expectedElement.isDisplayed(), "[INFO]: No Such Element was Found");
		logMessage("[INFO]: Element was Hovered Upon Successfully");
	}

	public void logMessage(String msg) {
		System.out.println(msg);
	}

	public URL getUrl(String urlstr) // To get object of a string url
	{
		URL url = null;
		try {
			url = new URL(urlstr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return url;
	}

	public WebElement getElement(String xpathstr) {
		return driver.findElement(By.xpath(xpathstr));
	}

	public void switchFrame(String framename) {
		driver.switchTo().frame(framename);
		// check if its true
	}

	public void switchFrame() {
		driver.switchTo().defaultContent();
	}

	public void dragItem(WebElement source,WebElement target)
	{
		Actions action = new Actions(driver);
		action.dragAndDrop(source, target).build().perform();
		logMessage("[INFO]: Item Has Been Draged");
	}
	
	public String getTab()                            //Tab Handling
	{
		return driver.getWindowHandle();
	}
	
	public void switchToTab(String tab)
	{
		driver.switchTo().window(tab);
	}
	
	public void addCookie(String CookieName, String CookieValue)     //Cookie Handling
	{
		Cookie ck = new Cookie(CookieName,CookieValue);
		driver.manage().addCookie(ck); 
		logMessage("[INFO]: Cookie was Created");
	}
	/*----------------------------------------------Test Cases-----------------------------*/
	public void verificationOfLandingPage(String urlgetstr, String urlexpstr, String btnstr) {
		logMessage("[INFO]: Starting verification Of Landing Test Case");
		getPage(urlgetstr);
		URL expectedurl = getUrl(urlexpstr);
		clickElement(btnstr, expectedurl);
	}

	public void verificationofClickGreen() {
		int flag = 0;
		for (int i = 1; i <= 4; i++) {

			for (int j = 1; j <= 4; j++) {
				if ("greenbox".equalsIgnoreCase(
						getElement("/html/body/div/div[2]/table/tbody/tr[" + i + "]/td[" + j + "]/div")
								.getAttribute("class"))) {
					clickElement("/html/body/div/div[2]/table/tbody/tr[" + i + "]/td[" + j + "]/div",
							getUrl("http://10.0.1.86/tatoc/basic/frame/dungeon")); // yaml
					flag = 1;
					break;
				}
			}
			if (flag == 1) {
				break;
			}
		}
	}

	public void verificationOfColorMatch() {
		switchFrame("main");
		String FirstBoxColor = getElement("/html/body/center/div").getAttribute("class");
		int i = 0;
		while (i != 1) {
			clickElement("/html/body/center/a[1]");

			switchFrame("child");

			String SecondBoxColor = (getElement("/html/body/div").getAttribute("class"));
			switchFrame();
			switchFrame("main");
			if (FirstBoxColor.equalsIgnoreCase(SecondBoxColor)) {
				clickElement("/html/body/center/a[2]", getUrl("http://10.0.1.86/tatoc/basic/drag"));
				break;
			}
		}
	}

	public void verificationOfHoverMenuPage() {
		logMessage("[INFO]: Starting verification Of Hover Menu Page Test Case");
		URL expectedurl = getUrl("http://10.0.1.86/tatoc/advanced/query/gate");
		hover("/html/body/div/div[2]/div[2]/span[1]", "/html/body/div/div[2]/div[2]/span[5]");
		clickElement("/html/body/div/div[2]/div[2]/span[5]", expectedurl);
	}

	public void verificationOfQueryGatePage() throws ClassNotFoundException, SQLException {
		logMessage("[INFO]: Starting verification Of Query Gate Page Test Case");
		String Symbol = getElement("/html/body/div/div[2]/div/form/div").getText();

		// Hitting Database

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://10.0.1.86:3306/tatoc", "tatocuser", "tatoc01");
		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery("select * from credentials");
		String username = null;
		String password = null;
		while (rs.next()) {
			char c = ((rs.getString(2)).toUpperCase()).charAt(0);
			if (c == Symbol.charAt(0)) {
				username = rs.getString(2);
				password = rs.getString(3);
			}
		}
		con.close();

		sendKeyF("//*[@id=\"name\"]", username);
		sendKeyF("//*[@id=\"passkey\"]", password);

		clickElement("//*[@id=\"submit\"]", getUrl("http://10.0.1.86/tatoc/advanced/video/player"));
	}

	public void verificationOfDragMe() {
		WebElement sourceLocator = getElement("/html/body/div/div[2]/div[2]");
		WebElement targetLocator = getElement("/html/body/div/div[2]/div[1]");

		dragItem(sourceLocator,targetLocator);
		clickElement("/html/body/div/div[2]/a");
	}

	public void verificationOfPopup() {
		String oldTab = getTab();
		
		clickElement("/html/body/div/div[2]/a[1]");
		
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
	    newTab.remove(oldTab);
	    
	    driver.switchTo().window(newTab.get(0));
	    sendKeyF("/html/body/input[1]", "Shreyansh");
	    clickElement("/html/body/input[2]");
		
	    switchToTab(oldTab);
		
		clickElement("/html/body/div/div[2]/a[2]");
	}

	public void verificationofCookieHandling() {
		clickElement("/html/body/div/div[2]/a[1]");

		String Token = getElement("/html/body/div/div[2]/span[2]").getText();
		Token=Token.replace("Token: ","");
		
		//cookie
		addCookie("Token",Token);
		
		clickElement("/html/body/div/div[2]/a[2]");
	}

	
}

/*package StepDefination;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class stepdefinition
{
	 public static WebDriver driver;
	    String baseURL = "http://10.0.1.86";
	 
	    @Given("^Open the Cherome and launch the application$")
	    public void user_launches_Google_webapp() throws Throwable {
	        // Write code here that turns the phrase above into concrete actions
	        System.setProperty("webdriver.chrome.driver", "rc\\test\\java\\com\\qait\\automation\\tat\\Driver.java");
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get(baseURL);
	    }
	
	    @Then("^Perform the tasks on the Basic Tatoc$")
	    public void results_retreived_should_contain_the_used(String resultString) throws Throwable {
	        WebElement result = driver.findElement(By.className("LC20lb"));
	        if (resultString.equalsIgnoreCase(result.getText())) {
	            System.out.println("Text is matching");
	        } else {
	            System.out.println("Text is not matching");
	 
	        }
	        driver.quit();
	    }

}*/

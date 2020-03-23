/*package TestRunner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

public class Runner {
	private TestNGCucumberRunner testNGCucumberRunner;  

	  @BeforeClass(alwaysRun = true)  
	    public void setUpClass() throws Exception {  
	        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());  
	  }  

	    @Test(groups = "Cucumber", description = "Runs Cucumber Feature")  
	    public void scenario(PickleEventWrapper pickleEvent, CucumberFeatureWrapper cucumberFeature) throws Throwable {  
	        testNGCucumberRunner.runScenario(pickleEvent.getPickleEvent());  
	  }  

	 
	    @AfterClass(alwaysRun = true)  
	    public void tearDownClass() throws Exception {  
	        testNGCucumberRunner.finish();  
	  }  
}*/

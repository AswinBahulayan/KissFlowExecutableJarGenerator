package steps;

import java.io.IOException;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.webdriverfactory.com.SeleniumHelper;;

public class Hooks extends SeleniumHelper{
	@Before
	public void beforeCucumber(Scenario sc) throws IOException {
		startResult();
		testCaseName = sc.getName();
		testCaseDescription =sc.getId();
		category = "Smoke";
		author= "Aswin";
		startTestCase();
		startApp("chrome", "https://kissflow.com");
		}
	@After
	public void afterScenario(Scenario sc) throws IOException {
		//closeAllBrowsers();
		stopResult();
		
	}








}



package steps;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.webdriverfactory.com.SeleniumHelper;

public class Background extends SeleniumHelper {
	
	
	public int i=0;
	public Scenario sc;
	
	@Given("I login to KiSSFLow with the username {string} and password {string}")
	public void Login(String username, String password) throws InterruptedException {
		waitForPageToLoad();
		click(locateElement("xpath", "//a[@href='https://app.kissflow.com']"));
	    takeSnap();
		type(locateElement("id", "emailId"), username);
		type(locateElement("id", "passwd"), password);
		click(locateElement("id", "submitButton"));
		Thread.sleep(3000);
		takeSnap();
	    
	}
	
	@And("I create an App {string}")
	public void CreateApp(String string) throws InterruptedException {
		try {
			waitForPageToLoad();
	    click(locateElement("xpath", "(//li[@kf-roles='AppFullAccess'])[1]/child::a"));
	    takeSnap();
	    Thread.sleep(5000);
	   // WaitForElementTodisplay(locateElement("xpath", "//div[@class='appImageContainer']/p"));
	    List<WebElement> app = driver.findElements(By.xpath("//div[@class='appHeading']/span"));
	    //WebDriverWait wait=new WebDriverWait(driver, 10);
	    //wait.until(ExpectedConditions.visibilityOfAllElements(app));
	    for(int i=13;i<app.size();i++)
	    {
	    	if(app.get(i).getText().equals("TestApp"))
	    	{
	    		AppName="TestApp"+i;
	    	}
	    }
	    waitForPageToLoad();
	    System.out.println(AppName);
	    takeSnap();
	    click(locateElement("xpath", "//div[@class='appImageContainer']/p"));
	    switchToFrame("wizard");
	    type(locateElement("xpath", "//input[@id='sectionheading']"), AppName);
	    type(locateElement("xpath", "//textarea[@id='shortDesc']"), "Hi this is Aswin. This application is part of a automated test");
	    takeSnap();
	    click(locateElement("xpath", "//a[contains(@ng-click,'updateProcess')]"));
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	    
	}
	
	@And("I create a Field of type {string} with Name {string} on {string}")
	public void FieldState(String string, String State, String TestApp) throws InterruptedException {
	
		i++;
		if(i==1)
		{
			if(!locateElement("xpath", "(//div[contains(@ng-if,'field.getValue')]/child::div)["+i+"]").isDisplayed())
			{
		WaitForElementTodisplay(locateElement("xpath", "//div[@title='Text']"));
		click(locateElement("xpath", "//div[@title='Text']"));
			}
		Thread.sleep(2000);
		click(locateElement("xpath", "(//div[contains(@ng-if,'field.getValue')]/child::div)["+i+"]"));		
		Thread.sleep(2000);
		type(locateElement("id", "field_name"), State);
		Thread.sleep(2000);
		click(locateElement("xpath", "//i[@class='fa fa-save']"));
		takeSnap();
		}
		else {
			//try {
			//if(!locateElement("xpath", "(//div[contains(@ng-if,'field.getValue')]/child::div)["+i+"]").isDisplayed())
			//{
			Thread.sleep(5000);
			click(locateElement("xpath", "//div[@title='Text']"));
			
			//}
		///*}catch(Exception e)
		//{
			//System.out.println(e.getMessage());
		//}*/
			click(locateElement("xpath", "(//div[contains(@ng-if,'field.getValue')]/child::div)["+i+"]"));		
			Thread.sleep(2000);
			type(locateElement("id", "field_name"), State);
			Thread.sleep(2000);
			click(locateElement("xpath", "(//input[@type='checkbox']/following-sibling::span)[1]"));
			Thread.sleep(3000);
			takeSnap();
			
		}
	}
	
	@And("I add a Formula {string} to {string} on {string}")
	public void i_add_a_Formula_to_on(String Formula, String string2, String string3) throws InterruptedException {
	    
		click(locateElement("xpath", "//i[@class='fa fa-chevron-down']"));
		Thread.sleep(2000);
		click(locateElement("xpath", "(//input[@type='checkbox']/following-sibling::span)[2]"));
		Thread.sleep(2000);
		type(locateElement("xpath", "//textarea[contains(@id,'formula')]"), "'"+Formula+"'");
		Thread.sleep(3000);
		click(locateElement("xpath", "//i[@class='fa fa-save']"));
		takeSnap();
	}

	@And("I published {string}")
	public void i_published(String string) throws InterruptedException {
	    Actions act=new Actions(driver);
	    act.moveToElement(locateElement("xpath", "//a[contains(@href,'workflow')]/child::div")).build().perform();
	    Thread.sleep(1000);
	    takeSnap();
	    click(locateElement("xpath", "//a[contains(@href,'workflow')]/child::div"));
	    Thread.sleep(1000);
	    waitForPageToLoad();
	    act.moveToElement(locateElement("xpath", "//a[contains(@href,'permission')]/child::div")).build().perform();
	    Thread.sleep(1000);
	    click(locateElement("xpath", "//a[contains(@href,'permission')]/child::div"));
	    Thread.sleep(1000);
	    takeSnap();
	    waitForPageToLoad();
	    act.moveToElement(locateElement("xpath", "//a[contains(@href,'publish')]/child::div")).build().perform();
	    Thread.sleep(1000);
	    click(locateElement("xpath", "//a[contains(@href,'publish')]/child::div"));
	    takeSnap();
	    click(locateElement("xpath", "//input[@id='publish_btn']"));
	    
	}
	
	
	public int j=0;
	
	@When("I Initiate {string} as item{int}")
	public void InitiateApp(String string,Integer a) throws InterruptedException, AWTException {
		
		//waitForPageToLoad();
		//System.out.println(Scenari.getName());
		Thread.sleep(5000);
		/*List<WebElement> GeneratedApp = driver.findElements(By.xpath("//span[text()='Live Apps']/following::div[@class='appHeading']/span"));
		for (int i=0;i<GeneratedApp.size();i++)
			{
			if(GeneratedApp.get(i).getText().equalsIgnoreCase(AppName))
			{
				GeneratedApp.get(i).click();
			}
			}*/
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		WebDriverWait wait= new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(locateElement("xpath", "(//li[@class='menuItem ng-scope']/a)[1]")));
		//driver.findElement(By.xpath("(//li[@class='menuItem ng-scope']/a)[1]")).
		WebElement home = driver.findElement(By.xpath("(//li[@class='menuItem ng-scope']/a)[1]"));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", home);
		//driver.findElement(By.xpath("//a[contains(@href,'init')]/span[text()='Show All']")).isDisplayed();*/
		
		//click(locateElement("xpath", "//a[contains(@href,'inbox')]"));
		waitForPageToLoad();
		takeSnap();
		Thread.sleep(10000);
		/*if(!driver.findElement(By.xpath("//a[contains(@href,'init')]/span[text()='Show All']")).isDisplayed())
		{
		driver.navigate().refresh();
		}*/
		WebElement showall = driver.findElement(By.xpath("//a[contains(@href,'init')]/span[text()='Show All']"));
		JavascriptExecutor jf=(JavascriptExecutor)driver;
		jf.executeScript("arguments[0].click();", showall);
		//click(locateElement("xpath", "//a[contains(@href,'init')]/span[text()='Show All']"));
		List<WebElement> ListApp = driver.findElements(By.xpath("//li[@class='initiateListItem ng-scope']"));
		for(int i=0;i<ListApp.size();i++)
		{
			//WebElement elect = ListApp.get(i).findElement(By.tagName("h5"));
			JavascriptExecutor jl=(JavascriptExecutor)driver;
			Object executeScript = jl.executeScript("return arguments[0].innerHTML;", ListApp.get(i).findElement(By.tagName("h5")));
			System.out.println("appname actual:"+AppName);
			System.out.println("text from webpage"+executeScript);
			if(((String) executeScript).equalsIgnoreCase(AppName))
			{
				JavascriptExecutor jd=(JavascriptExecutor)driver;
				WebElement initiate = ListApp.get(i).findElement(By.tagName("button"));
				jd.executeScript("arguments[0].click();", initiate);
//				Robot robot=new Robot();
//				robot.mouseMove(location.x, location.y);
//				robot.mousePress(InputEvent.BUTTON1_MASK);
//				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				break;
			}
		}
		
	}
	
	@And("I enter {string} in {string} for item{int}")
	public void EnterState(String StateName, String s, Integer f) throws InterruptedException, AWTException
	{
		waitForPageToLoad();
		type(locateElement("xpath", "//div[contains(@ng-if,'isReadOnly')]/input"), StateName);
		NameOfState=StateName;
		//Point location = driver.findElement(By.xpath("//form[@id='comments-section']")).getLocation();
		Robot robot=new Robot();
		//robot.mouseMove(location.x, location.y);
		robot.keyPress(KeyEvent.VK_TAB);
		//robot.mousePress(buttons);
		Thread.sleep(2000);
		takeSnap();
	}
	
	@Then("field {string} for item{int} should equal {string}")
	public void VerifyField(String s,Integer b,String c) throws InterruptedException
	{
		
		if(NameOfState.equalsIgnoreCase("TamilNadu"))
		{
			//String webtext = driver.findElement(By.xpath("//div[@class='ng-scope']/div[contains(@ng-bind-html,'Needs_Verification')]")).getText();
			//System.out.println(webtext);
			JavascriptExecutor je=(JavascriptExecutor)driver;
			Object Exec = je.executeScript("return arguments[0].innerHTML;", driver.findElement(By.xpath("//div[@class='ng-scope']/div[contains(@ng-bind-html,'Needs_Verification')]")));
			System.out.println(Exec);
			verifyText(((String) Exec), "Yes");
			//verifyExactText(locateElement("xpath", "//div[@class='ng-scope']/div[contains(@ng-bind-html,'Needs_Verification')]"), "Yes");
			//takeSnap();
			//Thread.sleep(3000);
		//if(driver.findElement(By.xpath("//div[@class='ng-scope']/div[contains(@ng-bind-html,'Needs_Verification')]")).getText().equalsIgnoreCase(anotherString))
		}
		else if(NameOfState.equalsIgnoreCase("Kerala")) {
			JavascriptExecutor jf=(JavascriptExecutor)driver;
			Object Execu = jf.executeScript("return arguments[0].innerHTML;", driver.findElement(By.xpath("//div[@class='ng-scope']/div[contains(@ng-bind-html,'Needs_Verification')]")));
			System.out.println(Execu);
			verifyText(((String) Execu), "No");
			//verifyExactText(locateElement("xpath", "//div[@class='ng-scope']/div[contains(@ng-bind-html,'Needs_Verification')]"), "Yes");
			//takeSnap();
			//Thread.sleep(3000);
		}
	}


	
}

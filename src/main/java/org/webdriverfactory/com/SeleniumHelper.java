package org.webdriverfactory.com;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import objectDetailedReporter.ObjectdetailedReporter;

public class SeleniumHelper extends ObjectdetailedReporter{
	public static String AppName=null;
	public int i = 1;
	public static String NameOfState=null;
	public static RemoteWebDriver driver;
	public static DesiredCapabilities capabilities;
	public void startApp(String browser, String url, String GridORLocal) throws MalformedURLException  {
		try {
			if(GridORLocal.equalsIgnoreCase("Local"))
			{
			if(browser.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("firefox")){
				System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
				driver = new FirefoxDriver();
			}
			}
			
			else if(GridORLocal.equalsIgnoreCase("Grid"))
			{
				
			if(browser.equalsIgnoreCase("chrome"))
			{
				//capabilities.chrome();
				/////
				
				capabilities= DesiredCapabilities.chrome();
				capabilities.setBrowserName("chrome");
				capabilities.setPlatform(Platform.WINDOWS);
				ChromeOptions options=new ChromeOptions();
				options.setHeadless(false);
				options.merge(capabilities);
			}
			if(browser.equalsIgnoreCase("firefox"))
			{
				capabilities=DesiredCapabilities.firefox();
				capabilities.setBrowserName("firefox");
				capabilities.setPlatform(Platform.WINDOWS);
				
			}
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
			}
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			reportStep("The browser: "+browser+" launched successfully", "pass");
		} catch (WebDriverException e) {			
			reportStep("The browser: "+browser+" could not be launched", "fail");
		}
	}
	
	public void verifyText(String s1,String S2)
	{
		if(s1.equalsIgnoreCase(S2))
		{
			reportStep("The result is as expected"+s1, "pass");
		}
		else {
			reportStep("The text didnot match"+S2, "fail");

		}
	}
	
	public void WaitForElementTodisplay(WebElement ele) {
		try {
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(ele));
		reportStep("waited for element", "pass");
		}
		catch(Exception e)
		{
			reportStep("an error occurred"+e.getMessage(), "fail");
		}
	}
	
	public void waitForPageToLoad() throws InterruptedException
	{
		try {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			String state=js.executeScript("return document.readyState").toString();
			for(int i=0;i<=10;i++)
			{
			if(state.equalsIgnoreCase("complete"))
			{
				break;
				}else {
					Thread.sleep(1000);
				}
			}
			reportStep("Waited for page to load", "pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			reportStep("an error occured"+e.getMessage(), "fail");
			e.printStackTrace();
		}
			
	}
	/*public Boolean WaitForElementTodisplayverify(WebElement ele) {
		Boolean status=false;
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(ele));
		
	}*/

	public WebElement locateElement(String locator, String locValue)  {
		try {
			switch(locator) {
			case "id"	 : return driver.findElementById(locValue);
			case "class" : return driver.findElementByClassName(locValue);
			case "name" : return driver.findElementByName(locValue);
			case "linktext" : return driver.findElementByLinkText(locValue);
			case "partialLink" : return driver.findElementByPartialLinkText(locValue);
			case "tagname" : return driver.findElementByTagName(locValue);
			case "xpath" : return driver.findElementByXPath(locValue);
			case "cssSelect" : return driver.findElementByCssSelector(locValue);
			}
			
		} catch (NoSuchElementException e) {
			reportStep("The element with locator "+locator+" not found.","fail");
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while finding "+locator+" with the value "+locValue, "fail");
		}
		return null;
	}

	public WebElement locateElement(String locValue) {
		return driver.findElementById(locValue);
	}

	public void type(WebElement ele, String data)  {
		try {
			ele.clear();
			ele.sendKeys(data);
			
			reportStep("The data: "+data+" entered successfully in the field :"+ele, "pass");
		} catch (InvalidElementStateException e) {
			reportStep("The data: "+data+" could not be entered in the field :"+ele,"fail");
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while entering "+data+" in the field :"+ele, "fail");
		}
		
	}

	public void clickWithNoSnap(WebElement ele) throws IOException, InterruptedException {
		String text = "";
		try {	
			text = ele.getText();
			ele.click();
			Thread.sleep(2000);
			
			reportStep("The element :"+text+"  is clicked.", "pass");
		} catch (InvalidElementStateException e) {
			reportStep("The element: "+text+" could not be clicked", "fail");
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while clicking in the field :","fail");
		} 
	}

	
	public void click(WebElement ele){
		String text = "";
		try {			
			text = ele.getText();
			ele.click();
		
			reportStep("The element "+text+" is clicked", "pass");
		} catch (InvalidElementStateException e) {
			reportStep("The element: "+text+" could not be clicked", "fail");
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while clicking in the field :", "fail");
		} 
	}

	public String getText(WebElement ele)  {	
		String bReturn = "";
		try {
			bReturn = ele.getText();
			
		} catch (WebDriverException e) {
			reportStep("The element: "+ele+" could not be found.", "fail");
		}
		return bReturn;
	}

	public String getTitle() throws IOException {		
		String bReturn = "";
		try {
			bReturn =  driver.getTitle();
			
		} catch (WebDriverException e) {
			reportStep("Unknown Exception Occured While fetching Title", "fail");
		} 
		return bReturn;
	}
	
	public String getAttribute(WebElement ele, String attribute) throws IOException {		
		String bReturn = "";
		try {
			bReturn=  ele.getAttribute(attribute);
			
		} catch (WebDriverException e) {
			reportStep("The element: "+ele+" could not be found.", "fail");
		} 
		return bReturn;
	}

	public void selectDropDownUsingText(WebElement ele, String value) throws IOException {
		try {
			new Select(ele).selectByVisibleText(value);
			
			reportStep("The dropdown is selected with text "+value,"pass");
		} catch (WebDriverException e) {
			reportStep("The element: "+ele+" could not be found.", "fail");
		}
	}

	public void selectDropDownUsingIndex(WebElement ele, int index) throws IOException {
		try {
			new Select(ele).selectByIndex(index);
		
			reportStep("The dropdown is selected with index "+index,"pass");
		} catch (WebDriverException e) {
			reportStep("The element: "+ele+" could not be found.", "fail");
		} 
	}

	public boolean verifyTitle(String title) throws IOException {
		boolean bReturn =false;
		try {
			if(getTitle().equals(title)) {
				
				reportStep("The title of the page matches with the value :"+title,"pass");
				bReturn= true;
			}else {
				reportStep("The title of the page:"+driver.getTitle()+" did not match with the value :"+title, "fail");
			}
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while verifying the title", "fail");
		} 
		return bReturn;
	}

	public void verifyExactText(WebElement ele, String expectedText)  {
		try {
			if(getText(ele).equals(expectedText)) {
			
				reportStep("The text: "+getText(ele)+" matches with the value :"+expectedText,"pass");
			}else {
				reportStep("The text "+getText(ele)+" doesn't matches the actual "+expectedText,"fail");
			}
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while verifying the Text", "fail");
		} 

	}

	public void verifyPartialText(WebElement ele, String expectedText) throws IOException {
		try {
			if(getText(ele).contains(expectedText)) {
				
				reportStep("The expected text contains the actual "+expectedText,"PASS");
			}else {
				reportStep("The expected text doesn't contain the actual "+expectedText,"FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while verifying the Text", "FAIL");
		} 
	}

	public void verifyExactAttribute(WebElement ele, String attribute, String value) throws IOException {
		try {
			if(getAttribute(ele, attribute).equals(value)) {
				
				reportStep("The expected attribute :"+attribute+" value matches the actual "+value,"pass");
			}else {
				reportStep("The expected attribute :"+attribute+" value does not matches the actual "+value,"fail");
			}
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while verifying the Attribute Text", "fail");
		} 

	}

	public void verifyPartialAttribute(WebElement ele, String attribute, String value) throws IOException {
		try {
			if(getAttribute(ele, attribute).contains(value)) {
				
				reportStep("The expected attribute :"+attribute+" value contains the actual "+value,"pass");
			}else {
				reportStep("The expected attribute :"+attribute+" value does not contains the actual "+value,"fail");
			}
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while verifying the Attribute Text", "fail");
		}
	}

	public void verifySelected(WebElement ele) throws IOException {
		try {
			if(ele.isSelected()) {
				
				reportStep("The element "+ele+" is selected","pass");
			} else {
				reportStep("The element "+ele+" is not selected","fail");
			}
		} catch (WebDriverException e) {
			reportStep("WebDriverException : "+e.getMessage(), "fail");
		}
	}

	public void verifyDisplayed(WebElement ele) throws IOException {
		try {
			if(ele.isDisplayed()) {
				
				reportStep("The element "+ele+" is visible","pass");
			} else {
				reportStep("The element "+ele+" is not visible","fail");
			}
		} catch (WebDriverException e) {
			reportStep("WebDriverException : "+e.getMessage(), "f");
		} 
	}

	public void switchToWindow(int index) throws IOException, InterruptedException {
		try {
			Set<String> allWindowHandles = driver.getWindowHandles();
			List<String> allHandles = new ArrayList<>();
			allHandles.addAll(allWindowHandles);
			driver.switchTo().window(allHandles.get(index));
			Thread.sleep(3000);
			
		} catch (NoSuchWindowException e) {
			reportStep("The driver could not move to the given window by index "+index,"pass");
		} catch (WebDriverException e) {
			reportStep("WebDriverException : "+e.getMessage(), "fail");
		}
	}

	public void switchToFrame(WebElement ele) throws IOException {
		try {
			driver.switchTo().frame(ele);
			
			reportStep("switch In to the Frame "+ele,"pass");
		} catch (NoSuchFrameException e) {
			reportStep("WebDriverException : "+e.getMessage(), "fail");
		} catch (WebDriverException e) {
			reportStep("WebDriverException : "+e.getMessage(), "fail");
		} 
	}
	
	public void switchToFrame(String id) {
		try {
			driver.switchTo().frame(id);
			
			reportStep("switch In to the Frame "+id,"pass");
		} catch (NoSuchFrameException e) {
			reportStep("WebDriverException : "+e.getMessage(), "fail");
		} catch (WebDriverException e) {
			reportStep("WebDriverException : "+e.getMessage(), "fail");
		} 
	}

	public void acceptAlert() throws IOException {
		String text = "";		
		try {
			Alert alert = driver.switchTo().alert();
			text = alert.getText();
			alert.accept();
			reportStep("The alert "+text+" is accepted.","pass");
		} catch (NoAlertPresentException e) {
			reportStep("There is no alert present.","fail");
		} catch (WebDriverException e) {
			reportStep("WebDriverException : "+e.getMessage(), "fail");
		}  
	}

	public void dismissAlert() throws IOException {
		String text = "";		
		try {
			Alert alert = driver.switchTo().alert();
			text = alert.getText();
			alert.dismiss();
			reportStep("The alert "+text+" is dismissed.","pass");
		} catch (NoAlertPresentException e) {
			reportStep("There is no alert present.","fail");
		} catch (WebDriverException e) {
			reportStep("WebDriverException : "+e.getMessage(), "fail");
		} 
	}

	public String getAlertText() throws IOException {
		String text = "";		
		try {
			Alert alert = driver.switchTo().alert();
			text = alert.getText();
		} catch (NoAlertPresentException e) {
			reportStep("There is no alert present.","fail");
		} catch (WebDriverException e) {
			reportStep("WebDriverException : "+e.getMessage(), "fail");
		} 
		return text;
	}

	public void  takeSnap() throws InterruptedException {
		File src = driver.getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"/img"+System.currentTimeMillis()+".png";
		File des = new File(path);
		try {
			FileUtils.copyFile(src, des);
			Thread.sleep(3000);
			test.addScreenCaptureFromPath(path);
		} catch (IOException e) {
			System.err.println("IOException");
		}
		
		
	}

	public void closeBrowser() throws IOException {
		try {
			driver.close();
			reportStep("The browser is closed","pass");
		} catch (Exception e) {
			reportStep("The browser could not be closed","fail");
		}
	}

	public void closeAllBrowsers() throws IOException {
		try {
			driver.quit();
			reportStep("The opened browsers are closed","pass");
		} catch (Exception e) {
			reportStep("Unexpected error occured in Browser","fail");
		}
	}

}
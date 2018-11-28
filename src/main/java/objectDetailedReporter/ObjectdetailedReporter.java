package objectDetailedReporter;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.webdriverfactory.com.SeleniumHelper;

public class ObjectdetailedReporter {
	public static ExtentReports extent;
	public static ExtentTest test;
	public static String testCaseName, testCaseDescription, author, category;
	
	public void startResult() {
		ExtentHtmlReporter html = new ExtentHtmlReporter
				(System.getProperty("user.dir")+"/result"+System.currentTimeMillis()+".html");
		html.setAppendExisting(true);
		extent = new ExtentReports();
		extent.attachReporter(html);
	}
	
	public void startTestCase() {
		test = extent
				.createTest(testCaseName, testCaseDescription);
		test.assignAuthor(author);
		test.assignCategory(category);
	}
	
	public void reportStep(String desc,String status)  {
		if (status.equalsIgnoreCase("pass")) {
			test.pass(desc);
			//test.addScreenCaptureFromPath(Se.takeSnap());
		}if (status.equalsIgnoreCase("fail")) {
			test.fail(desc);
		}
	}
	
	public void stopResult(){		
		extent.flush();
	}
}






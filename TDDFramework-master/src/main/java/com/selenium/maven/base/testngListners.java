package com.selenium.maven.base;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.IReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;




public class testngListners extends base implements ITestListener, IReporter {
	
	ExtentReports extent = new ExtentReports();	
	
	private static final Logger logger = LogManager.getLogger(testngListners.class);
	WebDriver driver;

	
	
	public void onStart(ITestContext context) {
		logger.info("onStart");
		
	}
	
	public void onTestStart(ITestResult result) {
		String className = result.getInstanceName();  
		String filePath = System.getProperty("user.dir") + "\\test-output\\extentReports\\" +className;
		File DestFile = new File(filePath + ".html");
		if (DestFile.exists()) {
			int cnt = 1;
			do {
				DestFile = new File(filePath + "-(" + cnt + ").html");
				cnt++;
			} while (DestFile.exists());
		}
		ExtentSparkReporter spark = new ExtentSparkReporter(DestFile);
		extent.attachReporter(spark);
		logger.info(result.getName()+" : Test started");
	}

	public void onTestSuccess(ITestResult result) {
		ExtentTest testCase = extent.createTest(result.getName());
		testCase.log(Status.PASS, result.getName()+" : Test passed");
	
	    logger.info(result.getName()+" : Test passed");
	}

	public void onTestFailure(ITestResult result) {
		ExtentTest testCase = extent.createTest(result.getName());
		String className = result.getInstanceName();  
		String scFilePath = takeScreenshot(driver,null, className+"\\"+result.getName());
		logger.info(result.getName()+" : Test failed");
		testCase.log(Status.FAIL, result.getName()+" : Test failed");
		testCase.addScreenCaptureFromPath(scFilePath);
	}

	public void onTestSkipped(ITestResult result) {
		ExtentTest testCase = extent.createTest(result.getName());
		testCase.log(Status.SKIP,result.getName()+" : Test skipped");
		logger.info(result.getName()+" : Test skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		ExtentTest testCase = extent.createTest(result.getName());
		String className = result.getInstanceName();  
		String scFilePath = takeScreenshot(driver,null, className+"\\"+result.getName());
		logger.info(result.getName()+" : Test failed within percentage");
		testCase.log(Status.FAIL, result.getName()+" : Test failed within percentage");
		testCase.addScreenCaptureFromPath(scFilePath);
	}

	public void onFinish(ITestContext context) {
		extent.flush();
		logger.info("onFinish");
	}
}

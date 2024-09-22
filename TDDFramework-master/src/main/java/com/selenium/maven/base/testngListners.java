package com.selenium.maven.base;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class testngListners extends base implements ITestListener {

    private static final Logger logger = LogManager.getLogger(testngListners.class);
    private static ExtentReports extent;
    private static ExtentTest testCase;
    WebDriver driver;

    public void onStart(ITestContext context) {
        logger.info("Test suite started");

        // Initialize ExtentReports
        extent = new ExtentReports();
        String reportPath = System.getProperty("user.dir") + "\\test-output\\extentReports\\testReport.html";
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        extent.attachReporter(spark);
    }

    public void onTestStart(ITestResult result) {
        testCase = extent.createTest(result.getMethod().getMethodName());
        logToBoth(result.getMethod().getMethodName() + " : Test started", Status.INFO);
    }

    public void onTestSuccess(ITestResult result) {
        logToBoth(result.getMethod().getMethodName() + " : Test passed", Status.PASS);
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		ExtentTest testCase = extent.createTest(result.getName());
		String className = result.getInstanceName();  
		String scFilePath = takeScreenshot(driver,null, className+"\\"+result.getName());
		logger.info(result.getName()+" : Test failed within percentage");
		testCase.log(Status.FAIL, result.getName()+" : Test failed within percentage");
		testCase.addScreenCaptureFromPath(scFilePath);
	}

    public void onTestSkipped(ITestResult result) {
        logToBoth(result.getMethod().getMethodName() + " : Test skipped", Status.SKIP);
    }

    public void onFinish(ITestContext context) {
        logger.info("Test suite finished");
        if (extent != null) {
            extent.flush();  // Writing the report to file
        }
    }

    // Custom method to log both to Log4j and Extent Reports
    public static void logToBoth(String message, Status status) {
        logger.info(message);  // Log to Log4j
        if (testCase != null) {
            testCase.log(status, message);  // Log to Extent Reports
        }
    }
}

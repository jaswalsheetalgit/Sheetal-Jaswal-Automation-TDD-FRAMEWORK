package com.selenium.maven.tests;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.selenium.maven.base.ExcelUtils;
import com.selenium.maven.pages.Homepages;
import com.selenium.maven.pages.Login_SwagLabs;
import com.selenium.maven.pages.homePage;


@Listeners(com.selenium.maven.base.testngListners.class)  
public class TESTCASE1 extends homePage{
	ExcelUtils excelUtils;
	Login_SwagLabs login = new Login_SwagLabs();
	Homepages homepage = new Homepages();
	public static Logger logger = LogManager.getLogger(TESTCASE1.class);
	
	@BeforeTest
	public void initiate() throws IOException {
		login.startSession("chrome");
		logger.info("Started session");	
		excelUtils = new ExcelUtils("E:\\Github\\com.selenium.maven2\\src\\test\\resources\\TestDatasheet.xlsx", "Sheet1");
	}

	@Test(priority=0,groups="Regression",description="Verify home page title.")
	public void LoginApplication() throws IOException, InterruptedException {
	    String url = excelUtils.getCellData("TESTCASE 1", "url");  // Row name "Test1", read value from 2nd column
	    System.out.println(url);
//		login.Logins("standard_user", "secret_sauce");	
//		homepage.AddItem();
	}
	
	
	
	@AfterTest
	public void endSession() {
		login.closeSession();
		logger.info("Ending session");
	}
}

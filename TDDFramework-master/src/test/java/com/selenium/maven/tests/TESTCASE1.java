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

import com.selenium.maven.base.CommonFunctions;
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
	
	@Test(priority=0,groups="Regression",description="End to End Add to cart product")
	public void Addtocartproduct() throws IOException, InterruptedException {
		logger.info("Logins Method");
		String userName = excelUtils.getCellData("TESTCASE 1", "UsernName");
		String password = excelUtils.getCellData("TESTCASE 1", "Password");
		login.Logins(userName, password);
		
		logger.info("AddItem Method");
		String Item = excelUtils.getCellData("TESTCASE 1", "Item");		
	    homepage.AddItem(Item);
	    
	    logger.info("checkout Method");
	    homepage.checkout();
	    
	    logger.info("Paymentpage Method");
	    String FirstName = excelUtils.getCellData("TESTCASE 1", "FirstName");
		String LastName = excelUtils.getCellData("TESTCASE 1", "LastName");
		String PostalCode = excelUtils.getCellData("TESTCASE 1", "PostalCode");
	    homepage.Paymentpage(FirstName,LastName,PostalCode);
	    
	    logger.info("verifyProduct Method");
	    homepage.verifyProduct(Item);

	}

	@AfterTest
	public void endSession() {
		login.closeSession();
		logger.info("Ending session");
	}
}

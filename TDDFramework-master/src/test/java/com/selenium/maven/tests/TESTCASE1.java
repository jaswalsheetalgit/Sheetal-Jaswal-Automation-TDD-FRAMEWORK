package com.selenium.maven.tests;

import java.io.IOException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.selenium.maven.base.ExcelUtils;
import com.selenium.maven.base.testngListners;
import com.selenium.maven.pages.Homepages;
import com.selenium.maven.pages.Login_SwagLabs;
import com.selenium.maven.pages.homePage;

@Listeners(com.selenium.maven.base.testngListners.class)
public class TESTCASE1 extends homePage {
    ExcelUtils excelUtils;
    Login_SwagLabs login = new Login_SwagLabs();
    Homepages homepage = new Homepages();

    @BeforeTest
    public void initiate() throws IOException {
       testngListners.logToBoth("Starting session...", com.aventstack.extentreports.Status.INFO);
        login.startSession("chrome");
         testngListners.logToBoth("Session started with ChromeDriver.", com.aventstack.extentreports.Status.INFO);

        excelUtils = new ExcelUtils("E:\\Github\\com.selenium.maven3\\src\\test\\resources\\TestDatasheet.xlsx", "Sheet1");
        testngListners.logToBoth("ExcelUtils initialized.", com.aventstack.extentreports.Status.INFO);
    }

    @Test(priority = 0, groups = "Regression", description = "End to End Add to cart product")
    public void Addtocartproduct() throws IOException, InterruptedException {
        testngListners.logToBoth("Executing Addtocartproduct test.", com.aventstack.extentreports.Status.INFO);

        String userName = excelUtils.getCellData("TESTCASE 1", "UsernName");
        String password = excelUtils.getCellData("TESTCASE 1", "Password");
        testngListners.logToBoth("Retrieved username and password from Excel.", com.aventstack.extentreports.Status.INFO);

        login.Logins(userName, password);
        testngListners.logToBoth("User logged in successfully.", com.aventstack.extentreports.Status.INFO);

        String item = excelUtils.getCellData("TESTCASE 1", "Item");
        testngListners.logToBoth("Adding item to cart: " + item, com.aventstack.extentreports.Status.INFO);
        homepage.AddItem(item);

        testngListners.logToBoth("Proceeding to checkout.", com.aventstack.extentreports.Status.INFO);
        homepage.checkout();

        String firstName = excelUtils.getCellData("TESTCASE 1", "FirstName");
        String lastName = excelUtils.getCellData("TESTCASE 1", "LastName");
        String postalCode = excelUtils.getCellData("TESTCASE 1", "PostalCode");
        testngListners.logToBoth("Filling payment details: " + firstName + " " + lastName + ", " + postalCode, com.aventstack.extentreports.Status.INFO);
        homepage.Paymentpage(firstName, lastName, postalCode);

        testngListners.logToBoth("Verifying the product in the cart.", com.aventstack.extentreports.Status.INFO);
        homepage.verifyProduct(item);
    }
    

    @AfterTest
    public void endSession() throws IOException {
    	excelUtils.close();
        testngListners.logToBoth("Ending session...", com.aventstack.extentreports.Status.INFO);
        login.closeSession();
        testngListners.logToBoth("Session ended.", com.aventstack.extentreports.Status.INFO);
    }
}

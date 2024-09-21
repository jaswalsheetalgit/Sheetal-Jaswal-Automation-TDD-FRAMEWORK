package com.selenium.maven.pages;

import java.util.Set;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import com.selenium.maven.base.base;
import com.selenium.maven.base.CommonFunctions;

@Listeners(com.selenium.maven.base.testngListners.class) 
public class Login_SwagLabs extends base{
	
	public static Logger logger = LogManager.getLogger(Login_SwagLabs.class);
	By userName = By.xpath("//*[@id='user-name']");
	By password = By.xpath("//*[@id='password']");
	By LoginButton = By.xpath("//*[@id='login-button']");
	
    public void Logins(String username, String Password) throws InterruptedException 
    {
    	logger.info("Login Page");
    	CommonFunctions.sendKeysToElement(driver, userName, username, "Enter the username");
    	CommonFunctions.sendKeysToElement(driver, password, Password, "Enter the Password");
    	CommonFunctions.clickElement(driver, LoginButton, "Click on Login Button");
    	Thread.sleep(6000);
	}
}

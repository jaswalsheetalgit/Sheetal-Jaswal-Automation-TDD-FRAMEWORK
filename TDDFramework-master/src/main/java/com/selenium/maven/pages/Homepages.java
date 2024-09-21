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
import org.testng.Assert;
import org.testng.annotations.Listeners;
import com.selenium.maven.base.base;
import com.selenium.maven.base.CommonFunctions;

@Listeners(com.selenium.maven.base.testngListners.class) 
public class Homepages extends base{
	public static Logger logger = LogManager.getLogger(Homepages.class);
	By Addtocart = By.xpath("(//button[text()='Add to cart'])[1]");
	By basket = By.xpath("//*[@id='shopping_cart_container']");
	By checkoOut = By.xpath("//*[@id='checkout']");
	By Item = By.xpath("//div[text()='Sauce Labs Backpack']");
	By FirstName = By.xpath("//*[@id='first-name']");
	By LastName = By.xpath("//*[@id='last-name']");
	By Postal = By.xpath("//*[@id='postal-code']");
	By ContinueButton = By.xpath("//*[@id='continue']");
	By FinishBtn = By.xpath("//*[@id='finish']");
	By headingText = By.xpath("//*[text()='Thank you for your order!']");
	By backHome = By.xpath("//*[@id='back-to-products']");
	
	public boolean verifySearchBarPresence() 
	{
		return driver.findElement(Addtocart).isDisplayed();
	}

	public String verifyItem() 
	{
		return driver.findElement(Item).getText();
			
	}
	
	public String headingTexts() 
	{
		return driver.findElement(headingText).getText();
			
	}
	
    public void AddItem(String Items) throws InterruptedException 
    {
    	logger.info("Add to cart method name AddItem");
    	CommonFunctions.highlightElement(driver, driver.findElement(Addtocart), "verify  Add to cart");
    	CommonFunctions.clickElement(driver, Addtocart, "Click Add to cart");
    	CommonFunctions.clickElement(driver, basket, "Click on Basket");
    	CommonFunctions.highlightElement(driver, driver.findElement(Item), "verify Item");
    	Assert.assertEquals(verifyItem(), Items);
    	Thread.sleep(2000);
    
	}
    
    public void checkout() throws InterruptedException 
    {
    	logger.info("Click on chekout");
    	CommonFunctions.clickElement(driver, checkoOut, "Click on checkout");
    
	}
    
    public void Paymentpage(String Firstname,String Lastname,String PostalCode) throws InterruptedException 
    {
    	logger.info("Payment page");   	
    	CommonFunctions.sendKeysToElement(driver, FirstName, Firstname, "Enter the Firstname "+Firstname);
    	CommonFunctions.sendKeysToElement(driver, LastName, Lastname, "Enter the Lastname "+Lastname);
    	CommonFunctions.sendKeysToElement(driver, Postal, PostalCode, "Enter the PostalCode "+PostalCode);
    	CommonFunctions.clickElement(driver, ContinueButton, "Click on Continue Button");
    	Thread.sleep(2000);   
	}
    
    public void verifyProduct(String Items) throws InterruptedException 
    {
    	logger.info("verify Item page");   	
    	CommonFunctions.highlightElement(driver, driver.findElement(Item), "verify Item");
    	Assert.assertEquals(verifyItem(), Items);
    	Thread.sleep(2000);
    	CommonFunctions.click(driver, FinishBtn, "Click on Finish Button");
    	CommonFunctions.highlightElement(driver, driver.findElement(headingText), "verify headingText");
    	Assert.assertEquals(headingTexts(), "Thank you for your order!");
    	Thread.sleep(2000); 
    	CommonFunctions.click(driver, backHome, "Click on backHome");
    	
    	
   
	}

	
}

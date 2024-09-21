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
public class Homepages extends base{
	public static Logger logger = LogManager.getLogger(Homepages.class);
	By Addtocart = By.xpath("(//button[text()='Add to cart'])[1]");
	By basket = By.xpath("//*[@id='shopping_cart_container']");
	By checkoOut = By.xpath("//*[@id='checkou']");
	
    public void AddItem() throws InterruptedException 
    {
    	logger.info("Add to cart method name AddItem");
    	CommonFunctions.clickElement(driver, Addtocart, "Click Add to cart");
    	CommonFunctions.clickElement(driver, basket, "Click on Basket");
    	CommonFunctions.clickElement(driver, checkoOut, "Click on checkout");
    	Thread.sleep(6000);
	}

	
}

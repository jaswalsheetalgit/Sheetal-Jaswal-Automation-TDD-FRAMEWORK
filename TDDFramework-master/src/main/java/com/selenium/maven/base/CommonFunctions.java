package com.selenium.maven.base;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import com.selenium.maven.pages.Login_SwagLabs;
import com.selenium.maven.pages.homePage;
import com.selenium.maven.base.base;
@Listeners(com.selenium.maven.base.testngListners.class)
public class CommonFunctions extends testngListners {
	static base obj = new base();
public static Logger logger = LogManager.getLogger(Login_SwagLabs.class);

public void initiate() throws IOException {
    testngListners.logToBoth("Starting session...", com.aventstack.extentreports.Status.INFO);
      testngListners.logToBoth("Session started with ChromeDriver.", com.aventstack.extentreports.Status.INFO);

     testngListners.logToBoth("ExcelUtils initialized.", com.aventstack.extentreports.Status.INFO);
 }


	public static void sendKeysToElement(WebDriver driver, By locator, String text,String descriptiuon) {
		try {
			// Find the web element
			WebElement element = driver.findElement(locator);
			element.clear();
			element.sendKeys(text);
			System.out.println(descriptiuon);
			testngListners.logToBoth("Retrieved username and password from clcik.", com.aventstack.extentreports.Status.PASS);
		} catch (Exception e) {
			 obj.takeScreenshot(driver,locator,descriptiuon+" Element Not Found");
			 System.out.println(descriptiuon+" Element Not Found");
			 Assert.assertEquals("Element Not Found",locator+" Element Not Found");
		}
	}

	public static void clickElement(WebDriver driver, By locator, String descriptiuon) {
		try {
			// Find the element using the locator
			WebElement element = driver.findElement(locator);
			element.click();          
			System.out.println(descriptiuon);
		} catch (NoSuchElementException e) {
			 obj.takeScreenshot(driver,locator,descriptiuon+" Element Not Click");
			 System.out.println(descriptiuon+" Element Not Click");
			 Assert.assertEquals("Element Click",locator+" Element Not Click");
		} catch (Exception e) {
			testngListners.logToBoth("Retrieved username and password from clcik.", com.aventstack.extentreports.Status.FAIL);
			System.out.println("An error occurred while clicking the element: " + e.getMessage());
		}
	}

	public static void selectElementByVisibleText(WebDriver driver, By locator, String text,String descriptiuon) {
		try
		{
		WebElement element = driver.findElement(locator);
		Select select = new Select(element);
		select.selectByVisibleText(text);
		}
		catch (Exception e) {
			obj.takeScreenshot(driver,locator,descriptiuon+" Element Not Found");
			 System.out.println(descriptiuon+" Element Not Found");
			 Assert.assertEquals("Element Not Found",locator+" Element Not Found");
			// TODO: handle exception
		}

	}

	public static void selectElementByValue(WebDriver driver, By locator, String value,String descriptiuon) {
		try
		{
		WebElement element = driver.findElement(locator);
		Select select = new Select(element);
		select.selectByValue(value);
		}
		catch (Exception e) {
			obj.takeScreenshot(driver,locator,descriptiuon+" Element Not Found");
			 System.out.println(descriptiuon+" Element Not Found");
			 Assert.assertEquals("Element Not Found",locator+" Element Not Found");
			// TODO: handle exception
		}

	}

	public static void selectElementByIndex(WebDriver driver, By locator, int index,String descriptiuon) {
		try
		{
		WebElement element = driver.findElement(locator);
		Select select = new Select(element);
		select.selectByIndex(index);
		}
		catch (Exception e) {
			obj.takeScreenshot(driver,locator,descriptiuon+" Element Not Found");
			 System.out.println(descriptiuon+" Element Not Found");
			 Assert.assertEquals("Element Not Found",locator+" Element Not Found");
			// TODO: handle exception
		}
	}

	public static void deselectAllOptions(WebDriver driver, By locator,String descriptiuon) {
		try
		{
		WebElement element = driver.findElement(locator);
		Select select = new Select(element);
		select.deselectAll();
		}
		catch (Exception e) {
			obj.takeScreenshot(driver,locator,descriptiuon+" Element Not Found");
			 System.out.println(descriptiuon+" Element Not Found");
			 Assert.assertEquals("Element Not Found",locator+" Element Not Found");
			// TODO: handle exception
		}
	}

	public static boolean isMultipleSelect(WebDriver driver, By locator,String descriptiuon) {
		try
		{
		WebElement element = driver.findElement(locator);
		Select select = new Select(element);
		return select.isMultiple();
		}
		catch (Exception e) {
			obj.takeScreenshot(driver,locator,descriptiuon+" Element Not Found");
			 System.out.println(descriptiuon+" Element Not Found");
			 Assert.assertEquals("Element Not Found",locator+" Element Not Found");
			// TODO: handle exception
		}
		return false;

	}

	public static void hoverOverElement(WebDriver driver, By locator,String descriptiuon) {
		try
		{
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform(); 
		}
		catch (Exception e) {
			obj.takeScreenshot(driver,locator,descriptiuon+" Element Not Found");
			 System.out.println(descriptiuon+" Element Not Found");
			 Assert.assertEquals("Element Not Found",locator+" Element Not Found");
			// TODO: handle exception
		}

    }

    public static void dragAndDrop(WebDriver driver, By sourceLocator, By targetLocator,String descriptiuon) {
    	try
    	{
        WebElement sourceElement = driver.findElement(sourceLocator);
        WebElement targetElement = driver.findElement(targetLocator);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(sourceElement, targetElement).perform();
    	}
    	catch (Exception e) {
			obj.takeScreenshot(driver,sourceLocator,descriptiuon+" Element Not Found");
			 System.out.println(descriptiuon+" Element Not Found");
			 Assert.assertEquals("Element Not Found",sourceLocator+" Element Not Found");
			 obj.takeScreenshot(driver,targetLocator,descriptiuon+" Element Not Found");
			 System.out.println(descriptiuon+" Element Not Found");
			 Assert.assertEquals("Element Not Found",targetLocator+" Element Not Found");
			// TODO: handle exception
		}
    }

    public static void contextClick(WebDriver driver, By locator,String descriptiuon) {
    	try
    	{
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();  
    	}
    	catch (Exception e) {
			obj.takeScreenshot(driver,locator,descriptiuon+" Element Not Found");
			 System.out.println(descriptiuon+" Element Not Found");
			 Assert.assertEquals("Element Not Found",locator+" Element Not Found");
			// TODO: handle exception
		}

    }
    
    public static void click(WebDriver driver, By locator,String descriptiuon) {
    	try
    	{
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.click(element).perform();  
    	}
catch (Exception e) {
	testngListners.logToBoth("Retrieved username and password from clcik.", com.aventstack.extentreports.Status.FAIL);
	obj.takeScreenshot(driver,locator,descriptiuon+" Element Not Found");
	 System.out.println(descriptiuon+" Element Not Found");
	 Assert.assertEquals("Element Not Found",locator+" Element Not Found");
	// TODO: handle exception
}
	// TODO: handle exception
}
    
    
    public static void highlightElement(WebDriver driver, WebElement elementToHighlight,String descriptiuon) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String originalStyle = elementToHighlight.getAttribute("style");
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", 
                elementToHighlight, "border: 2px solid red; background-color: yellow;");   
        try {
            Thread.sleep(500);  // Optional: highlight duration
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", 
                elementToHighlight, originalStyle);
    }


}

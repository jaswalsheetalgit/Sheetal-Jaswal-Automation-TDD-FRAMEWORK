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
public class homePage extends base{
	public static Logger logger = LogManager.getLogger(homePage.class);
	By searchBox = By.id("twotabsearchtextbox");
	By searchIcon = By.id("nav-search-submit-button");
	By fstRsltItm = By.xpath("//div[@data-component-type='s-search-result'][@data-index='3']//h2//span");
	By lngDrpDwn = By.id("icp-nav-flyout");
	By lngOpns = By.id("nav-flyout-icp");
	By lngLst = By.cssSelector("#nav-flyout-icp .nav-tpl-itemList  a span i");
	By selectedLang = By.cssSelector("#icp-nav-flyout span:nth-child(2) div");
	By lngOp = By.cssSelector("#nav-flyout-icp .nav-tpl-itemList  a i[class='icp-radio']");
	By srchDrpDwn = By.id("searchDropdownBox");
	By srchDrpDwnLbl = By.id("nav-search-label-id");
	By brandFilter = By.xpath("//div[@id='brandsRefinements']//span[text()='Brand']");
	By brandsCheckbox = By.xpath("//div[@id='brandsRefinements']//div[@data-expanded='true']/ul/span//li");
	By brandsPanel = By.xpath("//div[@id='brandsRefinements']//div[@data-expanded='true']");
	By slctdBrand = By.xpath("//li//input[@checked='']");

	public boolean verifySearchBarPresence() {
		return driver.findElement(searchBox).isDisplayed();
	}
	
	public String searchProduct(String searchStr) {
		
		CommonFunctions.sendKeysToElement(driver, searchBox, searchStr, "sheetal Added Search string in search box");
		driver.findElement(searchIcon).click();
		logger.info("Clicked on search icon");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		String actualTitle = driver.getTitle();
		return actualTitle;
	}
	
	public int openFstItmFrmSrchRslt() {
		int cnt=0;
		String oldTabTitle = driver.getTitle();
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(fstRsltItm)).click().build().perform();
	//	driver.findElement(fstRsltItm).click();
		logger.info("Clicked on first item from search result");

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		System.out.println(driver.getTitle());
		
		Set<String> windws = driver.getWindowHandles();
		Iterator<String> winLst = windws.iterator();
		
		while(winLst.hasNext()) {
			driver.switchTo().window(winLst.next());
			String newTabTitle = driver.getTitle();
			if(oldTabTitle!=newTabTitle) {
				cnt++;
			}
		}
		return cnt;
	}
	
	public void closeTab() {		
		String newTabTitle = driver.getTitle();
		logger.info("Current tab which needs to be closed : "+newTabTitle);
		driver.close();
		logger.info("Closed tab");
		
		Set<String> windws = driver.getWindowHandles();
		Iterator<String> winLst = windws.iterator();
		
		while(winLst.hasNext()) {
			driver.switchTo().window(winLst.next());
			String nextTabTitle = driver.getTitle();
			logger.info("Main tab : "+nextTabTitle);
		}
	}
	
	public int changeLang() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		Actions act = new Actions(driver);
		int cnt=0;
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(lngDrpDwn)));
		
		FluentWait<WebDriver> fWit = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(10))
				.ignoring(StaleElementReferenceException.class);
		
		fWit.until(ExpectedConditions.visibilityOf(driver.findElement(lngDrpDwn)));

		act.scrollToElement(driver.findElement(lngDrpDwn));
		
		driver.findElement(lngDrpDwn).isDisplayed();
		logger.info("Language drop down is present");
		
		act.moveToElement(driver.findElement(lngDrpDwn)).perform();
		logger.info("Mouse hovered on language drop-down");
		String defaultLang = driver.findElement(selectedLang).getText();
	
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(lngOpns)));
		if(driver.findElement(lngOpns).isDisplayed())
			cnt++;
		
		List<WebElement> languagesList = driver.findElements(lngOp);
		Random rndm = new Random();
		int rndNum = rndm.nextInt(languagesList.size());
		do{
			languagesList.get(rndNum).click();
		}while(driver.findElement(selectedLang).getText()==defaultLang);
		cnt++;
		logger.info("Selected language randomly from language drop-down to : "+driver.findElement(selectedLang).getText());
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		do{
			languagesList.get(rndNum).click();
		}while(driver.findElement(selectedLang).getText()!=defaultLang);
		logger.info("Selected default language to : "+driver.findElement(selectedLang).getText());
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		
		return cnt;
	}

	public int selectSrchCat() throws InterruptedException {
		driver.findElement(srchDrpDwn).isDisplayed();
		logger.info("Search drop-down displayed");
		String defaultSrchDrpDwnCat = driver.findElement(srchDrpDwnLbl).getText();
		driver.findElement(srchDrpDwn).click();
		logger.info("Clicked on search drop down");
		
		Select dropDwnOpns = new Select(driver.findElement(srchDrpDwn));
		System.out.println(defaultSrchDrpDwnCat);
		Random rndNm = new Random();
		int num = rndNm.nextInt(dropDwnOpns.getOptions().size());
		
		dropDwnOpns.selectByIndex(num);
		logger.info("Selected random value from drop-down");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		String newSrchDrpDwnCat = driver.findElement(srchDrpDwnLbl).getText();
		System.out.println(newSrchDrpDwnCat);
		int cnt=0;
		if(defaultSrchDrpDwnCat!=newSrchDrpDwnCat)
			cnt++;
		
		return cnt;
	}
	
	//due to website's functionality working differently every time this test case will fail for different reason
	//though it can be used for javascriptExecutor example
	public int selectBrandsFromFilter() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(brandFilter)));
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(brandFilter));
		
		//driver.findElement(brandFilter).click();
		if(driver.findElement(brandsPanel).isDisplayed()==false)
		js.executeScript("arguments[0].click();", driver.findElement(brandFilter));
		logger.info("Clicked on brands filter");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(brandsPanel)));
		
		List<WebElement> brands = driver.findElements(brandsCheckbox);
		//wait.until(ExpectedConditions.visibilityOfAllElements(brands));
		
		System.out.println(brands.size());
		Random rndm = new Random(brands.size());
		int rndNum = rndm.nextInt();
		brands.get(rndNum).click();
		logger.info("Clicked on random brand");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(slctdBrand)));
		int cnt=0;
		if(driver.findElement(slctdBrand).isDisplayed())
			cnt++;
		return cnt;
	}
	
	
}

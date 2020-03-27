package com.qa.hubspot.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementActions {
	
	//we need to create wrappers so
	//this element actions ll have lot of generic functions
	WebDriver driver;
	WebDriverWait wait;
	Actions action;
	
	//this is common for all the pages
	public ElementActions(WebDriver driver) {
		this.driver=driver;
		wait=new WebDriverWait(driver,AppConstants.DEFAULT_EXPLICIT_TIME_OUT);//so i can use at whenever i want
		action=new Actions(driver);
	}
	/*
	 * param get element
	 */
	public WebElement getElement(By locator)
	{
		WebElement element=null;
		
		try {
			//WebElement element=driver.findElement(locator);
				element=driver.findElement(locator);
				}
		catch(Exception e)
		{
			System.out.println("some exception occured while creating the webelement"+locator);
		}
		return element;//if the elemnt got created succefully then it will return element or else null
	}
	
	/*
	 * @param locator
	 */
	public void doClick(By locator)
	{
		getElement(locator).click();
	}
	
	/*
	 * @param sendkeys
	 */
	public void doSendKeys(By locator,String value)
	{
		getElement(locator).sendKeys(value);
	}
	
	/*
	 * param IsDisplayed
	 */
	public boolean doIsDisplayed(By locator)
	{
		return getElement(locator).isDisplayed();
	}
	
	/*
	 * param GetText
	 */
	public String doGetText(By locator)
	{
		return getElement(locator).getText();
	}
	
	/*
	 * param waitForElementPresent
	 */
	public void waitForElementPresent(By locator)
	{
	//wait=new WebDriverWait(driver,AppConstants.DEFAULT_EXPLICIT_TIME_OUT);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	/*
	 * param elementvisible
	 */
	
	public void waitForElementVisible(By locator)
	{
		WebElement ele=getElement(locator);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	/*
	 * param GetPageTitle
	 */
	public String doGetPageTitle(String title)
	{
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}
	//with actions class for do click and dosendkeys
	
	/*
	 * param action click keys
	 */
	public void doActionsClick(By locator)
	{
		//for this we need to create action class object
		action.click(getElement(locator)).build().perform();
	}
 	
	/*
	 * param action sendkeys
	 */
	public void doActionSendKeys(By locator,String value)
	{
		action.sendKeys(getElement(locator), value);
	}

	/*
	 * param action movetoelement
	 */
	
	public void doMoveToElement(By locator)
	{
		action.moveToElement(getElement(locator)).build().perform();
	}
}

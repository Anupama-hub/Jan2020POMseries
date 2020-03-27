package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.ElementActions;

public class HomePage extends BasePage {
	
	WebDriver driver;
	ElementActions elementActions;
	
	//By Homepageheader=By.cssSelector("h1.private-page__title");
	By Homepageheader=By.xpath("//i18n-string[text()='Take a closer look at how HubSpot works.']");
	By loggedInuserName=By.xpath("//div//a[@id='account-menu']");
	By loggedinName=By.cssSelector("div.user-info-name");
	
	//we need to create the 2 more locators for contacts tab as these elemnt is avialable in homepage
	By parentcontacts=By.id("nav-primary-contacts-branch");
	By childcontacts=By.id("nav-secondary-contacts");
	
	//1.construcotr of this class
	public HomePage(WebDriver driver)//CREate consturctor of the class
	{
		this.driver=driver;
		elementActions=new ElementActions(this.driver);
	}
	
	//2.page actions
	
	public String getHomepagetitle()
	{
		//String title=driver.getTitle();
		//return title;
		//return driver.getTitle();
		return elementActions.doGetPageTitle(AppConstants.HOME_PAGE_TITLE);
	}
	
	public String IsHomepgaeheaderExist()
	{
//		boolean header=driver.findElement(Homepageheader).isDisplayed();
//		return header;
		
//		return  driver.findElement(Homepageheader).isDisplayed();
//		if(driver.findElement(Homepageheader).isDisplayed())
//		{
//			return driver.findElement(Homepageheader).getText();
//		}
//		return null ;
		//need to add the explicit wait here
		elementActions.waitForElementPresent(Homepageheader);
		if(elementActions.doIsDisplayed(Homepageheader))
		{
			return elementActions.doGetText(Homepageheader);
		}
		return null ;
		
		
	}
	
	public String Isloggedinusername()
	{
//		driver.findElement(loggedInuserName).click();
////		boolean name=driver.findElement(loggedinName).isDisplayed();
////		return name;
//		
//		//return driver.findElement(loggedinName).isDisplayed();
//		if(driver.findElement(loggedinName).isDisplayed()) {
//			//String text=driver.findElement(loggedinName).getText();
//			return driver.findElement(loggedinName).getText();
//		}
//		return null;
		
		elementActions.doClick(loggedInuserName);
		//add wait here
		elementActions.waitForElementPresent(loggedinName);
		if(elementActions.doIsDisplayed(loggedInuserName)) {
			//String text=driver.findElement(loggedinName).getText();
			return elementActions.doGetText(loggedinName);
			
		}
		return null;
	
		
	}
	
	//create wrapper here
	public ContactsPage goToContactsPage()
	{
		clickOnContacts();//method chaining
		return new ContactsPage(driver);
	}
	
	//need to create pageaction for contacts as it is available in homepage
	public void clickOnContacts()
	{
		elementActions.waitForElementPresent(parentcontacts);
		elementActions.doClick(parentcontacts);
		elementActions.waitForElementPresent(childcontacts);
		elementActions.doClick(childcontacts);
		
	}
	
	
	
}

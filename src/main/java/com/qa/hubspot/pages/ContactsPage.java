package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pojo.Contacts;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.ElementActions;
import com.qa.hubspot.util.JavaScriptUtil;

public class ContactsPage extends BasePage {
	
	WebDriver driver;
	ElementActions elementActions;
	JavaScriptUtil jsutil;
	
	
	By createcontactbutton=By.xpath("//button[@type='button']//span[text()='Create contact'])[position()=1]");
	//By createcontactbutton=By.xpath("//button[contains(@type,'button') and contains(@data-button-use,'primary')]");
	By createcontactformbutton=By.xpath("(//button[@type='button']//span[text()='Create contact'])[position()=2]");
	
	By email=By.xpath("//input[@data-field='email']");
	By fname=By.xpath("//input[@data-field='firstname']");
	By lname=By.xpath("//input[@data-field='lastname']");
	By jobtitle=By.xpath("//input[@data-field='jobtitle']");
	
	//2.constructor of the class
	//when this cosntructro ll be called wen this we create objct of this class
	
	public ContactsPage(WebDriver driver)
	{
		this.driver=driver;
		elementActions =new ElementActions(driver);//create refrecne for this
		jsutil=new JavaScriptUtil(driver);
	}
	//3.get the contacts pagetitle
	public String getContactsPageTitle()
	{
//		String title=driver.getTitle();
//		return title;deleting these 2 lines of code
		
//		return driver.getTitle();-->deleting this line of code
		return elementActions.doGetPageTitle(AppConstants.CONTACTS_PAGE_TITLE);
		
	}
	
//	public void createNewContact(String emailid,String fstname,String lstname,String jobtit)
	public void createNewContact(Contacts contacts)
	{
		//driver.findElement(createcontact);
		//driver.switchTo().alert().accept();
		elementActions.waitForElementPresent(createcontactbutton);
		//driver.switchTo().alert().dismiss();
		elementActions.doClick(createcontactbutton);
		//we can also write
		//elementActions.doSendKeys(this.email,email);if we dint give the different paramters
//		elementActions.waitForElementPresent(email);
//		elementActions.doSendKeys(email, emailid);
//		elementActions.doSendKeys(fname, fstname);
//		elementActions.doSendKeys(lname, lstname);
////		elementActions.waitForElementVisible(jobtitle);
//		elementActions.doSendKeys(jobtitle, jobtit);
		
		elementActions.waitForElementPresent(email);
		elementActions.doSendKeys(email,contacts.getEmail());
		elementActions.doSendKeys(fname, contacts.getFirstName());
		elementActions.doSendKeys(lname, contacts.getLastName());
//		elementActions.waitForElementVisible(jobtitle);
		elementActions.doSendKeys(jobtitle, contacts.getJobTitle());
		
		//elementActions.doClick(createcontactformbutton);
		//elementActions.doActionsClick(createcontactformbutton);
		
		jsutil.clickElementByJS(elementActions.getElement(createcontactformbutton));
		
	}
}
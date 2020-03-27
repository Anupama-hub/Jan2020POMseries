package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pojo.Credentials;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.ElementActions;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {
	
	WebDriver driver;
	//in this we need to use the locators page locators or page objects
	ElementActions elementActions;
	//By locators-Page Objects
	
	By username=By.id("username");
	By password=By.id("password");
	By login=By.id("loginBtn");
	//By signup=By.xpath("//div[@class='signup_link']//following::a[@title='You have not Register than Please Click here for SignUp'][2]");
	By signup=By.xpath("//i18n-string[text()='Sign up']");
//	By failedtext=By.xpath("//div[@class='private-alert__inner']//h5");
//	By privacypolicy=By.xpath("//i18n-string[text()='Privacy Policy']");
	By errMesg=By.cssSelector("h5.private-alert__title");
	By random=By.id("random");
	
	
	//2. Constructor of Page class
		public LoginPage(WebDriver driver) {//when this cosntructro ll be called wen this we create objct of this class
		this.driver=driver;//this is local driver
		elementActions=new ElementActions(this.driver);
		}
		
		//3.page actions://page methods
		@Step("getting the page title")
		public String getPageTitle() 
		{
			
			//return driver.getTitle();
			return elementActions.doGetPageTitle(AppConstants.LOGIN_PAGE_TITLE);
		}
		
		@Step("verifying sign up link")
		public boolean verifySignUpLink()
		{
			elementActions.waitForElementPresent(signup);
			//return driver.findElement(signup).isDisplayed();
			return elementActions.doIsDisplayed(signup);
		}
		
//		public HomePage dologin(String emailid,String pwd)
		@Step("login with username:{0} and password:{1}")
		public HomePage dologin(Credentials credentials)
//		public void dologin(String emailid,String pwd)
		{
//			driver.findElement(username).clear();
//			driver.findElement(username).sendKeys(emailid);
//			driver.findElement(password).clear();
//			driver.findElement(password).sendKeys(pwd);
//			driver.findElement(login).click();
			//here i am adding wait
			elementActions.waitForElementPresent(username);//if this is there we get for pwd
//			elementActions.doSendKeys(username, emailid);
//			elementActions.doSendKeys(password, pwd);
			elementActions.doSendKeys(username, credentials.getEmailId());
			elementActions.doSendKeys(password, credentials.getPassword());
			elementActions.doClick(login);
			
 			return new HomePage(driver);//here we need to retun cause login will go and land homepage
			
		}
		
		public boolean checkLoginErrorMessg()
		{
//			return driver.findElement(errMesg).isDisplayed();
			return elementActions.doIsDisplayed(errMesg);
		}
		
}

package com.qa.hubspot.tests;

import java.sql.Driver;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.pojo.Credentials;
import com.qa.hubspot.util.AppConstants;

public class LoginPageTest {
	
	BasePage basepage;//object of the base page need to create refrence of the base page
	Properties prop;
	WebDriver driver;
	LoginPage loginpage;
	HomePage homepage;
	Credentials credentials;
	
	@BeforeTest
	public void setup()
	{
		 basepage=new BasePage();
		//BasePage basepage=new basepage();//THIS is cause this is limited to setup oly
		//so we have created at the class leve so we can use it anywer
		 prop=basepage.init_prop();//prop is calling config file which cntians url un,pwd etc
		 //it is returing prop so stroing in prop reference variable
		 driver=basepage.init_driver(prop);//init_driver is returning driver so we can initlize in driver reference
		 loginpage=new LoginPage(driver);//when we create the object loginpage then the constructor is called
		 credentials=new Credentials(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test(priority=2)
	public void verifyLoginPageTitleTest()
	{
		String title=loginpage.getPageTitle();
		System.out.println("login page title is:"+title);
		Assert.assertEquals(title,AppConstants.LOGIN_PAGE_TITLE);//Assertion should be inside the test level not on the main method
		
	}
	
	@Test(priority=1)
	public void verifySignUpLinkTest()
	{
		
		Assert.assertTrue(loginpage.verifySignUpLink());
	}
	
	@Test(priority=3)
	public void loginTest()
	{
//		homepage=loginpage.dologin(prop.getProperty("username"), prop.getProperty("password"));
		homepage=loginpage.dologin(credentials);
		String loggedinval=homepage.Isloggedinusername();
		System.out.println("logged in user is:"+loggedinval);
		Assert.assertEquals(loggedinval, prop.get("accountname"));;
		
	}
	
	@DataProvider
	public Object[][] getLoginInvalidData()//need to check all combinations of data
	{
		//here create 2 dimensional object array data can be anything
		Object data[][]={
			{"test@gmail.com","test"},
//			{"test@gmail.com"," "},
//			{" ","test"},
//			{"test","test"},
//			{" "," "}
				
		};
		return data;
	}
	//negative testing
//	@Test(priority=4,dataProvider="getLoginInvalidData",enabled=false)//thi data prvider associated with this
//	public void login_InvalidTestcase(String emailId,String pwd)
//	{
//		//loginpage.dologin("test@gamil.com", "test@123");
//		loginpage.dologin(emailId, pwd);
//		//loginpage.dologin(credentials);
//		//loginpage.checkLoginErrorMessg();
//		Assert.assertTrue(loginpage.checkLoginErrorMessg());//this will give u the boolean
//	}

/*	
//	@AfterTest
//	public void tearDown()
//	{
//		driver.quit();
//	}
*/
}


package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.pojo.Contacts;
import com.qa.hubspot.pojo.Credentials;
import com.qa.hubspot.util.AppConstants;

public class ContactsPageTest {

	BasePage basepage;//object of the base page need to create refrence of the base page
	Properties prop;
	WebDriver driver;
	LoginPage loginpage;//this is important to call login methods i.e user acct deatils is in loggeinpage
	HomePage homepage;
	ContactsPage contactspage;
	Credentials credentials;
	Contacts contacts;
	
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
		 //homepage=loginpage.dologin(prop.getProperty("username"), prop.getProperty("password"));//dologin returning HOmepage login
		 homepage=loginpage.dologin(credentials);
		 contactspage=homepage.goToContactsPage();
	}
	
	@Test(priority=1)
	public void verifyContactsPageTitleTest()
	{
		String title=contactspage.getContactsPageTitle();
		System.out.println("contacts page title is"+title);
		Assert.assertEquals(title, AppConstants.CONTACTS_PAGE_TITLE);
		
	}
	
	@Test(priority=2)
	public void createNewContactsTest(String email,String firstName,String lastName,String jobTitle)
	{
		//no need of creating the dataprovider here only one valu need to pass
//		contactspage.createNewContact("anu@gmail.com", "anu", "b","QA");
		contacts=new Contacts(email,firstName,lastName,jobTitle);
		contactspage.createNewContact(contacts);
	}
	
	
	
	
	
//	@AfterTest
//	public void tearDown()
//{
//		driver.quit();
//	}
}

package com.qa.hubspot.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	public WebDriver driver;
	public Properties prop;
	/*
	 * This method is used to initliaze the webdriver on the basis of browsername
	 * @param browsername
	 * @return this method will return driver instance
	 */

	
	public WebDriver init_driver(Properties prop)//give me the properties refrence
	//public webDriver init_driver(String browserName)
		{
		String browserName=prop.getProperty("browser");
		//String IsHeadless=prop.getProperty("headless");//this headless is not boolean cause prop.get will a;ways give string so store it in string
		
		boolean IsHeadless=Boolean.parseBoolean(prop.getProperty("headless"));//need to convert to boolean so write wrappers for this
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			if(IsHeadless)
			{
				ChromeOptions co=new ChromeOptions();
				co.addArguments("--headless");
				driver=new ChromeDriver(co);
			}
			else
			{
				driver=new ChromeDriver();
			}
			
		}
		else if(browserName.equals("firefox")) 
		{
			WebDriverManager.firefoxdriver().setup();
			if(IsHeadless)
			{
			FirefoxOptions fo=new FirefoxOptions();
			fo.addArguments("--headless");
			driver=new FirefoxDriver(fo);
			}
			else
			{
			driver=new FirefoxDriver();
		}
		}
		else if(browserName.equals("safari")) 
		{
			WebDriverManager.getInstance(SafariDriver.class).setup();
			driver=new SafariDriver();
		}
		else
		{
			System.out.println(browserName+"is not found,please pass the right browsername");
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}
	//intialise the chormedriver
	
	/*
	 * return this method retunrs the properties-prop available in config.properties
	 */
	public Properties init_prop()//this method will going to nteract with config file properites
	{
		prop=new Properties();
		try {
			FileInputStream ip=new FileInputStream(".\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties");
			prop.load(ip);//this fileinoutstream class is fetching the value\\it will load the properties
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("config file is not found...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
	}
}

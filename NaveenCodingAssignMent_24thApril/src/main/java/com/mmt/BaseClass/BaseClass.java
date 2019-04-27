package com.mmt.BaseClass;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseClass {
	
	
	private final String FilePath= System.getProperty("user.dir")+"/src/main/java/com/mmt/properties/config.Properties";
	
	FileInputStream fs = null;
	
	public static Properties Config = null;
	
	String BrowserName;
	
	public static WebDriver driver;
	
	
	
	
	@BeforeTest
	public void Setup() throws Exception {
		
		fs = new FileInputStream(new File(FilePath));
		Config=new Properties();
		Config.load(fs);
		
		BrowserName = Config.getProperty("BrowserName");

		if (BrowserName.equalsIgnoreCase("Chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver");
			driver = new ChromeDriver(options);
			//driver = new ChromeDriver();

			Reporter.log(BrowserName+" Opened");
			driver.manage().window().maximize();
		} else if (BrowserName.equalsIgnoreCase("Firefox")) {

			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/geckodriver");

			driver = new FirefoxDriver();
			Reporter.log(BrowserName+" Opened");
			//log.info(BrowserName+" Opened");
			driver.manage().window().maximize();

		} else if (BrowserName.equalsIgnoreCase("IE")) {

			driver = new InternetExplorerDriver();
			Reporter.log(BrowserName+" Opened");
			

		} else {
			Reporter.log(BrowserName+" is invalid");
			
			throw new Exception("Invalid Browser Name");
		}
		
		
		driver.get(Config.getProperty("Testing_URL"));
		Reporter.log(Config.getProperty("Testing_URL")+" Opened");
		//log.info(Config.getProperty("Testing_URL")+" Opened");
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("ImplicitWait")), TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
		
		
		
		
	}
	
	
@AfterTest
public void TearDown() {
	if(driver!=null) {
			driver.quit();
}
	}

}

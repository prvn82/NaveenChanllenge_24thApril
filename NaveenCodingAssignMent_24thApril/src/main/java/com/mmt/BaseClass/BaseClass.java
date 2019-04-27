package com.mmt.BaseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.mmt.Util.ExtentReportGenerator;

public class BaseClass {

	private final static String FilePath = System.getProperty("user.dir")
			+ "/src/main/java/com/mmt/properties/config.Properties";

	static FileInputStream fs = null;

	public static Properties Config = null;

	String BrowserName;

	public static WebDriver driver;
	
	public static ExtentReports extent = ExtentReportGenerator.createInstance();
    public static ThreadLocal<ExtentTest> test1 = new ThreadLocal<ExtentTest>();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	
	public static void fileSetup() {
		try {
			fs = new FileInputStream(new File(FilePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Config = new Properties();
		try {
			Config.load(fs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@BeforeTest
	public void Setup() throws Exception{

//		fs = new FileInputStream(new File(FilePath));
//		Config = new Properties();
//		Config.load(fs);
		fileSetup();

		BrowserName = Config.getProperty("BrowserName");

		if (BrowserName.equalsIgnoreCase("Chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");

			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");
			driver = new ChromeDriver(options);

			Reporter.log(BrowserName + " Opened");
			driver.manage().window().maximize();
		} else if (BrowserName.equalsIgnoreCase("Firefox")) {

			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/geckodriver");

			driver = new FirefoxDriver();
			Reporter.log(BrowserName + " Opened");
			// log.info(BrowserName+" Opened");
			driver.manage().window().maximize();
			Reporter.log(BrowserName + " Maximized");

		} else if (BrowserName.equalsIgnoreCase("IE")) {

			driver = new InternetExplorerDriver();
			Reporter.log(BrowserName + " Opened");

		} else {
			Reporter.log(BrowserName + " is invalid");

			throw new Exception("Invalid Browser Name");
		}

		driver.get(Config.getProperty("Testing_URL"));
		Reporter.log(Config.getProperty("Testing_URL") + " Opened");
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("ImplicitWait")),
				TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

	}

	@AfterTest
	public void TearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}

package com.mmt.BaseClass;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.mmt.Util.MMT_Util;

public class sampleCas {

	@Test
	public void main() throws InterruptedException{
		
	
	
//
	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver");
	WebDriver driver = new ChromeDriver();
//		
	driver.get("https://www.makemytrip.com");
	Reporter.log("This will be displayed in report");
	//log.info(Config.getProperty("Testing_URL")+" Opened");
	driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
	driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

	driver.findElement(By.xpath("//ul//a//span[2][text()='Flights']")).click();
	driver.findElement(By.xpath("//ul//li[text()='Round Trip']")).click();
	
	driver.findElement(By.xpath("//ul//li[text()='Round Trip']")).click();
//
		driver.findElement(By.xpath("//input[@id='fromCity']")).sendKeys("Mumbai");
//		
   List<WebElement> findElements = driver.findElements(By.xpath("//div[@class='react-autosuggest__section-container react-autosuggest__section-container--first']/ul/li"));
//
//	   
	   System.out.println(findElements.size());
//     
       for(WebElement e:findElements) {
//        
//
   	   if(e.getText().contains("Mumbai")) {
   		   e.click();
   		   break;
   	   }
       }
//       
     driver.findElement(By.xpath("//input[@id='toCity']")).sendKeys(Keys.TAB); 
       driver.findElement(By.xpath("//input[@id='toCity']")).sendKeys("Goa");
//		
	  

	   Thread.sleep(6000);
	   
	   List<WebElement> findElements1 = driver.findElements(By.xpath("//div[@class='react-autosuggest__section-container react-autosuggest__section-container--first']/ul/li"));
	   
      for(WebElement e:findElements1) {
//    	   
    	   System.out.println(e.getText());
//     
    	   if(e.getText().contains("Goa")) {
    		   e.click();
   		   break;
   	   }
       }
//
//		
		    Date DepartureDate = new Date();
		    Calendar c = Calendar.getInstance();
	        c.setTime(DepartureDate);

	        // manipulate date
	        
	        c.add(Calendar.DATE, 7); //same with c.add(Calendar.DAY_OF_MONTH, 1);
	     

	        // convert calendar to date
	        Date ArrivateDate = c.getTime();
		 

			if (DepartureDate.compareTo(ArrivateDate) > 0 || daysBetween(DepartureDate, ArrivateDate) > 7) {
				throw new SkipException(
						"This Method is restericated to accept 7 days difference between departure and Arrival date. Please check Given Date again. !!Contact Git Admin for more info");

			}

		

			SimpleDateFormat formatNowDay = new SimpleDateFormat("dd");
			SimpleDateFormat formatNowMonth = new SimpleDateFormat("MMMM");
			SimpleDateFormat formatNowYear = new SimpleDateFormat("YYYY");

			// Clicking on DepartureDate
			String Day1 = formatNowDay.format(DepartureDate);
			String Month1 = formatNowMonth.format(DepartureDate);
			String Year1 = formatNowYear.format(DepartureDate);
			if (Day1.startsWith("0")) {
				Day1 = Day1.substring(1);
			}
		
				String DepartureDateXpath = "//*[@class='DayPicker-Months']/div[*]/div/div[text()='" + Month1
						+ "']/span[text()='" + Year1 + "']//../../following-sibling::div[@class='DayPicker-Body']"
						+ "/div/div/div/p[text()='" + Day1 + "']/../..";
				
				System.out.println(DepartureDateXpath);
       
				
				driver.findElement(By.xpath("//label[@for='departure']")).click();

				Reporter.log("Clicking on date " + DepartureDate.toString());
				WebElement DepDate = driver.findElement(By.xpath(DepartureDateXpath));
				if (DepDate.isDisplayed()) {
					DepDate.click();
				}

				// Clicking on ArrivalDate
				String Day2 = formatNowDay.format(ArrivateDate);
				String Month2 = formatNowMonth.format(ArrivateDate);
				String Year2 = formatNowYear.format(ArrivateDate);

				if (Day2.startsWith("0")) {
					Day2 = Day2.substring(1);
				}

				String ArrivalDateXpath = "//*[@class='DayPicker-Months']/div[*]/div/div[text()='" + Month2
						+ "']/span[text()='" + Year2 + "']//../../following-sibling::div[@class='DayPicker-Body']"
						+ "/div/div/div/p[text()='" + Day2 + "']/../..";

				
				
				Reporter.log("Clicking on date " + ArrivateDate.toString());
				WebElement ArrivalDate = driver.findElement(By.xpath(ArrivalDateXpath));
				
				if (ArrivalDate.isDisplayed()) {
					ArrivalDate.click();
				
				}
				
				driver.findElement(By.xpath("//a[text()='Search']")).click();
				
//				WebDriverWait wait = new WebDriverWait(driver, 30);
//				wait.until(ExpectedConditions.jsReturnsValue("return document.readyState=='complete';"));
				
				Thread.sleep(10000);
//				
				JavascriptExecutor js = ((JavascriptExecutor) driver);
				
				for(int i=0;i<10;i++) {
				
				js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
				
				}
   
//				MMT_Util.checkPageIsReady();
//				MMT_Util.ScrollDownComplete();
			
				
				
				List<WebElement> findElements2 = driver.findElements(By.xpath("//div[@class='splitVw-sctn pull-left']/div[2]/div[@class='fli-list splitVw-listing']"));
				
				//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class='splitVw-sctn pull-right']/div[2]/div[@class='fli-list splitVw-listing']")));
				List<WebElement> findElements3 = driver.findElements(By.xpath("//*[@class='splitVw-sctn pull-right']/div[2]/div[@class='fli-list splitVw-listing']"));
				
				System.out.println(findElements2.size());
				System.out.println(findElements3.size());
				
				for(WebElement e:findElements2) {
					System.out.println(e.getText());
				}
				
				for(int i=0;i<10;i++) {
					
					js.executeScript("window.scrollTo(document.body.scrollHeight, 0);");
					
					}
				
       
				driver.findElement(By.xpath("//div[@id='fli_filter__stops']/span/label[@for='filter_stop0']")).click();
				
				Thread.sleep(10000);
//				
				//js = ((JavascriptExecutor) driver);
				
				for(int i=0;i<10;i++) {
				
				js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
				
				}
   
//				MMT_Util.checkPageIsReady();
//				MMT_Util.ScrollDownComplete();
			
				
				
				findElements2 = driver.findElements(By.xpath("//div[@class='splitVw-sctn pull-left']/div[2]/div[@class='fli-list splitVw-listing']"));
				
				//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class='splitVw-sctn pull-right']/div[2]/div[@class='fli-list splitVw-listing']")));
				findElements3 = driver.findElements(By.xpath("//*[@class='splitVw-sctn pull-right']/div[2]/div[@class='fli-list splitVw-listing']"));
				System.out.println("******************with filter**************");
				System.out.println(findElements2.size());
				System.out.println(findElements3.size());
				
				
                    for(int i=0;i<10;i++) {
					
					js.executeScript("window.scrollTo(document.body.scrollHeight, 0);");
					
					}
    			driver.findElement(By.xpath("//div[@id='fli_filter__stops']/span/label[@for='filter_stop0']")).click();

				driver.findElement(By.xpath("//div[@id='fli_filter__stops']/span/label[@for='filter_stop1']")).click();
				
				
				Thread.sleep(10000);
//				
				//js = ((JavascriptExecutor) driver);
				
				for(int i=0;i<10;i++) {
				
				js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
				
				}
   
//				MMT_Util.checkPageIsReady();
//				MMT_Util.ScrollDownComplete();
			
				
				
				findElements2 = driver.findElements(By.xpath("//div[@class='splitVw-sctn pull-left']/div[2]/div[@class='fli-list splitVw-listing']"));
				
				//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class='splitVw-sctn pull-right']/div[2]/div[@class='fli-list splitVw-listing']")));
				findElements3 = driver.findElements(By.xpath("//*[@class='splitVw-sctn pull-right']/div[2]/div[@class='fli-list splitVw-listing']"));
				System.out.println("******************with filter with one**************");
				System.out.println(findElements2.size());
				System.out.println(findElements3.size());
				
				

				
	}

	private static long daysBetween(Date one, Date two) {
		long difference = (one.getTime() - two.getTime()) / 86400000;
		return Math.abs(difference);
	}

}

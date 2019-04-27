package com.mmt.Pages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;

import com.mmt.BaseClass.BaseClass;
import com.mmt.Util.MMT_Util;

public class MMT_HomePage extends BaseClass {

	@FindBy(xpath = "//ul[@class='makeFlex font12']/li")
	private List<WebElement> MMT_Menu = new ArrayList<>();

	@FindBy(xpath = "//ul//li[text()='Round Trip']")
	private WebElement RoundTrip;

	@FindBy(xpath = "//ul//li[text()='Oneway']")
	private WebElement OneWayTrip;

	@FindBy(xpath = "//ul//li[text()='Multi City']")
	private WebElement MutiCity;

	@FindBy(xpath = "//input[@id='fromCity']")
	private WebElement From_City;

	@FindBy(xpath = "//input[@id='toCity']")
	private WebElement To_City;

	@FindBy(xpath = "//*[@id='react-autowhatever-1']/div[1]/div/p[text()='SUGGESTIONS ']")
	private WebElement CitySuggestion;

	@FindBy(xpath = "//div[@class='react-autosuggest__section-container react-autosuggest__section-container--first']/ul/li")
	private List<WebElement> Suggestion = new ArrayList<>();

	@FindBy(xpath = "//label[@for='departure']")
	private WebElement Cal;

	@FindBy(xpath = "//a[text()='Search']")
	private WebElement search;

	public MMT_HomePage() {
		PageFactory.initElements(driver, this);
	}

	public void ValidateTiltle() {
        Reporter.log("validating Page title...");
		Assert.assertEquals(driver.getTitle(),
				"MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights &amp; Holiday");
		test.get().info("validated Page title...");
	}

	// Method to Click MMT_Menu
	public void Select_MMT_Menu(String menu) {
		for (WebElement e : MMT_Menu) {
			if (e.getText().contains(menu)) {
				e.click();
				Reporter.log("Clicked on Menu "+ menu);
				test.get().info("Clicked on Menu "+ menu);
				break;
			}
		}
	}

	// Method to Click on type of Trip
	public void TripWay(String way) {
		if (way.equalsIgnoreCase("two")) {
			RoundTrip.click();
			Reporter.log("Select on Stopway filter "+ way);
			test.get().info("Select on Stopway filter "+ way);
		} else if (way.equalsIgnoreCase("one")) {
			OneWayTrip.click();
			Reporter.log("select on Stopway filter "+ way);
			test.get().info("Select on Stopway filter "+ way);
		} else {
			MutiCity.click();
			Reporter.log("select on Stopway filter "+ way);
			test.get().info("Select on Stopway filter "+ way);
		}
	}

	// Method to enter Cities for Search
	
	public boolean EnterFromCity(String FromCityName) throws InterruptedException {

		boolean flag = false;

		From_City.sendKeys(FromCityName, Keys.ENTER);
		Reporter.log("Enter Depature City "+ FromCityName);
		test.get().info("Enter Depature City "+ FromCityName);
		
		MMT_Util.Explicitwait(10, CitySuggestion);
		Reporter.log("waiting to populate Auto Suggestion after entering City..");
		test.get().info("waiting to populate Auto Suggestion after entering City..");

		for (WebElement e : Suggestion) {


			if (e.getText().toUpperCase().contains(FromCityName.toUpperCase())) {
				e.click();
				Reporter.log("selected city...");
				test.get().info("selected city...");
				flag = true;
				break;
			}
		}

		return flag;

	}

	public boolean EnterToCity(String ToCityName) throws InterruptedException {

		boolean flag = false;
		To_City.sendKeys(Keys.TAB);
		To_City.sendKeys(ToCityName, Keys.ENTER);
		Reporter.log("Enter Arrival City "+ ToCityName);
		test.get().info("Enter Arrival City "+ ToCityName);

		Thread.sleep(6000);
		Reporter.log("waiting to populate Auto Suggestion after entering City..");
		test.get().info("waiting to populate Auto Suggestion after entering City..");

		for (WebElement e : Suggestion) {

			if (e.getText().toUpperCase().contains(ToCityName.toUpperCase())) {
				e.click();
				Reporter.log("selected city...");
				test.get().info("selected city...");
				flag = true;
				break;
			}
		}

		return flag;

	}

	public boolean DatePicker(Date DepartureDate, Date ArrivateDate) {


		if (DepartureDate.compareTo(ArrivateDate) > 0 || daysBetween(DepartureDate, ArrivateDate) > 7) {
			throw new SkipException(
					"This Method is restericated to accept 7 days difference between departure and Arrival date. Please check Given Date again. !!Contact Git Admin for more info");

		}

		boolean StatusFlag = false;

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
		try {
			String DepartureDateXpath = "//*[@class='DayPicker-Months']/div[*]/div/div[text()='" + Month1
					+ "']/span[text()='" + Year1 + "']//../../following-sibling::div[@class='DayPicker-Body']"
					+ "/div/div/div/p[text()='" + Day1 + "']/../..";

			
			Cal.click();

			Reporter.log("Clicking on date " + Day1+"/"+Month1+"/"+Year1);
			WebElement DepDate = driver.findElement(By.xpath(DepartureDateXpath));
			if (DepDate.isDisplayed()) {
				MMT_Util.JavaScriptClick(DepDate);
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

			
			
			Reporter.log("Clicking on date " + Day2+"/"+Month2+"/"+Year2);
			WebElement ArrivalDate = driver.findElement(By.xpath(ArrivalDateXpath));
			
			if (ArrivalDate.isDisplayed()) {
				MMT_Util.JavaScriptClick(ArrivalDate);
			}
			StatusFlag = true;

		} catch (Exception e) {
			Reporter.log(e.getMessage());
			e.printStackTrace();

		}

		return StatusFlag;

	}

	public MMT_FlightResult ClickSearchButton() {

		search.click();
		Reporter.log("Clicked on Search button after entering all details");
		return new MMT_FlightResult();
		

	}
	

	private static long daysBetween(Date one, Date two) {
		long difference = (one.getTime() - two.getTime()) / 86400000;
		return Math.abs(difference);
	}

}

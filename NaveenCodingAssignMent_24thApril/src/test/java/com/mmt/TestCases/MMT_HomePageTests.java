package com.mmt.TestCases;

import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import com.mmt.BaseClass.BaseClass;
import com.mmt.Pages.MMT_HomePage;

public class MMT_HomePageTests extends BaseClass {

	private MMT_HomePage home;

	@BeforeClass
	public void init() {
		home = new MMT_HomePage();
	}

	@Test(priority = 1)
	public void ValidateHomePage() {
		home.ValidateTiltle();
	}

	@Test(priority = 2)
	public void SearchFlight() throws InterruptedException {
		
		home.Select_MMT_Menu("Flights");
		home.TripWay("two");
		Assert.assertTrue(home.EnterFromCity(Config.getProperty("From_City")));
		Assert.assertTrue(home.EnterToCity(Config.getProperty("To_City")));
		Date date1 = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date1);

		c.add(Calendar.DATE, 7); // Adding 7 Days in current date. this is arrival date.

		Date date2 = c.getTime();
		Assert.assertTrue(home.DatePicker(date1,date2));
	}
	

}

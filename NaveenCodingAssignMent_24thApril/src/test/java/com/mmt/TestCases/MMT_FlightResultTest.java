package com.mmt.TestCases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mmt.BaseClass.BaseClass;
import com.mmt.Pages.MMT_FlightResult;
import com.mmt.Pages.MMT_HomePage;
import com.mmt.Util.MMT_Util;
import com.mmt.dataStorage.DataProviderClass;

public class MMT_FlightResultTest extends BaseClass {
	

	private MMT_HomePage home;
	private MMT_FlightResult results;


	@BeforeClass
	public void init() {
		home = new MMT_HomePage();
		results = home.ClickSearchButton();
	}
	
	@Test(priority=1)
	public void ValidateNumberOfFlighstWithoutFilters() {
		System.out.println("*********** No of flights after without any filters***************");
		Reporter.log("*********** No of flights after without any filters***************");
		MMT_Util.ScrollDownComplete();
		
		results.numberOfDepartureFlight();
		results.numberOfArrivalFlight();
		
		
	}
	
	
	@Test(priority=2)
	public void ValidateNumberofFlightWith_NonStop() {
		System.out.println("***********No of flights after One STOP filter applied****************");
		Reporter.log("***********No of flights after One STOP filter applied***************");
		MMT_Util.ScrollUPComplete();
		results.StopsFlights("non stops");
		MMT_Util.ScrollDownComplete();
     	results.numberOfDepartureFlight();
		results.numberOfArrivalFlight();
		
	}
	
	@Test(priority=3)
	public void ValidateNumberofFlightWith_OneStop() {
		System.out.println("*********** No of flights after One STOP filter applied***************");
		Reporter.log("***********No of flights after One STOP filter applied****************");
		MMT_Util.ScrollUPComplete();
		results.StopsFlights("one stop");
		MMT_Util.ScrollDownComplete();
		results.numberOfDepartureFlight();
		results.numberOfArrivalFlight();
		MMT_Util.ScrollUPComplete();
		results.ClearStopFilter();
		MMT_Util.ScrollDownComplete();
		MMT_Util.ScrollUPComplete();
	}
	
	@Test(priority=4,dataProvider="getdata",dataProviderClass=DataProviderClass.class)
	public void SelectFlightAndValidateTotal(int depaInx, int ArrivalIdx) {
		
		results.SelectDepartureFlight(depaInx);
		results.SelectDArrivalFlight(ArrivalIdx);
		System.out.println("Selected Arrival flight Name: "+results.getArrival_Flightname());
		System.out.println("Selected Arrival flight Price: "+results.getArrival_FlightPrice());
		System.out.println("Selected Departure flight Name: "+results.getDepart_Flightname());
		System.out.println("Selected departure flight Price: "+results.getDepart_FlightPrice());
		
		Reporter.log("Selected Arrival flight Name: "+results.getArrival_Flightname());
		Reporter.log("Selected Arrival flight Name: "+results.getArrival_FlightPrice());
		Reporter.log("Selected Arrival flight Name: "+results.getDepart_Flightname());
		Reporter.log("Selected Arrival flight Name: "+results.getDepart_FlightPrice());
		
		
		test.get().info("Selected Arrival flight Name: "+results.getArrival_Flightname());
		test.get().info("Selected Arrival flight Name: "+results.getArrival_FlightPrice());
		test.get().info("Selected Arrival flight Name: "+results.getDepart_Flightname());
		test.get().info("Selected Arrival flight Name: "+results.getDepart_FlightPrice());
		
		

		Validate_SelectedFlightDetails();
		ValidateTotalFare();
		
	}
	
	
	public void Validate_SelectedFlightDetails() {
		Assert.assertTrue(results.ValidateSelectet_DepartureFlightDetails());
		Assert.assertTrue(results.ValidateSelectet_DepartureFlightDetails());

	}

	
	public void ValidateTotalFare() {
		Assert.assertTrue(results.validate_TotalFare());
	}
}

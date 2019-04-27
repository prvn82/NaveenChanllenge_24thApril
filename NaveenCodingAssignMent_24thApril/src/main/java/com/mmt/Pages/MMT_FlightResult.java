package com.mmt.Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.SkipException;

import com.mmt.BaseClass.BaseClass;
import com.mmt.Util.MMT_Util;

public class MMT_FlightResult extends BaseClass {

	private String Depart_FlightPrice;
	private String Depart_Flightname;

	private String Arrival_FlightPrice;
	private String Arrival_Flightname;

	public String getDepart_FlightPrice() {
		return Depart_FlightPrice;
	}

	private void setDepart_FlightPrice(String depart_FlightPrice) {
		Depart_FlightPrice = depart_FlightPrice;
	}

	public String getDepart_Flightname() {
		return Depart_Flightname;
	}

	private void setDepart_Flightname(String depart_Flightname) {
		Depart_Flightname = depart_Flightname;
	}

	public String getArrival_FlightPrice() {
		return Arrival_FlightPrice;
	}

	private void setArrival_FlightPrice(String arrival_FlightPrice) {
		Arrival_FlightPrice = arrival_FlightPrice;
	}

	public String getArrival_Flightname() {
		return Arrival_Flightname;
	}

	public void setArrival_Flightname(String arrival_Flightname) {
		Arrival_Flightname = arrival_Flightname;
	}

	@FindBy(xpath = "//div[@id='fli_filter__stops']/span/label[@for='filter_stop0']")
	private WebElement Non_Stop_Option;

	@FindBy(xpath = "//div[@id='fli_filter__stops']/span/label[@for='filter_stop1']")
	private WebElement oneStop_option;

	@FindBy(xpath = "//*[@class='splitVw-sctn pull-left']/div[2]/div")
	private List<WebElement> DepartureFlightList = new ArrayList<>();

	@FindBy(xpath = "//*[@class='splitVw-sctn pull-right']/div[2]/div")
	private List<WebElement> ArrivalFlightList = new ArrayList<>();

	@FindBy(xpath = "//div[@id='fli_filter__stops']/p/a[text()='Reset']")
	private WebElement Stops_Reset;

	@FindBy(xpath = "//div[@class='splitVw-footer-left ']")
	private WebElement Selected_DepartFlightDetails;

	@FindBy(xpath = "//div[@class='splitVw-footer-right ']")
	private WebElement Selected_ArrivalFlightDetails;

	@FindBy(xpath = "//div[@class='footer-fare']/p[2][@class='disc-applied']/span[2]")
	private WebElement discountPrice;

	@FindBy(xpath = "//div[@class='footer-fare']/p/span[@class='splitVw-total-fare']")
	private WebElement Flight_totalPrice;

	public MMT_FlightResult() {
		PageFactory.initElements(driver, this);
	}

	public void numberOfDepartureFlight() {
        
		System.out.println("Total number of departure flights " + DepartureFlightList.size());
		Reporter.log("Total number of departure flights " + DepartureFlightList.size());
		test.get().info("Total number of departure flights " + DepartureFlightList.size());

	}

	public void numberOfArrivalFlight() {

		System.out.println("Total number of departure flights " + ArrivalFlightList.size());
		Reporter.log("Total number of departure flights " + ArrivalFlightList.size());
		test.get().info("Total number of departure flights " + ArrivalFlightList.size());

	}

	public void StopsFlights(String typeOfStops) {

		//Stops_Reset.click();
		ClearStopFilter();
		if (typeOfStops.equalsIgnoreCase("non stops")) {
			Non_Stop_Option.click();
			Reporter.log("Clicked on filter "+typeOfStops);
			test.get().info("Clicked on filter "+typeOfStops);
			
		} else {
			oneStop_option.click();
			Reporter.log("Clicked on filter "+typeOfStops);
			test.get().info("Clicked on filter "+typeOfStops);
		}
	}

	public void ClearStopFilter() {
		Reporter.log("Clearing applied filters..");
		Stops_Reset.click();
		test.get().info("Clearing applied filters..");
	}

	public void SelectDepartureFlight(int Flight_Index) {
		int count = 0;
		if (DepartureFlightList.size() > Flight_Index && Flight_Index>0) {
			for (WebElement e : DepartureFlightList) {
				if (count == Flight_Index) {
					// System.out.println(e.getText());
					Departure_FetchFlightPirce(e);
					MMT_Util.JavaScriptClick(e);
					Reporter.log("Clicked on Flight number "+Flight_Index);
					test.get().info("Clicked on Flight number "+Flight_Index);
					break;
				}
				count++;

			}
		} else {
			throw new SkipException(
					"Given " + Flight_Index + " Index number is more than number of departure flight listed or Negative value");
		}
	}

	public void SelectDArrivalFlight(int Flight_Index) {
		int count = 0;
		if (ArrivalFlightList.size() > Flight_Index  && Flight_Index>0) {
			for (WebElement e : ArrivalFlightList) {
				if (count == Flight_Index) {
					Arrival_FetchFlightPirce(e);
					MMT_Util.JavaScriptClick(e);
					Reporter.log("Clicked on Flight number "+Flight_Index);
					test.get().info("Clicked on Flight number "+Flight_Index);
					break;
				}
				count++;

			}
		} else {
			throw new SkipException(
					"Given " + Flight_Index + " Index number is more than number of arrival flight listed or Negative value");
		}
	}

	private void Departure_FetchFlightPirce(WebElement e) {
		String flightDetail = e.getText();
		String[] FlightArray = flightDetail.split("\\r?\\n");
		setDepart_Flightname(FlightArray[0]);
		setDepart_FlightPrice(FlightArray[FlightArray.length - 1]);

	}

	private void Arrival_FetchFlightPirce(WebElement e) {
		String flightDetail = e.getText();
		String[] FlightArray = flightDetail.split("\\r?\\n");
		setArrival_Flightname(FlightArray[0]);
		setArrival_FlightPrice(FlightArray[FlightArray.length - 1]);

	}

	public boolean ValidateSelectet_DepartureFlightDetails() {
		boolean flag = false;
		String details = Selected_DepartFlightDetails.getText();

		if (details.replaceAll("\\s+", "").contains(getDepart_Flightname().replaceAll("\\s+", ""))
				&& details.replaceAll("\\s+", "").contains(getDepart_FlightPrice().replaceAll("\\s+", ""))) {
			flag = true;
		}

		return flag;
	}

	public boolean ValidateSelectet_ArrivalFlightDetails() {
		boolean flag = false;
		String details = Selected_ArrivalFlightDetails.getText();

		if (details.replaceAll("\\s+", "").contains(getArrival_Flightname().replaceAll("\\s+", ""))
				&& details.replaceAll("\\s+", "").contains(getArrival_FlightPrice().replaceAll("\\s+", ""))) {
			flag = true;
		}

		return flag;
	}
   
	private int Discountapplied() {
		
		int discount=0;
		
		try {
		if(discountPrice.isDisplayed()) {
			discount=MMT_Util.ConvertToIntPrice(discountPrice.getText());
		}
		}catch(Exception e) {
			//Note : No need to write any catch exception detail as we have to just handle exception. 
		}
		
		System.out.println("Applied discount is: "+discount+". Amount will be adjust from final fare");
		test.get().info("Applied discount is: "+discount+". Amount will be adjust from final fare");
		return discount;
		
	}
	
	public boolean validate_TotalFare() {
		boolean flag=false;
		int depatPrice = MMT_Util.ConvertToIntPrice(getDepart_FlightPrice());
		int ArrivalPrice=MMT_Util.ConvertToIntPrice(getArrival_FlightPrice());
		int totalFare =depatPrice+ArrivalPrice-Discountapplied();
		test.get().info("total flight cost for selected flights: "+totalFare);
		if(totalFare==MMT_Util.ConvertToIntPrice(Flight_totalPrice.getText())) {
			flag=true;
		}
		
		return flag;
	}
}

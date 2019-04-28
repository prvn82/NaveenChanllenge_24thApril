package com.mmt.dataStorage;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	
	

	@DataProvider
	public static Object[][] getdata(){
		
		Object[][] object = {{10,1},{3,4},{5,6},{8,3},{1,9},{5,3},{2,3},{300,4}};
		
		return object;
	}

}

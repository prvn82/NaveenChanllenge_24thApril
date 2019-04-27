package com.mmt.BaseClass;

import org.testng.annotations.Test;

import com.mmt.dataStorage.DataProviderClass;

public class Sample {

		
	@Test
	public void Test1() {
		String s="Rs 7,804";
		System.out.println(parseStringToInt(s));
	}
	
	
	public static int parseStringToInt(String s){
		
			
			if(s.contains("Rs")) {
				s=s.substring(3, s.length());
			}
			
			s = s.replaceAll(",", ""); //remove commas
	        return (int)Math.round(Double.parseDouble(s)); //return rounded double cast to int
		}
    

}

package com.mmt.Util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mmt.BaseClass.BaseClass;

public class MMT_Util extends BaseClass {

	static JavascriptExecutor je = (JavascriptExecutor) driver;

	public static void ClickElement(WebElement e) {
		e.click();
	}

	public static void SendKey(WebElement e, String s) {
		e.sendKeys(s);
	}

	public static void JavaScriptClick(WebElement e) {
		je.executeScript("arguments[0].click();", e);
	}

	public static void Explicitwait(int timeout, WebElement e) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(e));
	}

	public static void JavaScriptEnterValue(String value, WebElement e) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		System.out.println("arguments[0].value='" + value + "';");
		js.executeScript("arguments[0].value='" + value + "';", e);
	}

	public static void checkPageIsReady() {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		if (js.executeScript("return document.readyState").toString().equals("complete")) {
			System.out.println("Page Is loaded.");
			return;
		}

		for (int i = 0; i < 25; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}
		}
	}

	public static void ScrollDownComplete() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Scroll down completly to load all elements...");

		for (int i = 0; i < 10; i++) {

			je.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		}
	}
	
	public static void ScrollUPComplete() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Scroll Up...");

		for (int i = 0; i < 10; i++) {

			je.executeScript("window.scrollTo(document.body.scrollHeight,0);");

		}
	}
	
	public static int ConvertToIntPrice(String s) {
		
		if(s.contains("Rs")) {
			s=s.substring(3, s.length());
		}
		
		s = s.replaceAll(",", ""); //remove commas
        return (int)Math.round(Double.parseDouble(s)); //return rounded double cast to int
	}

}

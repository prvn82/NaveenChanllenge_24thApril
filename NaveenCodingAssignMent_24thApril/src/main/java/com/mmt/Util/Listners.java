package com.mmt.Util;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.mmt.BaseClass.BaseClass;

public class Listners extends BaseClass implements ITestListener{
	
//	private static ExtentReports extent = ExtentReportGenerator.createInstance();
//    private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
    private ExtentTest ParentextentTest;
    private ExtentTest ChildExtentTest;


	@Override
	public void onTestStart(ITestResult result) {
		    System.out.println((result.getMethod().getMethodName() + " started!"));
	        ChildExtentTest = ParentextentTest.createNode(result.getName());
	        test.set(ChildExtentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println((result.getMethod().getMethodName() + " passed!"));
        test.get().pass("Test passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String takeScreenShot = null;
		System.out.println((result.getMethod().getMethodName() + " failed!"));
		try {
			takeScreenShot = MMT_Util.takeScreenShot();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        test.get().fail(result.getThrowable());
        try {
			test.get().addScreenCaptureFromPath(takeScreenShot);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println((result.getMethod().getMethodName() + " skipped!"));
        test.get().skip(result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("Extent Reports Version 3 Test Suite started!");
        ParentextentTest = extent.createTest(context.getName());

		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println(("Extent Reports Version 3  Test Suite is ending!"));
        extent.flush();
		
	}
	
	
}

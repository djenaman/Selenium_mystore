package com.dipak.mystore.customListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.dipak.mystore.testBase.Baseclass;

import sun.util.resources.cldr.aa.CalendarData_aa_ER;

public class Clistner extends Baseclass implements ITestListener
{

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result)
	{
		SimpleDateFormat format=new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		Calendar cal=Calendar.getInstance();
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destfilepath=System.getProperty("user.dir")+"\\src\\main\\java\\com\\dipak\\mystore\\capturedScreenShot\\";
		File dest=new File(destfilepath+"_"+format.format(cal.getTime())+"_"+result.getName()+".jpeg");
		try {
			System.out.println("called");
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}

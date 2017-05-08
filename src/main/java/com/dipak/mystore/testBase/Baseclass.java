package com.dipak.mystore.testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;



public class Baseclass
{
	public static WebDriver driver;
	public final Logger log;
	public Baseclass()
	{
		PropertyConfigurator.configure("E:\\Selenium Practice\\MyStore\\log4j.properties");
		driver=null;
		log=Logger.getLogger(this.getClass().getName());
	}
	public void init(String Browser,String URL)
	{
		if(Browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//drivers//geckodriver.exe");
			driver=new FirefoxDriver();
			
		}
		else if(Browser.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"//drivers//IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
		else if(Browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//drivers//chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
		}
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(URL);
	}
	public void captureScreenShot(String name)
	{
		SimpleDateFormat formater=new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		Calendar cal=Calendar.getInstance();
		File srcfile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destfilepath=System.getProperty("user.dir")+"\\src\\main\\java\\com\\dipak\\mystore\\capturedScreenShot\\";
		File destfile=new File(destfilepath+"_"+formater.format(cal.getTime())+"_"+name+".png");

		try {
			FileUtils.copyFile(srcfile, destfile);
		} catch (IOException e) {

			e.printStackTrace();
		}
		Reporter.log("<a href='"+destfile.getAbsolutePath()+"'><img src="+"'"+destfile.getAbsolutePath()+"' height='100' width='100' /></a>");

	}
}

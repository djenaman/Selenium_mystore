package com.dipak.mystore.shopping;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dipak.mystore.pageActions.HomePage;
import com.dipak.mystore.pageActions.IndexPage;
import com.dipak.mystore.pageActions.LoginPage;
import com.dipak.mystore.pageActions.ShoppingPage_Blouse;
import com.dipak.mystore.testBase.Baseclass;

public class TC001_Women_Blouses_Shopping extends Baseclass {
	Properties props=null;
	FileInputStream fis=null;
	ShoppingPage_Blouse bpagobj=null;

	@BeforeClass
	public void initTetcase() throws IOException, InterruptedException
	{
		log.info("@@@ TC001_Women_Blouses_Shopping pre set up started @@@");
		props=new Properties();
		fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dipak\\mystore\\config\\AppProperties.properties");
		props.load(fis);

	}
	@Test
	public void shopping() throws IOException, InterruptedException
	{
		init("ie", "http://automationpractice.com/"); 
		log.info("*** TC001_Women_Blouses_Shopping started with IE browsr opening ***");
		new IndexPage(driver).clickSignin();
		log.info("sign in clicked fromindex page");
		new LoginPage(driver).clickSignin(props.getProperty("LoginID"), props.getProperty("password"));
		log.info("sign in clicked with email and pasw");
		Thread.sleep(3000);
		new HomePage(driver).clickDress("Women", "blouses");	
		bpagobj=new ShoppingPage_Blouse(driver);
		bpagobj.fillBlousPage();
	}

	@AfterClass
	public void closeTetcase() throws IOException
	{
		log.info("@@@ TC001_Women_Blouses_Shopping test case closed @@@");
		props=null;
		fis=null;
		driver.quit();
	}
}

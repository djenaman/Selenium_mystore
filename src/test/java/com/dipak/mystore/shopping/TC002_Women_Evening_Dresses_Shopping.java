package com.dipak.mystore.shopping;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.dipak.mystore.pageActions.HomePage;
import com.dipak.mystore.pageActions.IndexPage;
import com.dipak.mystore.pageActions.LoginPage;
import com.dipak.mystore.testBase.Baseclass;

@Listeners(com.dipak.mystore.customListener.Clistner.class)
public class TC002_Women_Evening_Dresses_Shopping extends Baseclass {
	Properties props=null;
	FileInputStream fis=null;
	
@BeforeClass
public void initTetcase() throws IOException
{
	log.info("@@@ TC002_Women_Evening Dresses_Shopping pre set up started @@@");
	props=new Properties();
	fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dipak\\mystore\\config\\AppProperties.properties");
	props.load(fis);
}
  
  @Test
  public void shopping()
  {
	  init("ie", "http://automationpractice.com/"); 
	  log.info("*** TC002_Women_Evening Dresses_Shopping started with IE browsr opening ***");
	  new IndexPage(driver).clickSignin();
	  log.info("sign in clicked fromindex page");
	  new LoginPage(driver).clickSignin(props.getProperty("LoginID"), props.getProperty("password"));
	  log.info("sign in clicked with email and pasw");
	  //Assert.assertTrue(false);
	  new HomePage(driver).clickDress("Women", "Evening Dress");	  
  }
  
  @AfterClass
  public void closeTetcase() throws IOException
  {
  	log.info("@@@ TC002_Women_Evening Dresses_Shopping test case closed @@@");
  	props=null;
  	fis=null;
  	//driver.close();
  }
}

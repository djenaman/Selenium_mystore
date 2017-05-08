package com.dipak.mystore.shopping;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dipak.mystore.pageActions.HomePage;
import com.dipak.mystore.pageActions.IndexPage;
import com.dipak.mystore.pageActions.LoginPage;
import com.dipak.mystore.testBase.Baseclass;

public class TC004_T_Shirts_Shopping extends Baseclass {
	Properties props=null;
	FileInputStream fis=null;
	
@BeforeClass
public void initTetcase() throws IOException
{
	log.info("@@@ TC004_T_Shirt_Shopping pre set up started @@@");
	props=new Properties();
	fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dipak\\mystore\\config\\AppProperties.properties");
	props.load(fis);
}
  @Test
  public void shopping()
  {
	  init("ie", "http://automationpractice.com/"); 
	  log.info("*** TC004_T_Shirt_Shopping started with IE browsr opening ***");
	  new IndexPage(driver).clickSignin();
	  log.info("sign in clicked fromindex page");
	  new LoginPage(driver).clickSignin(props.getProperty("LoginID"), props.getProperty("password"));
	  log.info("sign in clicked with email and pasw");
	  new HomePage(driver).clickDress("T_Shirts", "");	  
  }
  
  @AfterClass
  public void closeTetcase() throws IOException
  {
  	log.info("@@@ TC004_T_Shirt_Shopping test case closed @@@");
  	props=null;
  	fis=null;
  	//driver.close();
  }
}

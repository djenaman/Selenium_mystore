package com.dipak.mystore.registration;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

import com.dipak.mystore.excelReader.GetExcelData;
import com.dipak.mystore.pageActions.IndexPage;
import com.dipak.mystore.pageActions.LoginPage;
import com.dipak.mystore.pageActions.RegistrationPage;
import com.dipak.mystore.testBase.Baseclass;

public class TC001_Registration extends Baseclass
{
	@Test
	public void registration() throws IOException, Exception
	{
		Map<String, String> RegistrationData=null;
		log.info("**** Test case 001 for Registration started ****");
		init("chrome", "Http://Automationpractice.Com/");
		log.info("Index page opened");
		new IndexPage(driver).clickSignin();
		log.info("Signin button clicked");
		RegistrationData=new GetExcelData().getActiveRegistrationData();
		log.info("excel sheet data recieved for registration");
		if(RegistrationData!= null)
		{
			log.info("unused record found for registration...");
			new LoginPage(driver).clickCreateAccount(RegistrationData.get("EmailID").toString());
			new RegistrationPage(driver).registerUser(RegistrationData);

		}
		else
		{
			log.info("No active data row found in test data sheet..all are used.");
			//need to update status column
		}
		log.info("**** Test case 001 for Registration end ****");
	}

}


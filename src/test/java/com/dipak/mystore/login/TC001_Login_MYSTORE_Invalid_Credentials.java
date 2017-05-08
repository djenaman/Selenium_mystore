package com.dipak.mystore.login;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.dipak.mystore.pageActions.IndexPage;
import com.dipak.mystore.pageActions.LoginPage;
import com.dipak.mystore.testBase.Baseclass;

public class TC001_Login_MYSTORE_Invalid_Credentials extends Baseclass
{
	@Test
	public void logininvalid()
	{
		log.info("**** Test case001 for login with invalid credential started ****");
		init("firefox", "Http://Automationpractice.Com/");
		IndexPage ip=new IndexPage(driver);
		ip.clickSignin();
		log.info("clicked signin buttonon index page");
		LoginPage login=new LoginPage(driver);
		login.clickSignin("abcd@gmail.com", "password123");
		log.info("Login buttonclicked with invalid credentials ");
		Assert.assertEquals(login.loginStatus(), false);
		log.info("**** Test case001 for login with invalid credential end ****");

	}
	@AfterClass
	public void closecase()
	{
		driver.quit();
	}
}

package com.dipak.mystore.login;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dipak.mystore.excelReader.GetExcelData;
import com.dipak.mystore.pageActions.IndexPage;
import com.dipak.mystore.pageActions.LoginPage;
import com.dipak.mystore.testBase.Baseclass;

public class TC002_Login_MYSTORE_Valid_Credentials extends Baseclass
{
	private int callcount=0;
	private LoginPage login=null;
  @Test(dataProvider="logindata")
  public void loginvalid(String email,String Password)
  {
	  if(callcount==0)
	  {
	    init("firefox", "Http://Automationpractice.Com/");
		new IndexPage(driver).clickSignin();
		login=new LoginPage(driver);
		//PageFactory.initElements(driver, login);
	  }
		login.clickSignin(email,Password);		
		Assert.assertEquals(login.loginStatus(), true);
		captureScreenShot("homepage");
		login.clickSignout();
		callcount++;
		//captureScreenShot("signoutpage");
  }
  @AfterMethod
  public void closecase()
  {
		//driver.close();
  }
  @DataProvider(name="logindata",parallel=false)
  public Object[][] logindetails() throws IOException
  {
	  Object[][] obj=null;
	  GetExcelData data=new GetExcelData();
	  Map<String,String> mobj=data.getLoginData("login");
	  obj=new Object[mobj.keySet().size()][2];
	  Iterator<String> it=mobj.keySet().iterator();
	  for(int i=0;i<mobj.keySet().size();i++)
	  {
		  	  obj[i][0]=it.next();
	  		  obj[i][1]=mobj.get(obj[i][0].toString());
	  }
	  return obj;
	  
  }
}

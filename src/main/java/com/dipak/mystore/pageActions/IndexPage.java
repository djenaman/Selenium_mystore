package com.dipak.mystore.pageActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.dipak.mystore.testBase.Baseclass;

public class IndexPage
{
	//WebDriver dvr=null;
	public IndexPage(WebDriver driver)
	{
		//dvr=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className="login")
	WebElement signin;
	
	public void clickSignin()
	{
		//dvr.findElement(By.className("login")).click();
		signin.click();
		
	}
}

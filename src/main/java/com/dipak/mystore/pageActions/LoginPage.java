package com.dipak.mystore.pageActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.dipak.mystore.testBase.Baseclass;

public class LoginPage extends Baseclass{
	
public LoginPage(WebDriver driver) 
{
	PageFactory.initElements(driver, this);
	this.driver=driver;
}
@FindBy(id="email_create")
WebElement Email_Register;

@FindBy(id="email")
WebElement Email_Login;

@FindBy(id="passwd")
WebElement password;

@FindBy(id="SubmitCreate")
WebElement crtaccnt_btn;

@FindBy(id="SubmitLogin")
WebElement signin_btn;

@FindBy(className="logout")
WebElement logout_btn;

public void clickCreateAccount(String emailid)
{
	Email_Register.sendKeys(emailid);
	//crtaccnt_btn.click();
	Email_Register.submit();
}

public void clickSignin(String emailid,String password)
{
	Email_Login.sendKeys(emailid);
	this.password.sendKeys(password);
	
	captureScreenShot("indexpage");
	signin_btn.click();
}

public void clickSignout()
{
	logout_btn.click();
}

public boolean loginStatus()
{
	try {
		logout_btn.isDisplayed();
		return true;
	} catch (Exception e) {
		return false;
	}
}
}

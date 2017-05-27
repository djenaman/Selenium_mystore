package com.dipak.mystore.pageActions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.dipak.mystore.excelReader.GetExcelData;
import com.dipak.mystore.excelReader.PutExcelData;
import com.dipak.mystore.testBase.Baseclass;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class RegistrationPage extends Baseclass {
	public RegistrationPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this );
		Putdata=null;
		Getdata=null;
	}

	PutExcelData Putdata;
	GetExcelData Getdata;
	@FindBy(id="id_gender1")
	WebElement title_mr;

	@FindBy(id="id_gender2")
	WebElement title_mrs;

	@FindBy(id="customer_firstname")
	WebElement firstName;

	@FindBy(id="customer_lastname")
	WebElement lastName;
	
	@FindBy(id="passwd")
	WebElement passkey;

	@FindBy(id="days")
	WebElement Date_day;

	@FindBy(id="months")
	WebElement Date_month;

	@FindBy(id="years")
	WebElement Date_year;

	@FindBy(id="firstname")
	WebElement Address_firstname;

	@FindBy(id="lastname")
	WebElement Address_lastname;

	@FindBy(id="address1")
	WebElement Address_address1;

	@FindBy(id="address2")
	WebElement Address_address2;

	@FindBy(id="city")
	WebElement Address_city;

	@FindBy(id="id_state")
	WebElement Address_state;

	@FindBy(id="postcode")
	WebElement Address_postcode;

	@FindBy(id="id_country")
	WebElement Address_country;

	@FindBy(id="other")
	WebElement AdditionalInfo;

	@FindBy(id="phone")
	WebElement Home_Phone;

	@FindBy(id="phone_mobile")
	WebElement Mobile_Phone;

	@FindBy(id="alias")
	WebElement Address_alias;

	@FindBy(id="submitAccount")
	WebElement Register_btn;

	@FindBy(xpath="//*[@class='account']/span")
	WebElement Homepage_username;

	
	public void registerUser(Map<String,String> user) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		String[] dates=null;
		Select dayDD,monthDD,yearDD,stateDD,countryDD;
		if(user.get("Title").toString().toLowerCase().equals("mr."))
			title_mr.click();
		else
			title_mrs.click();
		firstName.sendKeys(user.get("First name"));
		lastName.sendKeys(user.get("Last name"));
		dates=user.get("DOB").toString().split("/");
		dayDD=new Select(Date_day);
		monthDD=new Select(Date_month);
		yearDD=new Select(Date_year);
		dayDD.selectByValue(dates[0]);
		monthDD.selectByValue(dates[1]);
		yearDD.selectByValue(dates[2]);
		Address_firstname.sendKeys(user.get("First name"));
		Address_lastname.sendKeys(user.get("Last name"));
		passkey.sendKeys(user.get("Password"));
		Address_address1.sendKeys(user.get("Address1"));
		Address_address2.sendKeys(user.get("Address2"));
		Address_city.sendKeys(user.get("city"));
		stateDD=new Select(Address_state);
		stateDD.selectByVisibleText(user.get("State"));
		Address_postcode.sendKeys(user.get("Zip"));
		countryDD=new Select(Address_country);
		countryDD.selectByVisibleText(user.get("Country"));
		AdditionalInfo.sendKeys(user.get("AddInfo"));
		Home_Phone.sendKeys("2323234321");
		Mobile_Phone.sendKeys(user.get("Mobile"));
		Address_alias.sendKeys(user.get("Address Alias"));

		Register_btn.click();
		try{
			String sourcestring = Homepage_username.getText().toLowerCase().trim();
			String deststring= user.get("First name").toString().toLowerCase().trim();
			if(sourcestring.contains(deststring))
			{
				log.info("register successfull...");
				Getdata=new GetExcelData();
				int rnum=Getdata.getRowNumber("registration", user.get("EmailID"));
				int cnum=Getdata.getColumnNumber("registration", "Status");
				Getdata=null;
				Putdata=new PutExcelData();
				Putdata.putCellData("registration", rnum, cnum, "Used");
				log.info("Data sheet successfully updted with record status...");
				HashMap<String,String> data=new HashMap<String,String>();
				data.put(user.get("EmailID"),user.get("Password"));
				Putdata.putLoginData("login", data);
				log.info("Login dtails savedto login data sheet");
				Putdata=null;
			}
		}
		catch(ElementNotFoundException e)
		{
			log.info("Could not create account...Some error exists");
		}
	}
}

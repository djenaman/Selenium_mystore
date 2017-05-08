package com.dipak.mystore.pageActions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dipak.mystore.excelReader.GetExcelData;

public class ShoppingPage_Blouse
{
	public Actions actionbuilder;
	GetExcelData excldata=null;
	WebDriver dvr=null;
	public ShoppingPage_Blouse(WebDriver dvr) throws IOException
	{
		PageFactory.initElements(dvr, this);
		this.dvr=dvr;
		actionbuilder=new Actions(dvr);
		excldata=new GetExcelData();
	}

	@FindBy(id="layered_id_attribute_group_1")
	WebElement size_small;

	@FindBy(id="layered_id_attribute_group_2")
	WebElement size_medium;
	
	@FindBy(id="layered_id_attribute_group_3")
	WebElement size_large;
	
	@FindBy(id="layered_id_attribute_group_8")
	WebElement color_white;
	
	@FindBy(id="layered_id_attribute_group_11")
	WebElement color_black;
	
	@FindBy(id="layered_id_feature_5")
	WebElement compositions;
	
	@FindBy(id="layered_id_feature_11")
	WebElement style;
	
	@FindBy(id="layered_id_feature_17")
	WebElement property;
	
	@FindBy(id="layered_manufacturer_1")
	WebElement manufacture;
	
	@FindBy(id="layered_condition_new")
	WebElement newItem;
	
	@FindBy(css=".ui-slider-handle.ui-state-default.ui-corner-all")
	WebElement range_slider; 
	
	@FindBy(css=".available-now")
	WebElement stock_Available;	
	
	@FindBy(css=".button.ajax_add_to_cart_button.btn.btn-default>span")
	WebElement addToCart;
	
	/*@FindBy(css=".btn.btn-default.button.button-medium>span")
	WebElement proceedToChckout1;*/
	
	@FindBy(xpath="//*[contains(@id,'cart_quantity_up']/span/i")
	WebElement increaseQuant;
	
	@FindBy(xpath="//p[@class='cart_navigation clearfix']//*[contains(text(),'Proceed to')]")
	WebElement proceedToChckout2;
	
	@FindBy(partialLinkText="Proceed to")
	WebElement proceedToChckout;	
	              
	@FindBy(xpath=".//*[@id='center_column']/form/p/button")
	WebElement Addres_proceedToChckoutButton;	
	
	@FindBy(xpath=".//*[@id='form']/p/button")
	WebElement ship_proceedToChckoutButton;
	
	
	@FindBy(css="#cgv")
	WebElement terms_Service;
	
	@FindBy(css=".bankwire")
	WebElement PaymentMethod_Bankwire;
	
	@FindBy(xpath=".//*[@id='cart_navigation']/button")
	WebElement confirm_order;
	
	
	public void fillBlousPage() throws FileNotFoundException, IOException, InterruptedException
	{
		Map<String,String> data= excldata.getBlouseShoppingData("Shop_Blouse");
		if(data.get("Size").equalsIgnoreCase("S"))
				size_small.click();
		else if(data.get("Size").equalsIgnoreCase("L"))
				size_large.click();
		else
				size_medium.click();
		
		if(data.get("Color").equalsIgnoreCase("white"))
			color_white.click();
		else
			color_black.click();
		if(data.get("Compositions").equalsIgnoreCase("cotton"))
			compositions.click();		
		if(data.get("Styles").equalsIgnoreCase("Casual"))
			style.click();
		if(data.get("Properties").toLowerCase().contains("short sleeve"))
			property.click();
		if(data.get("Manufacturer").toLowerCase().contains("fashion"))
			manufacture.click();
		if(data.get("Condition").equalsIgnoreCase("new"))
			newItem.click();
		
		actionbuilder.clickAndHold(range_slider).moveByOffset(35, 0).release().build().perform();
		if(stock_Available!=null)
		{
			actionbuilder.moveToElement(stock_Available).click(addToCart).perform();
		}
		
		new WebDriverWait(dvr,20).until(ExpectedConditions.visibilityOf(proceedToChckout));
		proceedToChckout.click();
		Thread.sleep(2000);
       // actionbuilder.moveToElement(increaseQuant).click().click().click().perform();
		new WebDriverWait(dvr,20).until(ExpectedConditions.visibilityOf(proceedToChckout));
		proceedToChckout.click();
		Thread.sleep(2000);
		//new WebDriverWait(dvr,20).until(ExpectedConditions.visibilityOf(proceedToChckoutButton));
		proceedToChckout2.click();
		Thread.sleep(2000);
		terms_Service.click();
		//new WebDriverWait(dvr,20).until(ExpectedConditions.visibilityOf(proceedToChckoutButton));
		proceedToChckout2.click();
		Thread.sleep(2000);
		PaymentMethod_Bankwire.click();
		Thread.sleep(2000);
		confirm_order.click();
		
		Properties props=new Properties();
		props.load(new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/dipak/mystore/config/AppProperties.properties"));
		props.setProperty("BlouseSheetRowPointer", (Integer.parseInt(props.getProperty("BlouseSheetRowPointer"))+1)+"");
		props.store(new FileOutputStream(System.getProperty("user.dir")+"/src/main/java/com/dipak/mystore/config/AppProperties.properties"), "value added");
	}
	


	}

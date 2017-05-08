package com.dipak.mystore.pageActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage
{
	public Actions actionbuilder;
	public HomePage(WebDriver dvr)
	{
		PageFactory.initElements(dvr, this);
		actionbuilder=new Actions(dvr);
	}

	@FindBy(xpath=".//*[@id='block_top_menu']/ul/li[1]/a")
	WebElement women;

	@FindBy(xpath=".//*[@id='block_top_menu']/ul/li[2]/a")
	WebElement dresses;

	@FindBy(xpath=".//*[@id='block_top_menu']/ul/li[3]/a")
	WebElement tshirt;


	@FindBy(xpath=".//*[@id='block_top_menu']/ul/li[1]/ul/li[1]/ul/li[1]/a")
	WebElement Women_tshirt;

	@FindBy(linkText="Blouses")
	WebElement Women_blouse;

	@FindBy(linkText="Casual Dresses")
	WebElement Women_CasualDress;

	@FindBy(linkText="Evening Dresses")
	WebElement Women_EveningDress;

	@FindBy(linkText="Summer Dresses")
	WebElement Women_SummerDress;

	@FindBy(xpath=".//*[@id='block_top_menu']/ul/li[2]/ul/li[1]/a")
	WebElement Dresses_casualdress;

	@FindBy(xpath=".//*[@id='block_top_menu']/ul/li[2]/ul/li[2]/a")
	WebElement Dresses_eveningdress;

	@FindBy(xpath=".//*[@id='block_top_menu']/ul/li[2]/ul/li[3]/a")
	WebElement Dresses_summerdress;

	public void clickDress(String category,String Sub_category)
	{
		WebElement subcat=null;
		String subcatTEXT=Sub_category.toLowerCase();
		String catTEXT=category.toLowerCase();
		if(catTEXT.contains("women"))
		{
			if(subcatTEXT.contains("tshirts"))
				subcat=Women_tshirt;
			else if(subcatTEXT.contains("evening dress"))
				subcat=Women_EveningDress;
			else if(subcatTEXT.contains("casual dress"))
				subcat=Women_CasualDress;
			else if(subcatTEXT.contains("summer dress"))
				subcat=Women_SummerDress;
			else
				subcat=Women_blouse;
			actionbuilder.moveToElement(women).moveToElement(subcat).click().perform();
		}
		else if(catTEXT.contains("dress"))
		{
			if(subcatTEXT.contains("evening dress"))
				subcat=Dresses_eveningdress;
			else if(subcatTEXT.contains("casual dress"))
				subcat=Dresses_casualdress;
			else
				subcat=Dresses_summerdress;
			actionbuilder.moveToElement(dresses).moveToElement(subcat).click().perform();

		}
		else
		{
			tshirt.click();
		}
	}
}

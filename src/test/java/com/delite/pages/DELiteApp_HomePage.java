package com.delite.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DELiteApp_HomePage {

	WebDriver driver;						//Declaring web driver object
	public DELiteApp_HomePage(WebDriver driver)		//Constructor 
	{
		this.driver=driver;							//Storing object reference passed to object declared in this class
	}
	
	//All Locators
	
	By menuBarIcon = By.xpath("//a[@class='menuIconWrapper']/span");		//Locator for menu bar icons in Home Page
	By listWrapMenu =  By.xpath("//ul[@class='listWrap ng-scope']/li/a");	//Locator for wrapper menu displaying on mouse click/mouse hover on menu icon
    
	public void selectOptionFromMenuBar(String menuIcon,String subMenuIcon) //Function to select option from menu 
	{
		System.out.println("***************STARTING SELECT OPTION FROM MENU FUNCTION********************");
		menuIcon=menuIcon.replaceAll("\\s", "");	//Remove all white spaces in input string
		List<WebElement> menuBarIconList = driver.findElements(menuBarIcon); //Store all the menu bar icon locators to a list
		for(int i=0;i<menuBarIconList.size();i++)
		{
			if(menuBarIconList.get(i).getAttribute("class").split("\\s")[0].equalsIgnoreCase(menuIcon)) //Check for the menu bar icon need to be clicked .Comparing with input data
			{
				menuBarIconList.get(i).click();										//Click on menu bar icon.
				break;
			}
		}
		List<WebElement> listWrapMenuList = driver.findElements(listWrapMenu); //Store all the wrap list icon Locators to a list
		for(int i=0;i<listWrapMenuList.size();i++)
		{
			if(listWrapMenuList.get(i).getText().equalsIgnoreCase(subMenuIcon))	//Check for the icon need to be clicked from wrapper menu .Comparing with input data
			{
				listWrapMenuList.get(i).click();								//Click on specific icon.
				break;
			}
		}
	}
}

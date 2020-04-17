package com.delite.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DELiteApp_AuditorDashboard {			//Class for Auditor dash board

WebDriver driver;									//Global variable
	
	public DELiteApp_AuditorDashboard(WebDriver driver) //constructor
	{
		this.driver=driver;							//Storing object driver to the driver object in this class
	}
	
//	********************************ALL LOCATORS********************************
	By dashBoardButtonLocator = By.xpath("//a[@class='nav-link ng-binding']");			//Locator for button in dash board
	By dashBoardAuditIdLocator = By.xpath("//span[@ng-click='CallAuditPlanDetailsPage(listdata.PlanID,PageID)']"); //Locator for Audit ID
	public void clickButtonInDashboard(String clickOption)					//Function to click button in dash board
	{
		List<WebElement> dashBoardButtonLocatorList = driver.findElements(dashBoardButtonLocator);	//Store web elements for dashboard buttons to list
		for(int i=0;i<dashBoardButtonLocatorList.size();i++)
		{
			System.out.println(dashBoardButtonLocatorList.get(i).getText());
			if(dashBoardButtonLocatorList.get(i).getText().contains(clickOption))		//Check for preferred dash board button
			{
				dashBoardButtonLocatorList.get(i).click();						//Click on the dash board button
				break;
			}
		}
	}
	
	public boolean checkForAuditIdInDashBoard(String auditID)					//Function to check  Audit ID is present in dashboard
	{
		List<WebElement> dashBoardAuditIdLocatorList = driver.findElements(dashBoardAuditIdLocator);  //List of all Audit id in dash board
		for(int i=0;i<dashBoardAuditIdLocatorList.size();i++)
		{
			System.out.println(dashBoardAuditIdLocatorList.get(i).getText());
			if(dashBoardAuditIdLocatorList.get(i).getText().equals(auditID))						//Check for presence of auditID
			{
				return true;
			}
		}
		return false;
	}
}

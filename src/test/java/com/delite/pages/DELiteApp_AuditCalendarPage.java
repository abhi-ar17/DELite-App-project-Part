package com.delite.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DELiteApp_AuditCalendarPage {							//Class for Audit Calendar Page

	WebDriver driver;	//Global variable driver
	List<WebElement> auditDetailsLocatorList;
	public DELiteApp_AuditCalendarPage(WebDriver driver)			//Constructor
	{
		this.driver=driver;											//Storing object driver to the driver object in this class
	}
	List<WebElement> dayWithInprogressAudit=new ArrayList<WebElement>();	//Declare list to store web element for days with in progress audit
	List<WebElement> inProgressAuditDetails=new ArrayList<WebElement>();	//Declare list to store web element for data contained in progress audit 
	
//	********************************************ALL LOCATORS*********************************
	
	By dayWithDataContained = By.xpath("//td[@class='fc-event-container']/a");							//Locator for days with data contained in it
	By auditDetailsLocator = By.xpath("//td[@class='fc-event-container']/a//span[@class='fc-title']");	//Locator for audit details
	By dataContainedLocator = By.xpath("//div[@class='fc-content']");									//Locator for data contained 
	
	public void getInprogressAuditList()				//Function to get audit list in in progress stage
	{
		
		List<WebElement> dayWithDataContainedList = driver.findElements(dayWithDataContained);	//List to store web elements for days containing data
		auditDetailsLocatorList = driver.findElements(auditDetailsLocator);	//List to store web elements for data 
		for(int i=0;i<dayWithDataContainedList.size();i++)
		{
			//Check for that day with data contained in it is in progress stage
			if(dayWithDataContainedList.get(i).getAttribute("class").contains("plannedYet")||dayWithDataContainedList.get(i).getAttribute("class").contains("plannedProgress"))	
			{
				dayWithInprogressAudit.add(dayWithDataContainedList.get(i));  //add web element to list dayWithInprogressAudit
				inProgressAuditDetails.add(auditDetailsLocatorList.get(i));	  //add web element to list inProgressAuditDetails
			}
		}
	}
	
	public void clickOnAuditWithPlanId(String planId)					//Function to click audit in audit calendar with Audit ID:planId
	{
		auditDetailsLocatorList = driver.findElements(auditDetailsLocator);
		for(int i=0;i<auditDetailsLocatorList.size();i++)
		{
			if(auditDetailsLocatorList.get(i).getText().contains(planId))
			{
				auditDetailsLocatorList.get(i).click();
				break;
			}
		}
	}
	
	
	public boolean checkAvailabilityOfResumeAudit(String planId)			//Function to check availability of resume audit button
	{
		DELiteApp_AuditDetailsPage auditDetailsPage=new DELiteApp_AuditDetailsPage(driver); //Create object  instance for DELiteApp_AuditDetailsPage
		boolean result=auditDetailsPage.checkAvaiabilityOfResumeAuditButton();						//call function in DELiteApp_AuditDetailsPage
		return result;
	}
}

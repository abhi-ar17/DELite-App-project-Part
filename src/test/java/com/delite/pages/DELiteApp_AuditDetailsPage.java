package com.delite.pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.delite.utils.SwitchWindow;


public class DELiteApp_AuditDetailsPage {				//Class for DELiteApp_AuditDetailsPage
	
	WebDriver driver;									//Global variable
	
	public DELiteApp_AuditDetailsPage(WebDriver driver)	//Constructor
	{
		this.driver=driver;								//Storing object driver to the driver object in this class
	}
	
//	*******************************ALL LOCATORS*************************
	By resumeAuditButton = By.xpath("//div[@class='btmCont col-md-12 noPadding']//div");   	//Locator for resume audit button
	By labelHeadingLocator= By.xpath("//div[@class='col-xs-4 labelSection noPadding']/div[1]");	//Locator for Label heading 
	By labelTextLocator = By.xpath("//div[@class='col-xs-4 labelSection noPadding']//div[@class='labelText ng-binding' or @class='labelText lastCol ng-binding' or @class='labelText ng-binding ng-scope']");	//Locator for Label text
	
	public HashMap<String, String> getDataFromAuditDetailsPage()		//Function to get data from Audit Details page
	{
		SwitchWindow.switchWindowControl(driver, 1);					//Switch control between tabs
		WebDriverWait wait = new WebDriverWait(driver, 6);				//Initializing object to perform Explicit wait
		List<WebElement> labelHeadingLocatorList = driver.findElements(labelHeadingLocator);	//store web elements for label heading to list
		wait.until(ExpectedConditions.numberOfElementsToBe(labelTextLocator, labelHeadingLocatorList.size())); //wait until label text contains same number of data as label head
		List<WebElement> labelTextLocatorList = driver.findElements(labelTextLocator);						   //Store web elements for label text to list
		HashMap<String, String> dataFromAuditDetails = new HashMap<String,String>();						   //Declare hash map to store data 
		for(int i=0;i<labelHeadingLocatorList.size();i++)
		{
		dataFromAuditDetails.put(labelHeadingLocatorList.get(i).getText(), labelTextLocatorList.get(i).getText()); //Store data to hash map
		}
//		System.out.println("Details"+dataFromAuditDetails);
		return dataFromAuditDetails;
	}
	
	public boolean checkAvaiabilityOfResumeAuditButton()			//Function to check availability of resume audit button
	{
		SwitchWindow.switchWindowControl(driver, 1);				//Switch control between tabs
		WebDriverWait wait = new WebDriverWait(driver, 5);			//Initializing object to perform Explicit wait
		try
		{
			wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(resumeAuditButton),"Resume Audit"));	//Wait until resume audit text is present in div tag
			driver.findElement(resumeAuditButton).click();					//Click resume audit button
			
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void clickResumeAudit()         		//Function to click resume audit button
	{
		SwitchWindow.switchWindowControl(driver, 1);			//Switch control between tabs
		WebDriverWait wait = new WebDriverWait(driver, 5);		//Initializing object to perform Explicit wait
		wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(resumeAuditButton),"Resume Audit"));	//Wait until resume audit text is present in div tag
		driver.findElement(resumeAuditButton).click();			//click button
	}
	
}

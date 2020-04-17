package com.delite.pages;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DELiteApp_AssessmentContinuePage {
	
	WebDriver driver;											//Global variable driver
	public DELiteApp_AssessmentContinuePage(WebDriver driver)	//Constructor 
	{
		this.driver=driver;										//Storing object driver to the driver object in this class
	}
	
//	******************************************ALL LOCATORS****************************************
	
	By rightBottomCornerButtonLocator = By.xpath("//*[@class='btn btn-primary pull-right buttonCont primaryBtn ng-binding'  or @class='btn btn-primary pull-right buttonCont primaryBtn ng-scope' or @class='btn btn-primary pull-right buttonCont primaryBtn'  ]");  //Locator for the button at right bottom corner
	By projectNameLocator = By.xpath("//a[@href=\"/apps/DELite/Assessment/ProjectInformation\"]");																									   //Locator for the Project name in assessment page
	By planIdLocator = By.xpath("//a[@class=\"labelValue ng-binding\"]");																															   //Locator for the Plan Id/Audit Id in assessment page
	By stageNavigationButtonOnTop = By.xpath("//li[@class='ng-scope current' or @class='ng-scope current final']//span[2]");																		   //Locator for the Navigation button at the top
	By yesButtonConfirmationLocator=By.xpath("//button[@class='btn-default pull-right btnBlue']");																   //Locator for the yes button in confirmation box
	By yesButtonConfirmationCategoryLocator=By.id("confirmationModalYes");
	By yesButtonConfirmationFRSLocator=By.xpath("//div[@id=\"FindingConfirmationModal\"]//button[@class=\"btn-default pull-right btnBlue\"]");																					   //Locator for the yes button in confirmation box for fact recording sheet
	
	public boolean checkButtonAtRightCorner()       		//Function to check button at right bottom corner
	{
		List <WebElement> rightBottomCornerButtonLocatorList = driver.findElements(rightBottomCornerButtonLocator);	//List for button at right bottom corner
		for(int i=0;i<rightBottomCornerButtonLocatorList.size();i++)
		{
			System.out.println(rightBottomCornerButtonLocatorList.get(i).getText());
			//Check whether the button at right bottom corner is referring to next stage instead of next button
			if(rightBottomCornerButtonLocatorList.get(i).getText().equals("Validate and Continue")||rightBottomCornerButtonLocatorList.get(i).getText().equals("Start Assessment")||rightBottomCornerButtonLocatorList.get(i).getText().equals("Submit")||rightBottomCornerButtonLocatorList.get(i).getText().equals("Fact Recording Sheet")||rightBottomCornerButtonLocatorList.get(i).getText().equals("Confirm Categorization & Continue")||rightBottomCornerButtonLocatorList.get(i).getText().equals("Send & Complete"))
			{
			 return true;
			}
		}
		return false;
	}
	
	public HashMap<String, String> getDataFromAssessmentPage() 											//function to get audit details from Assessment page
	{
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);									//wait for the page to be loaded
		WebDriverWait wait = new WebDriverWait(driver, 10);												//Initializing object to perform Explicit wait
		HashMap<String, String> dataFromAssessmentPage = new HashMap<String, String>(); 				// Hash map to store audit details
		wait.until(ExpectedConditions.textMatches(projectNameLocator,Pattern.compile("[a-zA-Z0-9]+"))); //Wait until project name is displayed 
		dataFromAssessmentPage.put("Project Name", driver.findElement(projectNameLocator).getText());	//Store Project name to the hash map
		dataFromAssessmentPage.put("Audit ID",driver.findElement(planIdLocator).getText());				//Store Audit ID to the hash map
		return dataFromAssessmentPage;																	//Return hash map containg audit details
	}
	
	public void clickNextStageButton(String activeStage)			//function to click button to next stage
	{
	 WebDriverWait wait = new WebDriverWait(driver, 6);				//Initializing object to perform Explicit wait
	 if(activeStage.equals("Assessment"))							//Check whether active stage is assessment stage 
	 {
		 driver.findElement(rightBottomCornerButtonLocator).click();	//Click on the button to next stage
		 try {
			 
		 wait.until(ExpectedConditions.elementToBeClickable(yesButtonConfirmationLocator));		  //wait until yes button in confirmation is clickable
		 driver.findElement(yesButtonConfirmationLocator).click();								  //Click yes button in confirmation box
		 wait.until(ExpectedConditions.elementToBeClickable(rightBottomCornerButtonLocator));	  //wait until next stage button is clickable
		 driver.findElement(rightBottomCornerButtonLocator).click();						 	  //Click button to next stage
		 wait.until(ExpectedConditions.elementToBeClickable(yesButtonConfirmationFRSLocator));	  //wait until yes button in confirmation is clickable
		 driver.findElement(yesButtonConfirmationFRSLocator);	 								  //Click yes button in confirmation box
		 }
		 catch (Exception e)
		 {
			 
		 }
	 }
	 else
	 {
	 driver.findElement(rightBottomCornerButtonLocator).click();
	 try
	 {
		 wait.until(ExpectedConditions.elementToBeClickable(yesButtonConfirmationCategoryLocator));		//wait until yes button in confirmation is clickable
		 driver.findElement(yesButtonConfirmationCategoryLocator).click();
		 wait.until(ExpectedConditions.elementToBeClickable(yesButtonConfirmationLocator));		//wait until yes button in confirmation is clickable
		 driver.findElement(yesButtonConfirmationLocator).click();								//Click yes button in confirmation box
		 wait.until(ExpectedConditions.elementToBeClickable(rightBottomCornerButtonLocator));   //wait until next stage button is clickable
		 driver.findElement(rightBottomCornerButtonLocator).click();							//Click button to next stage
	 }
	 catch (Exception e)
	 {
		 
	 }
	}
	}
	
	public String findActiveStage()																//Function to find the active stage  in assessment page
	{
		String activeStage= driver.findElement(stageNavigationButtonOnTop).getText();			//Get text from navigation button and store it to a string
		return activeStage;																		//Return string activeStage
	}
	
	
}

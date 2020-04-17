package com.delite.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DELite_LoginPage {								//Class for Login Page
	WebDriver driver;										//Declaring Web Driver for this class
	public DELite_LoginPage(WebDriver driver)				//Constructor with parameter web driver 
	{
		this.driver=driver;									//Referenced object is been stored to driver in this class
	}
    
	
//	*****************************ALL LOCATORS*********************************
	
	By userName=By.id("login");					//Locator for the user name text box
	By passWord=By.id("passwd");				//Locator for the password text box
	By logOnButton=By.id("Log_On");				//Locator for the LogOn button
	
	public void logIn(String user,String pass)	//Function to login to Delite App
	{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); //Manage driver to wait until the page is been loaded
		driver.findElement(userName).sendKeys(user);					 //Send user name to the userName text box
		driver.findElement(passWord).sendKeys(pass);					 //Send password to passWord text box
		driver.findElement(logOnButton).click();						 //Click the Log On button
	}



}

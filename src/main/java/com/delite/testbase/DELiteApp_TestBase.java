package com.delite.testbase;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DELiteApp_TestBase {
	
public static WebDriver driver;
	
	
	public static void launchBrowser()
	{
Scanner s = new Scanner(System.in);
		
		System.out.println("Enter Browser to Launch :\n1.Chrome\n2.FireFox\n3.Internet Explorer\nEnter Your Choice : ");
		int choice = s.nextInt();
				
		switch(choice)
		{
		   case 1 :  //Launch Chrome Browser
			   		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
			   		driver = new ChromeDriver();
		   			 break;
		   			 
		   case 2 :  //Launch FireFox Browser
			   		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
			   		driver = new FirefoxDriver();
		   			 break;
		   			 
		   default : //Launch IE Browser 
			   		System.setProperty("webdriver.ie.driver", "src/test/resources/drivers/IEDriverServer.exe");
			   		driver = new FirefoxDriver();
		}
		s.close();
		
	driver.get("https://deliveryexcellencest.cognizant.com/apps/DElite");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

}

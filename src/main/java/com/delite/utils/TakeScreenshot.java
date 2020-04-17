package com.delite.utils;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenshot {

	WebDriver driver;
	public TakeScreenshot(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void pickScreenshot(String testCase,String status,String testDataNum)
	{
		File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		screenShot.renameTo(new File("src/test/resources/Screenshot/TestData"+testDataNum+testCase+status+ System.currentTimeMillis() + ".png"));
		
	}

}

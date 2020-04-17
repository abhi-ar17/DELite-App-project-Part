package com.delite.utils;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

public class SwitchWindow {
	WebDriver driver;
	public static void switchWindowControl(WebDriver driver,int windowNum) 
	{
		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(availableWindows.get(windowNum));
	}

}

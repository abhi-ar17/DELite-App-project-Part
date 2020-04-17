package com.delite.testcases;


import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.delite.pages.DELiteApp_AssessmentContinuePage;
import com.delite.pages.DELiteApp_AuditCalendarPage;
import com.delite.pages.DELiteApp_AuditDetailsPage;
import com.delite.pages.DELiteApp_AuditorDashboard;
import com.delite.pages.DELiteApp_HomePage;
import com.delite.pages.DELite_LoginPage;
import com.delite.testbase.DELiteApp_TestBase;
import com.delite.utils.SwitchWindow;
import com.delite.utils.TakeScreenshot;


public class DELiteApp_TestCases extends DELiteApp_TestBase {
	
	DELite_LoginPage lp;
	DELiteApp_HomePage hp;
	DELiteApp_AuditCalendarPage auditCalendarPage;
	DELiteApp_AuditDetailsPage auditDetailsPage;
	DELiteApp_AssessmentContinuePage assessmentContinuePage;
	DELiteApp_AuditorDashboard auditorDashboardPage;
	TakeScreenshot screenShot;
	
	WebDriver driver;
	HashMap<String, String> dataFromAuditDetailsPage;
	HashMap<String, String> dataFromAssessmentPage;
	String activeStage;
	boolean auditStatus;
	boolean result;
	Object[][] testDataResult;
	Integer testCase,statusCol;
	String testDataNum;
	
	public void beforeTest(Integer testDataNum)
	{
		testDataResult = new Object[8][2];
		testCase=0;
		statusCol=0;
		this.testDataNum=testDataNum.toString();
		DELiteApp_TestBase.launchBrowser();
		this.driver=DELiteApp_TestBase.driver;
		lp= new DELite_LoginPage(driver);
		hp=new DELiteApp_HomePage(driver);
		auditCalendarPage=new DELiteApp_AuditCalendarPage(driver);
		auditDetailsPage = new DELiteApp_AuditDetailsPage(driver);
		assessmentContinuePage = new DELiteApp_AssessmentContinuePage(driver);
		auditorDashboardPage = new DELiteApp_AuditorDashboard(driver);
		screenShot=new TakeScreenshot(driver);
		String userName="";
		String passwd="";
		lp.logIn(userName, passwd);
	}
	

	public void testCase_001(String menuIcon,String subMenuIcon)
	{
		System.out.println("Test Case 001");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hp.selectOptionFromMenuBar(menuIcon,subMenuIcon);
		System.out.println(driver.getCurrentUrl());
		if(driver.getCurrentUrl().contains("Calendar"))
		{
			result=true;
			testDataResult[testCase][statusCol]="Test Case 001";
			testDataResult[testCase][statusCol+1]="PASSED";
			screenShot.pickScreenshot("Test Case 001", "PASSED",testDataNum);
			
		}
		else
		{
			result=false;
			testDataResult[testCase][statusCol]="Test Case 001";
			testDataResult[testCase][statusCol+1]="FAILED";
			screenShot.pickScreenshot("Test Case 001", "FAILED",testDataNum);
		}
		Assert.assertTrue(result,"User is able to access Audit Calender Page");
		
	}
	
	public void testCase_002(String planID)
	{
		System.out.println("Test Case 002");
		testCase++;
		auditCalendarPage.clickOnAuditWithPlanId(planID);
		dataFromAuditDetailsPage = auditDetailsPage.getDataFromAuditDetailsPage();
		result = auditCalendarPage.checkAvailabilityOfResumeAudit(planID);
		
		if(result)
		{
			testDataResult[testCase][statusCol]="Test Case 002";
			testDataResult[testCase][statusCol+1]="PASSED";
			screenShot.pickScreenshot("Test Case 002", "PASSED",testDataNum);
			
		}
		else
		{
			testDataResult[testCase][statusCol]="Test Case 002";
			testDataResult[testCase][statusCol+1]="FAILED";
			screenShot.pickScreenshot("Test Case 002", "FAILED",testDataNum);
		}
//		Assert.assertTrue(result,"Resume Audit Button is available");
	}
	
	public void testCase_003(String planID)
	{
		System.out.println("Test Case 003");
		testCase++;
		
		System.out.println("....."+dataFromAuditDetailsPage.get("Audit ID"));
		if(dataFromAuditDetailsPage.get("Audit ID").equals(planID))
		{
			result=true;
			testDataResult[testCase][statusCol]="Test Case 003";
			testDataResult[testCase][statusCol+1]="PASSED";
			screenShot.pickScreenshot("Test Case 003", "PASSED",testDataNum);
		}
		else
		{
			result=false;
			testDataResult[testCase][statusCol]="Test Case 003";
			testDataResult[testCase][statusCol+1]="FAILED";
			screenShot.pickScreenshot("Test Case 003", "FAILED",testDataNum);
		}
//		Assert.assertTrue(result,"Audit ID in Audit Calendar and Audit Details page is same");
	}
	
	
	public void testCase_004()
	{
		System.out.println("Test Case 004");
		testCase++;
//		auditDetailsPage.clickResumeAudit();
		activeStage=assessmentContinuePage.findActiveStage();
		if(activeStage.length()!=0)
		{
			result=true;
			testDataResult[testCase][statusCol]="Test Case 004";
			testDataResult[testCase][statusCol+1]="PASSED";
			screenShot.pickScreenshot("Test Case 004", "PASSED",testDataNum);
		}
		else
		{
			result=false;
			testDataResult[testCase][statusCol]="Test Case 004";
			testDataResult[testCase][statusCol+1]="FAILED";
			screenShot.pickScreenshot("Test Case 004", "FAILED",testDataNum);
		}
//		Assert.assertTrue(result,"Audit assessment page is opened");
	}
	

	public void testCase_005()
	{
		System.out.println("Test Case 005");
		testCase++;
		auditStatus=assessmentContinuePage.checkButtonAtRightCorner();
		if(auditStatus)
		{
			testDataResult[testCase][statusCol]="Test Case 005";
			testDataResult[testCase][statusCol+1]="PASSED";
			screenShot.pickScreenshot("Test Case 005", "PASSED",testDataNum);
		}
		else
		{
			testDataResult[testCase][statusCol]="Test Case 005";
			testDataResult[testCase][statusCol+1]="FAILED";
			screenShot.pickScreenshot("Test Case 005", "FAILED",testDataNum);
		}
//		Assert.assertTrue(auditStatus,"Button referring to next page is available");
	}
	

	public void testCase_006()
	{
		System.out.println("Test Case 006");
		testCase++;
		dataFromAssessmentPage=assessmentContinuePage.getDataFromAssessmentPage();
		System.out.println(dataFromAssessmentPage);
		if(dataFromAssessmentPage.get("Project Name").equals(dataFromAuditDetailsPage.get("Project Name")) && dataFromAssessmentPage.get("Audit ID").equals(dataFromAuditDetailsPage.get("Audit ID")))
		{
			result=true;
			testDataResult[testCase][statusCol]="Test Case 006";
			testDataResult[testCase][statusCol+1]="PASSED";
			screenShot.pickScreenshot("Test Case 006", "PASSED",testDataNum);
		}
		else
		{
			result=false;
			testDataResult[testCase][statusCol]="Test Case 006";
			testDataResult[testCase][statusCol+1]="FAILED";
			screenShot.pickScreenshot("Test Case 006", "FAILED",testDataNum);
		}
//		Assert.assertTrue(result,"Audit ID and Project Name in Assessment Page is same as that of Audit Details Page");
	}
	

	public void testCase_007()
	{
		testCase++;
		activeStage=assessmentContinuePage.findActiveStage();
		if(activeStage.equals("Audit Information"))
		{
			assessmentContinuePage.clickNextStageButton(activeStage);
			String nextStage="Assessment";
			if(assessmentContinuePage.findActiveStage().equals(nextStage))
			{
				result=true;
			}
			else
			{
				result=false;
			}
		}
		else if(activeStage.equals("Assessment"))
		{
			assessmentContinuePage.clickNextStageButton(activeStage);
			String nextStage="Fact Recording Sheet";
			if(assessmentContinuePage.findActiveStage().equals(nextStage))
			{
				result=true;
			}
			else
			{
				result=false;
			}
		}
		else if(activeStage.equals("Fact Recording Sheet"))
		{
			assessmentContinuePage.clickNextStageButton(activeStage);
			String nextStage="Category Detail";
			if(assessmentContinuePage.findActiveStage().equals(nextStage))
			{
				result=true;
			}
			else
			{
				result=false;
			}
		}
		else if(activeStage.equals("Category Detail"))
		{
			assessmentContinuePage.clickNextStageButton(activeStage);
			String nextStage="Mail & Complete";
			if(assessmentContinuePage.findActiveStage().equals(nextStage))
			{
				result=true;
			}
			else
			{
				result=false;
			}
		}
		else
		{
			assessmentContinuePage.clickNextStageButton(activeStage);
			if(driver.getCurrentUrl().contains("AuditorDashboard"))
			{
				result=true;
			}
			else
			{
				result=false;
			}
		}
		
		if(result)
		{
			testDataResult[testCase][statusCol]="Test Case 007";
			testDataResult[testCase][statusCol+1]="PASSED";
			screenShot.pickScreenshot("Test Case 007", "PASSED",testDataNum);
		}
		else
		{
			testDataResult[testCase][statusCol]="Test Case 007";
			testDataResult[testCase][statusCol+1]="FAILED";
			screenShot.pickScreenshot("Test Case 007", "FAILED",testDataNum);
		}
		Assert.assertTrue(result,"The user is able to click next stage button and successfully navigated to next stage");
	}
	
	public void testCase_008(String menuIcon,String subMenuIcon,String planID)
	{
//		activeStage=assessmentContinuePage.findActiveStage();
		if(activeStage.equals("Mail & Complete"))
		{
			System.out.println("............");
			auditorDashboardPage.clickButtonInDashboard("Completed");
			result=auditorDashboardPage.checkForAuditIdInDashBoard(planID);
		}
		else
		{
			driver.close();
			SwitchWindow.switchWindowControl(driver, 0);
			hp.selectOptionFromMenuBar(menuIcon, subMenuIcon);
			auditorDashboardPage.clickButtonInDashboard("In Progress");
			result=auditorDashboardPage.checkForAuditIdInDashBoard(planID);
		}
		if(result)
		{
			testDataResult[testCase][statusCol]="Test Case 008";
			testDataResult[testCase][statusCol+1]="PASSED";
			screenShot.pickScreenshot("Test Case 008", "PASSED",testDataNum);
		}
		else
		{
			testDataResult[testCase][statusCol]="Test Case 008";
			testDataResult[testCase][statusCol+1]="FAILED";
			screenShot.pickScreenshot("Test Case 008", "FAILED",testDataNum);
		}
	}
	
	
	public Object[][] afterTest()
	{
		driver.quit();
		return testDataResult;
	}

}

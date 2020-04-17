package com.delite.testscript;


import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.delite.testcases.DELiteApp_TestCases;
import com.delite.utils.ReadExcel;
import com.delite.utils.WriteExcel;

public class DELiteApp_TestScript {

	DELiteApp_TestCases testCaseRun;
	ReadExcel config;
	Integer rows;
	Integer testDataNum=0;
	Object[][] testDataResult;
	
	@BeforeTest
	public void beforeTest()
	{
		testCaseRun=new DELiteApp_TestCases();
	}
	
	@Test(dataProvider = "testData")
	public void deliteAppTestScript(String menuIcon,String subMenuIcon,Integer planID,String menuIconTc08,String subMenuIconTc08)
	{
		testDataNum++;
		String planId=planID.toString();
//		System.out.println(menuIcon);
//		System.out.println(subMenuIcon);
//		System.out.println(planId);
//		System.out.println(menuIconTc08);
//		System.out.println(subMenuIconTc08);

		testCaseRun.beforeTest(testDataNum);
		testCaseRun.testCase_001(menuIcon, subMenuIcon);
		testCaseRun.testCase_002(planId);
		testCaseRun.testCase_003(planId);
		testCaseRun.testCase_004();
		testCaseRun.testCase_005();
		testCaseRun.testCase_006();
		testCaseRun.testCase_007();
		testCaseRun.testCase_008(menuIconTc08, subMenuIconTc08, planId);
		
	}
	
	@DataProvider(name="testData")
	public Object testData()
	{
		config = new ReadExcel("src/test/resources/DataTables/deliteAppTestData.xlsx");
		 rows = config.getRowCount(0);
		 System.out.println(rows);
		Object[][] testDatas = new Object[rows-1][5];
		for (int instant = 1,i=0; instant < rows; instant++,i++) 
		{
			for(int z=0;z<5;z++)
			{
				testDatas[i][z] = config.getData(0,instant, z);
			}	
		}

		return testDatas;
	}
	
	@AfterMethod
	public void afterMethod()
	{
		testDataResult=testCaseRun.afterTest();
		System.out.println(testDataResult);
		try
		{
			WriteExcel writeExcelObject = new WriteExcel("src/test/resources/results/testResultForTestData "+testDataNum.toString()+".xlsx");
			writeExcelObject.writeData(testDataResult);
		}
		catch (EncryptedDocumentException e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	
}

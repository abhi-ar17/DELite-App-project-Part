package com.delite.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	 XSSFWorkbook wb;
	 XSSFSheet sheet;

	  
	 public ReadExcel(String excelPath)
	 {
	 try
	 	{
	 File src = new File(excelPath);
	 FileInputStream fis = new FileInputStream(src);
	 wb = new XSSFWorkbook(fis);

	 	}
	  
	 catch(Exception e)
	 	{
	 System.out.println(e.getMessage());
	 	}
	 }
	  
	 public Object getData(int sheetnumber, int row, int column)
	 {
	 sheet = wb.getSheetAt(sheetnumber);
	try {
			String data = sheet.getRow(row).getCell(column).getStringCellValue();
			 return data;
		
		}
			
	catch(Exception e)
		{
		 Integer data = (int) sheet.getRow(row).getCell(column).getNumericCellValue();
		 return data;	
		
		}	 
 }
	  
	 public int getRowCount(int sheetIndex)
	 {
	 int row = wb.getSheetAt(sheetIndex).getLastRowNum();
	 row = row + 1;
	 
	 return row;
	 }

	 public void close() throws IOException
	 {
		 wb.close();
	 }

}

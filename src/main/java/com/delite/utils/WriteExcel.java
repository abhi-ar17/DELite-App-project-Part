package com.delite.utils;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {
	FileOutputStream fos;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	String source;
	
	public WriteExcel(String excelPath) 
	{
		this.source=excelPath;
		 try 
		 {
			workbook =new XSSFWorkbook();
		 	sheet=workbook.createSheet("D");
		 }
		 catch(Exception e)
		 {}
	}
	
	
	 public void writeData(Object[][] testData) 
	 {
		 
		 int rowCount = 0;
         
	        for (Object[] aBook : testData)
	        {
	            Row row = sheet.createRow(++rowCount);
	             
	            int columnCount = 0;
	             
	            for (Object field : aBook) {
	                Cell cell = row.createCell(++columnCount);
	                cell.setCellValue((String) field);
	            }
	        }
	 	try 
	 	{
	 		fos=new FileOutputStream(source);
	 		workbook.write(fos);
	 		workbook.close();
	 		fos.close();
	 	}
	 	catch(Exception e)
	 	{
	 		
	 	}
	 }
}
	 

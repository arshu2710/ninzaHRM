package com.ninza.hrm.api.genericutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
public String getDataFromExcel(String sheetName,int rowNum, int celNum) throws EncryptedDocumentException, IOException {
	FileInputStream fis =new FileInputStream("./testData/testScriptdata.xlsx");
	Workbook wb =WorkbookFactory.create(fis);
	String data=wb.getSheet(sheetName).getRow(rowNum).getCell(celNum).toString();
	

	return data;
}
public int getRowCount (String sheetName) throws EncryptedDocumentException, IOException {
	FileInputStream fis =new FileInputStream("./testData/testScriptdata.xlsx");
	Workbook wb =WorkbookFactory.create(fis);
	int rowCount=wb.getSheet(sheetName).getLastRowNum();
	return rowCount;
}
public void setDataIntoExcel(String sheetName,int rowNum,int celNum,String data) throws IOException {
FileInputStream fis =new FileInputStream("./testData/testScriptdata.xlsx");
Workbook wb =WorkbookFactory.create(fis);
wb.getSheet(sheetName).getRow(rowNum).createCell(celNum).setCellValue(data);

FileOutputStream fos =new FileOutputStream("./testData/testScriptdata.xlsx");
wb.write(fos);
wb.close();

}
}

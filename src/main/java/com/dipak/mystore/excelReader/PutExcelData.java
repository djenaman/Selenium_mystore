package com.dipak.mystore.excelReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PutExcelData
{
	private XSSFWorkbook xwb;
	private XSSFSheet xs;
	private XSSFRow xr;
	FileInputStream fis;
	FileOutputStream fos;
	public PutExcelData() throws IOException, EncryptedDocumentException, InvalidFormatException
	{
		xs=null;
		xr=null;
		fis=new FileInputStream(System.getProperty("user.dir")+"//data//Masterdata.xlsx");
		xwb=(XSSFWorkbook) WorkbookFactory.create(fis);
		fos=new FileOutputStream(System.getProperty("user.dir")+"//data//Masterdata.xlsx");;
	}
	public void putLoginData(String sheetname,Map<String,String> data)
	{
		xs=xwb.getSheet(sheetname);
		xr=xs.createRow(xs.getLastRowNum()+1);
		Set<String> keydata=data.keySet(); 
		Object[] key=keydata.toArray();
		for(int i=0;i<keydata.size();i++)
		{
			xr.createCell(i).setCellValue(key[i].toString());
			xr.createCell(i+1).setCellValue(data.get(key[i].toString()));
		}
		try {
			xwb.write(fos); 
			fos.close();
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void putCellData(String Sheetname,int rownum,int colnum,String value)
	{
		xs=xwb.getSheet(Sheetname);
		xs.getRow(rownum).getCell(colnum).setCellValue(value);
	}
	
}

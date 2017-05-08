package com.dipak.mystore.excelReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetExcelData
{
	private XSSFWorkbook xwb;
	private XSSFSheet xs;
	private XSSFRow xr;
	FileInputStream fis;
	FileOutputStream fos;
	Properties props;
	public GetExcelData() throws IOException
	{
		xs=null;
		fis=new FileInputStream(System.getProperty("user.dir")+"//data//Masterdata.xlsx");
		xwb=new XSSFWorkbook(fis);
		fos=null;
		props=null;
	}
	public Map<String,String> getLoginData(String sheet) throws IOException
	{
		Map<String,String> data=new HashMap<String,String>();
		xs=xwb.getSheet(sheet);
		for(int i=1;i<=xs.getLastRowNum();i++)
		{
			xr=xs.getRow(i);
			xr.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
			data.put(xr.getCell(0).getStringCellValue(), xr.getCell(1).toString());
		}
		return data;
	}
	public String getCellData(String sheetname,int row,int col)
	{
		DataFormatter formatter = new DataFormatter();
		xs=xwb.getSheet(sheetname);
		Cell c=xs.getRow(row).getCell(col);
		if(c==null || c.getCellType() == Cell.CELL_TYPE_BLANK)
			return null;
		else
			return formatter.formatCellValue(c);
	}
	public int getColumnNumber(String sheetname,String columnName)
	{
		xs=xwb.getSheet(sheetname);
		int lastrow=xs.getLastRowNum();
		int firstRow=xs.getFirstRowNum();
		for(int i=firstRow;i<=lastrow;i++)
		{
			xr=xs.getRow(i);
			for(int j=xr.getFirstCellNum();j<=xr.getLastCellNum();j++)
			{
				if(xr.getCell(j).toString().equalsIgnoreCase(columnName))
					return j;
				//break;
			}
		}
		return -1;
	}
	public int getRowNumber(String sheetname,String emailId)
	{
		int rowNumber=-1;
		int colemailid=getColumnNumber(sheetname, "EmailID");
		xs=xwb.getSheet(sheetname);
		for(int i=0;i<=xs.getLastRowNum();i++)
		{
			if(xs.getRow(i).getCell(colemailid).toString().equals(emailId))
			{
				rowNumber=i;
				break;
			}
		}
		return rowNumber;
	}
	public Map<String,String> getActiveRegistrationData() throws Exception
	{
		Map<String,String> data=null;
		int statuscolumn=getColumnNumber("registration", "Status");
		if(statuscolumn==-1)
		{
			throw new Exception("Specified status column not found in the excel sheet... ");
		}
		else
		{
			xs=xwb.getSheet("registration");
			int lastrow=xs.getLastRowNum();
			int firstRow=xs.getFirstRowNum();
			data=new HashMap<String,String>();
			//DataFormatter formatter = new DataFormatter();
			for(int i=firstRow+1;i<=lastrow;i++)
			{
				xr=xs.getRow(i);
				if(getCellData("registration",i,statuscolumn)==null)
				{
					for(int j=xr.getFirstCellNum();j<xr.getLastCellNum();j++)
					{					
						data.put(getCellData("registration",firstRow,j),getCellData("registration",i,j));				

					}
					break;
				}

			}
		}
		return data;
	}
	public Map<String,String> getBlouseShoppingData(String sheetname) throws FileNotFoundException, IOException
	{
		Map<String,String> data=new HashMap<String,String>();
		XSSFRow firstrow=null;
		props=new Properties();
		props.load(new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dipak\\mystore\\config\\AppProperties.properties"));
		xs=xwb.getSheet(sheetname);
		firstrow=xs.getRow(xs.getFirstRowNum());
		int rowpointer=Integer.parseInt(props.getProperty("BlouseSheetRowPointer"));
		if(rowpointer==xs.getLastRowNum())
		{
			props.setProperty("BlouseSheetRowPointer", "1");
			rowpointer=1;
		}
		xr=xs.getRow(rowpointer);
		for(int i=0;i<xr.getLastCellNum();i++)
		{
			data.put(firstrow.getCell(i).getStringCellValue(), xr.getCell(i).toString());
		}
		
		return data;		
	}
}

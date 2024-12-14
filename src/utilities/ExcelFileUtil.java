package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//All excel data  here mthod is all non static acces in otejr using object of class
public class ExcelFileUtil
{

	XSSFWorkbook wb; //gloabl variable

	//defining one cosntructor (has no retrun type Nd as same name as class name) for reading excel ath
	//Whenevr we are going with reusable method we need to go with argument not with value.

	//creatign cnst for reading excel path
	public ExcelFileUtil (String ExcelPath) throws Throwable  //construc
	{

		FileInputStream fs = new FileInputStream(ExcelPath); //no need to give value give argument
		wb= new XSSFWorkbook(fs);//get file from ths workbook in this workbook mae excel file path

	}

	//count no of rows in sheet
	public int rowcount(String SheetName)
	{
		return wb.getSheet(SheetName).getLastRowNum();	

	}

	//method for reading column data
	public String getCellData(String SheetName, int row , int col) //with arg
	{
		String data= " "; //loacal declare within the mthod

		if(wb.getSheet(SheetName).getRow(row).getCell(col).getCellType()==Cell.CELL_TYPE_NUMERIC)  //if all data ==  nymeric   //comaprison opertor if numeric
		{
			int numericcelldata= (int) wb.getSheet(SheetName).getRow(row).getCell(col).getNumericCellValue(); //here all datain  numeric date here
			data= String.valueOf(numericcelldata);//here valueof int data ko string mae convet

		}

		else
		{ 
			data= wb.getSheet(SheetName).getRow(row).getCell(col).getStringCellValue();
		}

		return data;
	}


	//MEhtof for writing result and no retrun type just performing action 
	public void setCelldata(String Sheetname,int row,int col,String status,String WriteExcel) throws Throwable  ///new sheet
	{

		//get sheet from wb
		XSSFSheet sheet=wb.getSheet(Sheetname); // workbook sae sheet

		//get row  from sheet
		XSSFRow rownum=sheet.getRow(row);  //sheet sae row 

		//get cell from row
		XSSFCell cellnum= rownum.createCell(col); //row sae column

		//write status by cretaaing one column

		cellnum.setCellValue(status);

		if(status.equalsIgnoreCase("Pass"))
		{
			//if pass highligh color bod


			XSSFCellStyle style= wb.createCellStyle();
			XSSFFont font=wb.createFont();   //cretae obkect for font

			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font); //set all thoso font into style

			rownum.getCell(col).setCellStyle(style); //	set all this font in cell
		}

		else if(status.equalsIgnoreCase("Fail"))

		{
			XSSFCellStyle style= wb.createCellStyle();
			XSSFFont font=wb.createFont();   //cretae obkect for font

			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font); //set all thoso font into style

			rownum.getCell(col).setCellStyle(style); //	set all this font in cell


		}

		else if(status.equalsIgnoreCase("Blocked"))
		{


			XSSFCellStyle style= wb.createCellStyle();
			XSSFFont font=wb.createFont();   //cretae obkect for font

			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font); //set all thoso font into style

			rownum.getCell(col).setCellStyle(style); //	set all this font in cell
		}

		FileOutputStream fo= new FileOutputStream(WriteExcel); //weriet all this n otutpue
		wb.write(fo);


	}
	//calling this non staic method using Classanem onject in smae class
	public static void main(String[] args) throws Throwable 
	{ 
		ExcelFileUtil xl= new ExcelFileUtil("C:/EClipse_Shraddha/Sampledata_1.xlsx");//give here yur excel Sheet apth

		//rowcount
		int rowcount = xl.rowcount("Empdata_1");
		System.out.println(rowcount);

		for(int i=1;i<=rowcount;i++) //acess all data  give equal to then it wll read all
		{

			String fname= xl.getCellData("Empdata_1",i, 0); //first col=0
			String lastname=xl.getCellData("Empdata_1",i, 1); //sec ol
			String empid=xl.getCellData("Empdata_1",i, 2);
			System.out.println(fname+" " +lastname+ " " +empid);

			//access shhet dtaa
			//xl.setCelladata("Empdata_1", i, 3, "Pass", "C:/EClipse_Shraddha/OutputFormat.xlsx"); //get data from excel and write pass/fail/blocked in 4 col
			//xl.setCelladata("Empdata_1", i, 3, "Fail", "C:/EClipse_Shraddha/OutputFormat.xlsx"); //get data from excel and write pass/fail/blocked in 4 col
			xl.setCelldata("Empdata_1", i, 3, "Blocked", "C:/EClipse_Shraddha/OutputFormat.xlsx"); //get data from excel and write pass/fail/blocked in 4 col

		}


	}







}

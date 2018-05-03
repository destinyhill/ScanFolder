package ScanFolder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperation {
	
	public static Workbook createWorkbook(File file) throws Exception{
		Workbook wb = null;
		String fileName = file.getName();
		FileInputStream fs = new FileInputStream(file);
		if(fileName.endsWith(".xls"))
		{
			wb = new HSSFWorkbook(fs);
		}
		else if(fileName.endsWith(".xlsx"))
		{
			wb = new XSSFWorkbook(fs);
		}
		else
		{
			System.out.println("the file is not EXCEL");
		}
		return wb;
	}
	
	public void insertExcel(Workbook wb,int row,HashMap<Integer,String> col_data,String sheetName) throws IOException {
		Sheet sheet = wb.createSheet(sheetName);
		Row r = sheet.createRow(row);
		for(Entry<Integer,String> en : col_data.entrySet())
		{
			Cell cell = r.createCell(en.getKey());
			cell.setCellValue(en.getValue());
		}
	
	}
	
	public File checkExist(String filePath,String fileName) throws IOException{
		File file = new File(filePath+"//"+fileName);
		if(file.exists())
		{
			System.out.println("the file exsits!");
			return null;
		}
		else 
		{
			try {
				file.createNewFile();
			}
			catch(FileNotFoundException e) {
				System.out.println("the file fails to create: "+e.getMessage());
				throw new FileNotFoundException();
			}
			System.out.println("the file creates successfully!");
			return file;
		}
		
	}

}

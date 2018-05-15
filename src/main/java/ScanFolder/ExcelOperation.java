package ScanFolder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.onion.common.config.OnionConfig;

public class ExcelOperation {
	public static final Logger log = LoggerFactory.getLogger(ExcelOperation.class);
	private String fileType = OnionConfig.getString("file.type");
	private static Workbook wb = null;
	private static Sheet sheet = null;
	private static File file = null;
	
	public static Workbook createWorkbook(File file) throws IOException{		
		String fileName = file.getName();
		if(fileName.endsWith(".xls"))
		{
			wb = new HSSFWorkbook();
		}
		else if(fileName.endsWith(".xlsx"))
		{
			wb = new XSSFWorkbook();
		}
		else
		{
			System.out.println("the file is not EXCEL");
		}
		return wb;
	}
	
	public void insertExcel(int row,HashMap<Integer, String> col_data) throws IOException {	
		FileOutputStream fOut = null;
		if(sheet != null)
		{
			try 
			{	
				Row r = sheet.createRow(row);
				for(Entry<Integer,String> en : col_data.entrySet())
				{
					Cell cell = r.createCell(en.getKey());
					cell.setCellValue(en.getValue());
				}						
				fOut = new FileOutputStream(file);
				wb.write(fOut);
				fOut.flush();
			}
			catch(Exception e)
			{
				log.info(e.getMessage());
			}
		}
	}
	
	public void checkExist(String filePath,String fileName,String sheetName) throws IOException{
		Date date = new Date();  
        DateFormat format=new SimpleDateFormat("yyyy_MM_dd");  
        String s = fileName + format.format(date) + fileType;
		file = new File(filePath +"\\"+ s);
		if(file.exists())
		{
			log.info("the file:"+ s +" exsits!");
		}
		else 
		{
			try 
			{
				file.createNewFile();
				wb = ExcelOperation.createWorkbook(file);
				sheet = wb.createSheet(sheetName);
			}
			catch(FileNotFoundException e) {
				System.out.println("the file fails to create: "+e.getMessage());
				throw new FileNotFoundException();
			}
			log.info("the file:"+ s +" created successfully!");
		}	
	}
}

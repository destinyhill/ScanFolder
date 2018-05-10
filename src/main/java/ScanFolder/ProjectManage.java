package ScanFolder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectManage {
	public static final Logger log = LoggerFactory.getLogger(ProjectManage.class);
	private static int count = 1;
	private static int col = 0;
	private static int row = 0;
	private static HashMap<Integer, String> col_data = new HashMap<Integer, String>();
	private static int index = 0;
	private static String proName = null;
	//private static File fs = null;
	private static ExcelOperation ex = new ExcelOperation();
	
	public static void scanFolder(String path) throws IOException{
		File file = new File(path);				
		if(file.exists())
		{				
			if(count == 1)
			{		
				index = file.getAbsolutePath().split("\\\\").length ;	
				proName = file.getName();
				col_data.put(col, proName);
				log.info("the name of the project is: " + proName);		
				ex.checkExist(new PathOperation().GetPath(),proName,proName);
				count++;
			}
						
			File[] files = file.listFiles();			
			if(files.length == 0)
			{
				System.out.println(row + " " + col_data);
				System.out.println("the folder: "+ path +" is empty");
				new ExcelOperation().insertExcel(row, col_data);
				row++;
				col = 0;				
				col_data.clear();
				return;
			}
			else
			{				
				for(File f : files)
				{										
					if(f.isDirectory())
					{
						col = f.getAbsolutePath().split("\\\\").length - index;
						col_data.put(col,f.getName());
						scanFolder(f.getAbsolutePath());					
					}
					else 
					{
						col = f.getAbsolutePath().split("\\\\").length - index;
						col_data.put(col,f.getName());
						System.out.println(row + " " + col_data);	
						new ExcelOperation().insertExcel(row, col_data);
						col_data.clear();
						row++;
					}
				}
			}
		}
		else
		{
			log.info("the folder doesn't exist");
		}		
	}	
	
	
}

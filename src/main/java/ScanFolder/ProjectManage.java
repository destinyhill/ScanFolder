package ScanFolder;

import java.io.File;
import java.util.HashMap;


public class ProjectManage {
	static int count = 1;
	static int col = 0;
	static int row = 0;
	static HashMap<Integer, String> col_data = new HashMap<Integer, String>();
	static boolean isFile = false;
	static int index = 0;
	
	public static void scanFolder(String path){
		File file = new File(path);		
		
		if(file.exists())
		{	
			
			if(count == 1)
			{		
				index = file.getAbsolutePath().split("\\\\").length ;	
				String proName = file.getName();
				System.out.println("the name of the project is: " + proName);
				col_data.put(col, proName);
				//System.out.println(row + " " + col_data);
				count++;
			}
			
			
			File[] files = file.listFiles();			
			if(files.length == 0)
			{
				System.out.println(row + " " + col_data);
				System.out.println("the folder: "+ path +" is empty");
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
						//System.out.println(row + " " + col_data);
						scanFolder(f.getAbsolutePath());					
					}
					else 
					{
						col = f.getAbsolutePath().split("\\\\").length - index;
						col_data.put(col,f.getName());
						System.out.println(row + " " + col_data);
						col_data.clear();
						row++;
					}
				}
			}
		}
		else
		{
			System.out.println("the folder doesn't exist");
		}
	}
		
}

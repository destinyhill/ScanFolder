package main.java.ScanFolder;

import java.io.File;
import java.util.Scanner;

public class demo {
	static int count = 1;
	static int col = 1;
	static int row = 1;
	
	public static void ScanFolder(String path){
		File file = new File(path);				
		if(file.exists())
		{
			if(count == 1)
			{
				System.out.printf("%d,%d  %s\n",row,col,file.getName());
				count++;
			}
			
			File[] files = file.listFiles();
			
			if(files.length == 0)
			{
				System.out.println("the folder: "+ path +" is empty");
				row++;
				col = 1;
				return;
			}
			{				
				for(File f : files)
				{											
					if(f.isDirectory())
					{
						col++;
						System.out.printf("%d,%d  %s\n",row,col,f.getName());
						ScanFolder(f.getAbsolutePath());						
					}
					else 
					{
						System.out.printf("%d,%d  %s\n",row++,col+1,f.getName());
					}
				}
			}
		}
		else
		{
			System.out.println("the folder doesn't exist");
		}
	}
	
	public static void main(String[] args){		
		try{
			Scanner path = new Scanner(System.in);
			System.out.println("Input the path:");
			String s = path.nextLine();
			if(s!= null)
			{
				ScanFolder(s);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}

package ScanFolder;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScanFolder {
	
	public static final Logger log = LoggerFactory.getLogger(ScanFolder.class);
	public static void main(String[] args){		
		try{
			Scanner path = new Scanner(System.in);
			System.out.println("Input the path:");
			String s = path.nextLine();
			if(s!= null)
			{
				ProjectManage.scanFolder(s);				
			}		
			log.info("successfully~~~~");
			path.close();
		}
		catch(Exception e){
			log.info(e.getMessage());
		}
		
		
	}

}

package ScanFolder;

import java.util.Scanner;

public class UserDemo {
	
	
	public static void main(String[] args){		
		try{
			Scanner path = new Scanner(System.in);
			System.out.println("Input the path:");
			String s = path.nextLine();
			if(s!= null)
			{
				ProjectManage.scanFolder(s);
				
			}
			path.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
	}

}

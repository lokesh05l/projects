package com.app.main;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import com.app.bo.impl.FileHandlerBOImpl;
import com.app.exception.BusinessException;


public class FilesMain {
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("Welcome to File Handling App");
		System.out.println("----Developed by Lokesh----\n");
		FileHandlerBOImpl fileHandlerBO=new FileHandlerBOImpl();
		
		String dirPath,fileName;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the path of root directory:");
		dirPath=sc.nextLine();
		
		if( fileHandlerBO.isValidPath(dirPath))
		{
			int choice=0;
		
			do
			{
				System.out.println("File Handling Menu");
				System.out.println("------------------");
				System.out.println("1. Display Current Files");
				System.out.println("2. Add a file");
				System.out.println("3. Delete a file");
				System.out.println("4. Search a file");
				System.out.println("5. Close the App");
				System.out.println("Enter your choice:");
				choice=Integer.parseInt(sc.nextLine());
			
				switch(choice)
				{
					case 1: 
						try{
							File[] files=fileHandlerBO.displayFiles(dirPath);
							for(File file:files)
								System.out.println(file.getName());
						}
						catch(BusinessException e){
							System.out.println(e.getMessage());
						}
						break;
					
					case 2: 
						System.out.println("\nEnter the filename to be created:");
						fileName=sc.nextLine();
						try {
								File fil=fileHandlerBO.addFile(fileName,dirPath);
								if(fil!=null)
									System.out.println("File Created Successfully\n");
						}catch(BusinessException e)
						{
									System.out.println(e.getMessage());
						}	
						break;
					
					case 3: 
						try {
							fileHandlerBO.deleteFile(dirPath);
						}catch(BusinessException e)
						{
							System.out.println(e.getMessage());
						}
						break;
				
					case 4:
						System.out.println("\nEnter the filename to be searched:");
						fileName=sc.nextLine();
						try {
							File fi=fileHandlerBO.searchFile(fileName, dirPath);
							if(fi!=null)
								System.out.println("File with name "+fileName+" is found\n");
						}catch(BusinessException e)
						{
						System.out.println(e.getMessage());
						}
						break;
					
					case 5: 
						System.out.println("Thank you for using our App");
						break;
					
					default: System.out.println("Invalid Choice! Please enter a valid one\n");
				}
			}while(choice!=5);	
		}
		else
			System.out.println("Please enter a valid directory path");
	}
}

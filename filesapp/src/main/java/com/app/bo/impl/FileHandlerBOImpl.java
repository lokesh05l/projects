package com.app.bo.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Scanner;
import com.app.bo.FileHandlerBO;
import com.app.exception.BusinessException;

public class FileHandlerBOImpl implements FileHandlerBO
{
	Scanner sc=new Scanner(System.in);
	@Override
	public File searchFile(String fileName, String dirPath) throws BusinessException
	{
		File fil=null;
		
		if(new FileHandlerBOImpl().checkValidName(fileName,dirPath))
		{
			String filePath=dirPath+fileName;
			File file=new File(filePath);
			
			if(file.exists())
				fil=file;
			else
				throw new BusinessException("File with name "+fileName+" not found!\n");
		}
		else
			throw new BusinessException("Please enter a valid filename\n");
		
		return fil;
	}

	@Override
	public File addFile (String fileName, String dirPath) throws BusinessException
	{
		File fil=null;
		
		if(new FileHandlerBOImpl().checkValidName(fileName,dirPath))
		{
				String filePath=dirPath+fileName;
				try {
					fil=new File(filePath);
					if(!fil.exists())
						fil.createNewFile();
					else
						throw new BusinessException("File already exists! Try creating with other name\n");
				} 
				catch (IOException e) {
					System.out.println("Oops! An Error Occured. Try Again\n");
				}
		}
		else
			throw new BusinessException("Please enter a valid filename");
		
		return fil;
	}

	@Override
	public boolean checkValidName(String fileName, String dirPath) 
	{
		String filePath=dirPath+fileName;

		if(filePath.equals(dirPath) || fileName.matches("[\\\"<>\\|\\?\\/://*]+\\.[a-z]{3,}"))
			return false;
		else
			return true;
	}

	@Override
	public File[] displayFiles(String dirPath) throws BusinessException 
	{	
			File[] files=null;
			
			if(new FileHandlerBOImpl().isDirectoryEmpty(dirPath))
			{
				File fil=new File(dirPath);
				files=fil.listFiles();
			}
			else
				throw new BusinessException("Directory is Empty!\n");
			
			return files;
	}

	@Override
	public boolean isDirectoryEmpty(String dirPath) 
	{
		File fil=new File(dirPath);
		String[] list=fil.list();
		
		if(list==null || list.length==0)
			return false;
		else
			return true;
	}

	@Override
	public void deleteFile(String dirPath) throws BusinessException 
	{
		if(new FileHandlerBOImpl().isDirectoryEmpty(dirPath))
		{
			System.out.println("\nEnter the filename to be deleted");
			String fileName=sc.nextLine();
			
			if(new FileHandlerBOImpl().checkValidName(fileName,dirPath))
			{
				File file= new FileHandlerBOImpl().searchFile(fileName,dirPath);
				if(file!=null)
				{
					String filePath=dirPath+fileName;
					File fil=new File(filePath);
					fil.delete();
					System.out.println("File Deleted Successfully\n");
				}
			}
			else
				throw new BusinessException("Please enter a valid filename\n");
		}
		else
			throw new BusinessException("Directory is Empty!\n");		
	}

	@Override
	public boolean isValidPath(String dirPath) 
	{
		File fil=new File(dirPath);
		return fil.isDirectory();
	}
}

package com.app.bo;

import java.io.File;
import java.io.IOException;
import com.app.exception.BusinessException;

public interface FileHandlerBO {

	public File searchFile(String fileName, String dirPath) throws BusinessException;
	public File addFile(String fileName, String dirPath) throws BusinessException;
	public boolean checkValidName(String fileName, String dirPath);
	public File[] displayFiles(String dirPath) throws BusinessException;
	public void deleteFile (String dirPath) throws BusinessException;
	public boolean isDirectoryEmpty(String dirPath);
	public boolean isValidPath(String dirPath);
}

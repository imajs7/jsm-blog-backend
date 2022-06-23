package com.jsmblog.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsmblog.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile fileToBeUploaded) throws IOException {
		
		// create file name
		String fileName = fileToBeUploaded.getOriginalFilename();
				
		// full path
		String filePath = path + File.separator + fileName;
				
		// create folder if not created
		File destinationFolder = new File(path);
		
		if( ! destinationFolder.exists() )
			destinationFolder.mkdir();
		
		// file copy
		Files.copy( fileToBeUploaded.getInputStream(), Paths.get(filePath) );
		
		return fileName;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath = path + File.separator + fileName;
		InputStream inputStream = new FileInputStream(fullPath);
		return inputStream;
	}

}

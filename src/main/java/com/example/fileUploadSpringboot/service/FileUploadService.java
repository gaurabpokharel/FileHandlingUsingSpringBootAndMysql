package com.example.fileUploadSpringboot.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.fileUploadSpringboot.model.UploadedFile;

public interface FileUploadService {

	public void uploadToLocal(MultipartFile multipartFile);
	
	public void uploadToDB(MultipartFile file);
	
	public UploadedFile downloadFile(int id);
}

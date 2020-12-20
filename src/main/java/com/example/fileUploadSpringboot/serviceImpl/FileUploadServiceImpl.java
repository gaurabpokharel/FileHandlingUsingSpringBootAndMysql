package com.example.fileUploadSpringboot.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.fileUploadSpringboot.model.UploadedFile;
import com.example.fileUploadSpringboot.repository.FileUploadRepository;
import com.example.fileUploadSpringboot.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	@Autowired
	private FileUploadRepository fileUploadRepository; 
	private String uploadFolderPath="D:\\Clonion\\";
	public void uploadToLocal(MultipartFile file) {
		
		try {
			byte[] data=file.getBytes();
			Path path=Paths.get(uploadFolderPath + file.getOriginalFilename());
			Files.write(path,data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void uploadToDB(MultipartFile file) {
//		UploadedFile uploadedFile=new UploadedFile();
//		try {
//			uploadedFile.setFileData(file.getBytes());
//			uploadedFile.setFileType(file.getContentType());
//			uploadedFile.setFileName(file.getOriginalFilename());
//			fileUploadRepository.save(uploadedFile);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	public UploadedFile downloadFile(int id) {
		UploadedFile uploadFileToRet=fileUploadRepository.findById(id).get();
		return uploadFileToRet ;
	}
}

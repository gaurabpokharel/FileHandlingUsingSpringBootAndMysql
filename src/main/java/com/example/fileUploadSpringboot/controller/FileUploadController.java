package com.example.fileUploadSpringboot.controller;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.fileUploadSpringboot.model.UploadedFile;
import com.example.fileUploadSpringboot.repository.FileUploadRepository;
import com.example.fileUploadSpringboot.service.FileUploadService;



@RestController
@RequestMapping("/api/v1")
public class FileUploadController {

	@Autowired
	FileUploadService fileUploadService;
	@Autowired
	FileUploadRepository fileUploadRepository; 
	

	@PostMapping("/upload/local")
	public void uploadLocal(@RequestParam("file") MultipartFile multipartFile)
	{
		fileUploadService.uploadToLocal(multipartFile);
	}
	
	
	@PostMapping("/upload/db")
	public void uploadDb(@RequestParam("file") MultipartFile file,
			@RequestParam("firstName") String firstname,
			@RequestParam("lastName") String lastname,
			@RequestParam("contactNumber") String contactnumber)
	{
		UploadedFile uploadedFile=new UploadedFile();
		try {
			uploadedFile.setFileData(file.getBytes());
			uploadedFile.setFileType(file.getContentType());
			uploadedFile.setFileName(file.getOriginalFilename());
			uploadedFile.setFirstName(firstname);
			uploadedFile.setLastName(lastname);
			uploadedFile.setContactNumber(contactnumber);
			fileUploadRepository.save(uploadedFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/download/{id}")
	public ResponseEntity<Resource> downloadFile(@PathVariable int id)
	{
		UploadedFile uploadFileToRet= fileUploadService.downloadFile(id);
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(uploadFileToRet.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment :filename"+uploadFileToRet.getFileName())
				.body(new ByteArrayResource(uploadFileToRet.getFileData()));
	}
	
}

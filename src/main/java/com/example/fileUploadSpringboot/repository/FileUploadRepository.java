package com.example.fileUploadSpringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fileUploadSpringboot.model.UploadedFile;

@Repository
public interface FileUploadRepository extends JpaRepository<UploadedFile, Integer> {

	
}

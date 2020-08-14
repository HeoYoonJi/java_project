/**
 * 
 */
package com.ssm.project.service;

import org.springframework.web.multipart.MultipartFile;

import com.ssm.project.domain.FileVO;

public interface FileUploadService {
	
	FileVO storeUploadFile(int boardNum, MultipartFile file);

}
/**
 * 
 */
package com.ssm.project.service;

import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * @author javateam
 *
 */
@Service
@Slf4j
public class FileUploadNamingServiceImpl implements FileUploadNamingService {
	
	/**
	 * @see com.javateam.spring_board.util.FileUploadNamingService
	 * #parseFilePostfix(int, java.lang.String)
	 */
	@Override
	public String parseFilePostfix(int boardNum, String file) {

		log.info("#### 실제 저장 파일로 변환 : 파일 접미어(_ + boardNum) 첨부");
		// 본 파일명과 확장자 분리 처리
		String[] fileStr = file.split("\\."); 
		String fileName = fileStr[0];
		String fileExt = fileStr[1];
		
		// 업로드 파일명 형성
		return fileName + "_" 
			   + boardNum  
			   + "." + fileExt;
	} //

	@Override
	public String getOriginalFilename(int boardNum, String file) {
		
		log.info("#### 원래 파일명으로 변환 : 파일 접미어 제거");
		String result = null;
		
		String[] fileStr = file.split("\\."); 
		String fileName = fileStr[0];
		String fileExt = fileStr[1];
		
		fileName = fileName.replaceAll("_"+boardNum, "");
		
		result = fileName + "." + fileExt;
		
		return result;
	}

}

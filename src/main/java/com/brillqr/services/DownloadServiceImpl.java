package com.brillqr.services;

import java.io.File;
import java.io.FileInputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.brillqr.constant.QR_CONSTANT;

@Service
public class DownloadServiceImpl implements DownloadService{
	
	@Override
	public ResponseEntity<Object> downloadVCF(String qrID) {
		
		 try {
		    	File inputStream = new File(QR_CONSTANT.LOCAL_STORAGE_PATH_VCF+qrID+QR_CONSTANT.VCF_EXTENSION);
		    	InputStreamResource isr = new InputStreamResource(new FileInputStream(inputStream));
		    	HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", inputStream.getName()));
				headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
				headers.add("Pragma", "no-cache");
				headers.add("Expires", "0");
				
				ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(inputStream.length()).contentType(MediaType.parseMediaType("application/txt")).body(isr);
				return responseEntity;
		    }catch (Exception e) {
				return new ResponseEntity<>("error occurred at downloadVCF(String qrID)", HttpStatus.INTERNAL_SERVER_ERROR);	
			}
	}

}

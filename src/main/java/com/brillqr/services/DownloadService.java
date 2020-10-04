package com.brillqr.services;

import org.springframework.http.ResponseEntity;

public interface DownloadService {

	ResponseEntity<Object> downloadVCF(String qrId);
		
}

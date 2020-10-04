package com.brillqr.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brillqr.services.DownloadService;



@RestController
@RequestMapping("/download")
public class DownloadController {

	@Autowired
	DownloadService downloadService;
	
	@GetMapping(value = "/vcf/{qrId}")
	public ResponseEntity<Object> downloadVCF(@PathVariable String qrId) {
	   
		return downloadService.downloadVCF(qrId);
	}
}

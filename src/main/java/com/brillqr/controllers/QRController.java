package com.brillqr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.brillqr.dto.QRDataPayload;
import com.brillqr.dto.Response;
import com.brillqr.services.QRService;


@RestController
@RequestMapping("/qr")
@CrossOrigin(origins = "*")
public class QRController {

    @Autowired
    QRService qrService;


    @PostMapping(value ="/saveQRData",produces = { "application/json; charset=UTF-8" }, consumes = {
	"application/json; charset=UTF-8" })
    @ResponseBody
    public ResponseEntity  saveQRData(@RequestBody QRDataPayload qrDataPayload){
    		Response response = qrService.saveOR(qrDataPayload);
    		return new ResponseEntity(response,HttpStatus.OK);
    }
    
    @GetMapping(value = "/getSingleQRData/{qrId}")
    public ResponseEntity getQRDataFromQRId(@PathVariable String qrId) {
    	Response response = qrService.getQRDataFromQRId(qrId);
    	return new ResponseEntity(response,HttpStatus.OK);
    }
}

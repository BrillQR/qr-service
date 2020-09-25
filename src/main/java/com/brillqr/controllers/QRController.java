package com.brillqr.controllers;

import com.brillqr.dto.QRDataPayload;
import com.brillqr.dto.Response;
import com.brillqr.services.QRService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/qr")
public class QRController {

    @Autowired
    QRService qrService;


    @PostMapping(value ="/saveQRData",produces = { "application/json; charset=UTF-8" }, consumes = {
	"application/json; charset=UTF-8" })
    @CrossOrigin
    @ResponseBody
    public ResponseEntity  saveQRData(@RequestBody QRDataPayload qrDataPayload){
    		Response response = qrService.saveOR(qrDataPayload);
    		return new ResponseEntity(response,HttpStatus.OK);
    }
}

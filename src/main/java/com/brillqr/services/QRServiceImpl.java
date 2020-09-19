package com.brillqr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.brillqr.dto.Response;
import com.brillqr.helper.ResponseHelper;
import com.brillqr.dto.QRDataPayload;
import com.brillqr.entities.QRData;
import com.brillqr.repositories.QRDataRepository;

@Service
public class QRServiceImpl implements QRService{

	@Autowired
	private QRDataRepository qrDataRepository;

	@Override
	public Response saveOR(QRDataPayload qrDataPayload) {
		Response response = ResponseHelper.getResponseTemplate();
		
		if(qrDataPayload!=null && StringUtils.isNotBlank(qrDataPayload.getUserCode())) {
			
			QRData qrData = qrDataRepository.findByUserCode(qrDataPayload.getUserCode());
			if(qrData==null) {
				qrData = new QRData();
				qrData.setContact(qrDataPayload.getContact());
				qrData.setCountryCode(qrDataPayload.getCountryCode());
				qrData.setEmailId(qrDataPayload.getEmailId());
				qrData.setFullName(qrDataPayload.getFullName());
				qrData.setUserCode(qrDataPayload.getUserCode());
				
				qrDataRepository.save(qrData);
				response.getData().put("Success :","QR Data Inserted");
				return response;
				
			}else {
				response.getData().put("Error :", "Duplicate Value Found");
				return response;
			}
		}
		else {
			response.getError().put("Error :", "Data Not Found");
			return response;
		}
	}
	
	 
}

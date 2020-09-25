package com.brillqr.services;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
		
		if(qrDataPayload!=null && StringUtils.isNotBlank(qrDataPayload.getUserCode()) && StringUtils.isNotBlank(qrDataPayload.getQrId())) {
			
			QRData qrData = qrDataRepository.findByUserCode(qrDataPayload.getQrId());
			if(qrData==null) {
				qrData = new QRData();
				qrData.setQrId(qrDataPayload.getQrId());
				qrData.setContact(qrDataPayload.getContact());
				qrData.setEmail(qrDataPayload.getEmail());
				qrData.setUserCode(qrDataPayload.getUserCode());
				qrData.setAddress(qrDataPayload.getAddress());
				qrData.setBusinessEstDate(LocalDateTime.now());
				qrData.setEducation(qrDataPayload.getEducation());
				qrData.setExperience(qrDataPayload.getExperience());
				qrData.setFieldProjects(qrDataPayload.getFieldProjects());
				qrData.setFirstName(qrDataPayload.getFirstName());
				qrData.setLastName(qrDataPayload.getLastName());
				qrData.setSurname(qrDataPayload.getSurname());
				qrData.setProfession(qrDataPayload.getProfession());
				qrData.setWebsite(qrDataPayload.getWebsite());
				qrData.setProfilePhoto(qrDataPayload.getProfilePhoto());
				
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

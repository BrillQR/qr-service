package com.brillqr.services;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.brillqr.dto.Response;
import com.brillqr.helper.Abbreviation;
import com.brillqr.helper.QRCodeGenerator;
import com.brillqr.helper.ResponseHelper;
import com.brillqr.helper.VCFGenerator;
import com.brillqr.constant.QR_CONSTANT;
import com.brillqr.dto.QRDataPayload;
import com.brillqr.entities.QRData;
import com.brillqr.entities.QRMataData;
import com.brillqr.repositories.QRDataRepository;
import com.brillqr.repositories.QRMatadataRepository;

@Service
public class QRServiceImpl implements QRService{

	@Autowired
	private QRDataRepository qrDataRepository;
	
	@Autowired 
	private QRMatadataRepository  qrMatadataRepository;
	
	@Autowired
	QRMatadataService qrMatadataService;

	@Override
	public Response saveOR(QRDataPayload qrDataPayload) {
		Response response = ResponseHelper.getResponseTemplate();
		
		if(qrDataPayload!=null && StringUtils.isNotBlank(qrDataPayload.getUserCode())) {
			
			QRData qrData = qrDataRepository.findByUserCode(qrDataPayload.getQrId());
			if(qrData==null) {
				qrData = new QRData();
				String qrId = Abbreviation.QR.abbreviation()+"-"+ UUID.randomUUID().toString();
				qrDataPayload.setQrId(qrId);
				qrData.setQrId(qrId);
				qrData.setContact(qrDataPayload.getContact());
				qrData.setEmail(qrDataPayload.getEmail());
				qrData.setUserCode(qrDataPayload.getUserCode());
				qrData.setBusinessEstDate(qrDataPayload.getBusinessEstDate());
				qrData.setEducation(qrDataPayload.getEducation());
				qrData.setExperience(qrDataPayload.getExperience());
				qrData.setFieldProjects(qrDataPayload.getFieldProjects());
				qrData.setPrefix(qrDataPayload.getPrefix());
				qrData.setFirstName(qrDataPayload.getFirstName());
				qrData.setLastName(qrDataPayload.getLastName());
				qrData.setSurname(qrDataPayload.getSurname());
				qrData.setProfession(qrDataPayload.getProfession());
				qrData.setWebsite(qrDataPayload.getWebsite());
				qrData.setProfilePhoto(qrDataPayload.getProfilePhoto());
				qrData.setCountry(qrDataPayload.getCountry());
				qrData.setLocality(qrDataPayload.getLocality());
				qrData.setPostalCode(qrDataPayload.getPostalCode());
				qrData.setRegion(qrDataPayload.getRegion());
				qrData.setWorkAddress(qrDataPayload.isWorkAddress());
				qrData.setStreetAddress(qrDataPayload.getStreetAddress());
				qrData.setOrganization(qrDataPayload.getOrganization());
				
				qrData = qrDataRepository.save(qrData);
				if(qrData!=null) {
				VCFGenerator vcfGenerator = new VCFGenerator(qrDataPayload);
				String finalVcfData = vcfGenerator.getVCFData();
				 //from string generate VCF File and store it to AWS s3;
				AWSS3Storage  awss3Storage = new AWSS3Storage(qrData.getQrId());
				awss3Storage.saveVCFtoS3();
				//update QR_matadata
				boolean isMataDataUpdated = qrMatadataService.saveQRMatadata(qrData.getUserCode(), qrData.getQrId(),QR_CONSTANT.S3_BUCKET_NAME);
				
				QRCodeGenerator qrCodeGenerator = new QRCodeGenerator(QR_CONSTANT.BASE_QR_DATA_URL,qrDataPayload.getQrId());
				qrCodeGenerator.SaveQRCodeAsImage();
				response.getDataMap().put("Success :","QR Data Inserted and QR Image Generated and VCF file Stored in AWS S3");
				return response;
				}else {
					response.getError().put("DB Error :","While Saving QR - Data");
					return response;
				}
				
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

	@Override
	public Response getQRDataFromQRId(String qrId) {
		
		Response response = ResponseHelper.getResponseTemplate();
		
		QRData qrData = qrDataRepository.findByqrId(qrId);
		
		QRMataData qrMataData = qrMatadataRepository.findByqrId(qrId);
		if(qrData!=null && qrMataData!=null) {
			response.getData().put("QR Data for ID: "+ qrId, qrData);
			response.getData().put("QR MataData for ID: "+ qrId, qrMataData);
			response.getData().put("downloadUri","http://localhost:8001/api/v1/download/vcf/"+qrId);
			return response;
		}else {
			response.getError().put("Error :","QR Data not present");
			return response;
		}	
		
	}
	
	 
}

package com.brillqr.services;

import java.time.LocalDate;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brillqr.entities.QRMataData;
import com.brillqr.repositories.QRMatadataRepository;


@Service
public class QRMatadataServiceImpl implements QRMatadataService{
	
	@Autowired
	QRMatadataRepository qrMatadataRepository;
	
	
	@Override
	public boolean saveQRMatadata(String userCode,String qrId,String location) {
	
		QRMataData qrMataData;
		if(StringUtils.isNotBlank(userCode) &&StringUtils.isNotBlank(qrId) && StringUtils.isNotBlank(location)) {
			qrMataData = qrMatadataRepository.findByqrId(qrId);
			  if(qrMataData==null) {
				  qrMataData = new QRMataData();
				  qrMataData.setDateCreated(LocalDate.now());
				  qrMataData.setQrId(qrId);
				  qrMataData.setQrLocation(location);
				  qrMataData.setUserCode(userCode);
				  qrMataData.setQrScanCount(0);
				  
				  qrMatadataRepository.save(qrMataData);
				  
				  return true;
			  }else {
				  return false;
			  }
			
		}
		else {
			return false;
		}
	}

	
}

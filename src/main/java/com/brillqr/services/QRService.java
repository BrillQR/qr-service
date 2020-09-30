package com.brillqr.services;

import com.brillqr.dto.QRDataPayload;
import com.brillqr.dto.Response;
import com.brillqr.entities.QRData;

public interface QRService {
	
	Response saveOR(QRDataPayload qrDataPayload);
	QRData getQRData(String userCode);

}

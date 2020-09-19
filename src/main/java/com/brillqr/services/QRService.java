package com.brillqr.services;

import com.brillqr.dto.QRDataPayload;
import com.brillqr.dto.Response;

public interface QRService {
	
	Response saveOR(QRDataPayload qrDataPayload);

}

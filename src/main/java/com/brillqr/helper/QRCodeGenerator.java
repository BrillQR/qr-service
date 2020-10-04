package com.brillqr.helper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.brillqr.constant.QR_CONSTANT;
import com.brillqr.exceptions.QRException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.client.j2se.MatrixToImageWriter;

public class QRCodeGenerator {

	private String baseUrl;
	private String qrId;
	
	public final static String folderLocation = QR_CONSTANT.LOCAL_STORAGE_PATH_QR;
	final static String charset = QR_CONSTANT.CHARSET;
	public QRCodeGenerator(){
		this.baseUrl=null;
		this.qrId=null;
	}
	public QRCodeGenerator(String vcfData,String qrId) {
		this.baseUrl = vcfData;
		this.qrId=qrId;
	}
	
	public void SaveQRCodeAsImage() {
		try {
		Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType,ErrorCorrectionLevel>(1);
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        BitMatrix matrix = new MultiFormatWriter().encode(
        		baseUrl+qrId,
                BarcodeFormat.QR_CODE,800,800,hintMap);
            MatrixToImageWriter.writeToPath(matrix,QR_CONSTANT.QR_EXTENSION.substring(1), new File(folderLocation+qrId+QR_CONSTANT.QR_EXTENSION).toPath());
		}catch(Exception e) {
			throw new QRException("Error while SaveQRCodeAsImage()");
		}
	}
}

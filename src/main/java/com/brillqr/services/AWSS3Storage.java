package com.brillqr.services;

import java.io.File;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.brillqr.constant.QR_CONSTANT;
import com.brillqr.exceptions.VCFException;


public class AWSS3Storage {
	private String qrId;
	public AWSS3Storage() {
		this.qrId=null;
	}
	public AWSS3Storage(String qrId) {
		this.qrId = qrId;
	}

	public void saveVCFtoS3(){
		try {
		BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials("","");
		AmazonS3 awsClient = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_SOUTH_1).withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).build();
		String filePath = QR_CONSTANT.LOCAL_STORAGE_PATH_VCF+qrId+QR_CONSTANT.VCF_EXTENSION;
		PutObjectRequest request = new PutObjectRequest(QR_CONSTANT.S3_BUCKET_NAME,qrId+QR_CONSTANT.VCF_EXTENSION,new File(filePath));
		
		awsClient.putObject(request);
		}catch(AmazonS3Exception s3) {
			throw new VCFException("Error While Putting File to AWS");
		}catch(Exception e) {
			throw new VCFException("Error While : saveVCFtoS3() ");
		}
	}
}

package com.brillqr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brillqr.entities.QRMataData;

public interface QRMatadataRepository extends JpaRepository<QRMataData,String>{
	
	QRMataData findByqrId(String qrId);
}

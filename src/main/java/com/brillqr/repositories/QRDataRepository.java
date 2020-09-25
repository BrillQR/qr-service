package com.brillqr.repositories;

import com.brillqr.entities.QRData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QRDataRepository extends JpaRepository<QRData,String> {
    QRData findByUserCode(String userCode);
    
    QRData findByqrId(String qrId);
}

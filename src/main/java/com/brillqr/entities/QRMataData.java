package com.brillqr.entities;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "qr_matadata")
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QRMataData {
	
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name ="id")
	@Id
	private Long id;
	
	@Column(name = "user_code")
	private String userCode;
	
	@Column(name = "qr_id")
	private String qrId;
	
	@Column(name = "date_created")
	private LocalDate dateCreated;
	
	@Column(name = "date_updated")
	private LocalDate dateUpdated;
	
	@Column(name = "qr_scan_count")
	private long qrScanCount;
	
	@Column(name = "qr_location")
	private String qrLocation;
}

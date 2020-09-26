package com.brillqr.dto;

import java.time.LocalDate;
import java.util.Date;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class QRDataPayload {

	private Long id;
	private String qrId;
	private String userCode;
	private String prefix;
	private String firstName;
	private String lastName;
	private String surname;
	private long contact;
	private String profession;
	private String experience;
	private String education;
	private String profilePhoto;
	private String website;
	private String fieldProjects;
	private LocalDate businessEstDate;
	private String email;
	//address part
	private String streetAddress;
	private String locality;
	private String region;
	private Long postalCode;
	private String country;
	private boolean isWorkAddress;
	private String organization;
}

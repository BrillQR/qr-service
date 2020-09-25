package com.brillqr.dto;

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
	private String firstName;
	private String lastName;
	private String surname;
	private long contact;
	private String address;
	private String profession;
	private String experience;
	private String education;
	private String profilePhoto;
	private String website;
	private String fieldProjects;
	private Date businessEstDate;
	private String email;
}

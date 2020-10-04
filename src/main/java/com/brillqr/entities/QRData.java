package com.brillqr.entities;

import java.time.LocalDate;

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

@Table
@Entity(name="qr_data")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QRData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    
	@Column(name="qr_id")
	private String qrId;
	
    @Column(name= "user_code")
    private String userCode;
    
    @Column(name = "prefix")
	private String prefix;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;
    
    @Column(name="surname")
    private String surname;

    @Column(name="contact")
    private long contact;
    
    @Column(name="profession")
    private String profession;
    
    @Column(name="experience")
    private String experience;
    
    @Column(name="education")
    private String education;
    
    @Column(name="profile_photo")
    private String profilePhoto;
  
    @Column(name="website")
    private String website;
    
    @Column(name="field_projects")
    private String fieldProjects;
    
    @Column(name="business_established_date")
    private LocalDate businessEstDate;
    
    @Column(name="email")
    private String email;
    
    @Column(name="street_address")
	private String streetAddress;
    
    @Column(name="locality")
	private String locality;
    
    @Column(name="region")
	private String region;
    
    @Column(name="postal_code")
	private Long postalCode;
    
    @Column(name="country")
	private String country;
    
    @Column(name="isworkaddress")
	private boolean isWorkAddress;
    
    @Column(name="organization")
    private String organization;
}

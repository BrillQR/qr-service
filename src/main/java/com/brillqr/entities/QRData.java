package com.brillqr.entities;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
    
    @Column(name= "user_code")
    private String userCode;

    @NotNull
    @Column(name="full_name")
    private String fullName;

    @Column(name="country_code")
    private String countryCode;

    @NotNull
    @Column(name="contact")
    private long contact;

    @Column(name="email_id")
    @NotNull
    private String emailId;
}

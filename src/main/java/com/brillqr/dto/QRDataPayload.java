package com.brillqr.dto;

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

    private String userCode;
    private String fullName;
    private String countryCode;
    private long contact;
    private String emailId;

}

package com.brillqr.helper;

import com.brillqr.constant.QR_CONSTANT;
import com.brillqr.dto.QRDataPayload;
import com.brillqr.exceptions.VCFException;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.parameter.AddressType;
import ezvcard.parameter.EmailType;
import ezvcard.parameter.ImageType;
import ezvcard.parameter.TelephoneType;
import ezvcard.property.Address;
import ezvcard.property.Note;
import ezvcard.property.Photo;
import ezvcard.property.StructuredName;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;

public class VCFGenerator {
	private VCard vcard;
	private QRDataPayload qrDataPayload;

	public VCFGenerator() {

	}

	public VCFGenerator(QRDataPayload qrDataPayload) {
		this.qrDataPayload = qrDataPayload;
	}

	public String getVCFData() {
		vcard = new VCard();

		vcard.addLanguage("en-US");

		StructuredName n = new StructuredName();
		n.setFamily(qrDataPayload.getSurname());
		n.setGiven(qrDataPayload.getFirstName());
		n.getPrefixes().add(qrDataPayload.getPrefix());
		vcard.setStructuredName(n);

		vcard.setFormattedName(
				qrDataPayload.getFirstName() + " " + qrDataPayload.getLastName() + " " + qrDataPayload.getSurname());
		vcard.addTitle(qrDataPayload.getProfession());
		vcard.setOrganization(qrDataPayload.getOrganization(),"");

		Address adr = new Address();
		adr.setStreetAddress(qrDataPayload.getStreetAddress());
		adr.setLocality(qrDataPayload.getLocality());
		adr.setRegion(qrDataPayload.getRegion());
		
		if(qrDataPayload.getPostalCode()!=-1)
			adr.setPostalCode(String.valueOf(qrDataPayload.getPostalCode()));
		
		adr.setCountry(qrDataPayload.getCountry());
		adr.setLabel(getAddressLabel());

		if (qrDataPayload.isWorkAddress())
			adr.getTypes().add(AddressType.WORK);

		else
			adr.getTypes().add(AddressType.HOME);

		vcard.addAddress(adr);
		
		if(StringUtils.isNotBlank(qrDataPayload.getEmail()))
			vcard.addEmail(qrDataPayload.getEmail(), EmailType.WORK);
		
		
		if(qrDataPayload.getContact()!=-1)
			vcard.addTelephoneNumber(String.valueOf(qrDataPayload.getContact()), TelephoneType.WORK);
		
		
		vcard.addUrl(qrDataPayload.getWebsite());
		
		try {
		File photoPath = new File("C:\\Users\\Raj\\Downloads\\frame.png");
	    InputStream targetStream = new FileInputStream(photoPath);
		Photo photo = new Photo(targetStream, ImageType.JPEG);
		vcard.addPhoto(photo);
		}catch(IOException io) {
			io.printStackTrace();
		}
		
		Note note;
		String additionalNote="";
		if(StringUtils.isNotBlank(String.valueOf(qrDataPayload.getBusinessEstDate()))) {
			additionalNote+=QR_CONSTANT.BUSINESS_ETS_DATE +qrDataPayload.getBusinessEstDate()+"\n";
		}
		if(StringUtils.isNotBlank(qrDataPayload.getFieldProjects())) {
			additionalNote+=QR_CONSTANT.FIELD_PROJECTS +qrDataPayload.getFieldProjects()+"\n";
		}
		if(StringUtils.isNotBlank(qrDataPayload.getEducation())) {
			additionalNote+=QR_CONSTANT.EDUCATION +qrDataPayload.getEducation()+"\n";
		}
		if(StringUtils.isNotBlank(qrDataPayload.getExperience())) {
			additionalNote+=QR_CONSTANT.EXPERIENCE +qrDataPayload.getExperience();
			note = new Note(additionalNote);
			note.setLanguage("en");
			vcard.addNote(note);
		}
		
		try {
		File file = new File(QR_CONSTANT.LOCAL_STORAGE_PATH_VCF+qrDataPayload.getQrId()+QR_CONSTANT.VCF_EXTENSION);
		Ezvcard.write(vcard).go(file);
		}
		catch(IOException io) {
			throw new VCFException("Error While : getVCFData()");
		}
		return Ezvcard.write(vcard).version(VCardVersion.V3_0).go();
	}

	private String getAddressLabel() {
		StringBuffer sb = new StringBuffer();
		sb.append(qrDataPayload.getStreetAddress()).append(", ").append(qrDataPayload.getLocality()).append(", ")
				.append(qrDataPayload.getCountry());
		return sb.toString();
	}

}

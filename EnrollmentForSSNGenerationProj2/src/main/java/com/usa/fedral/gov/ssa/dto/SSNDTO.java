package com.usa.fedral.gov.ssa.dto;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Lob;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
/**
 * 
 * @author Mohammed
 * DTO class that is work as helper between the controller and service  
 */
@Data
public class SSNDTO implements Serializable {


	private Integer ssnNumber;
	private String  fname;
	private String lname;
	private Date dob;
	private String gender;
	private Long phone;
	private String state;
	private MultipartFile photo;
	private FileOutputStream photoDis;
	@Lob
	private byte[] photoByte;
	
}

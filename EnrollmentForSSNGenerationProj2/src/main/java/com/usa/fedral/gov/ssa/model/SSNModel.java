package com.usa.fedral.gov.ssa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * 
 * @author Mohammed
 * model class to capture the data from the form
 *
 */
@Data
public class SSNModel implements Serializable {
	
	private String ssnNumber;
	
	@NotBlank(message = "First name is reqiure")
	@Size(min = 5,max = 20 ,message = "First name must be between 5 to 20 charactor")
	private String  fname;
	@NotBlank(message = "Last name is reqiure ")
	@Size(min = 5,max = 20 ,message = "Last name must be between 5 to 20 charactor")
	private String lname;
	//@NotEmpty(message = "Date is reqiure")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	
	@NotEmpty(message = "Gender is reqiure")
	private String gender;
	private Long phone;
	@NotBlank(message = "State is reqiure")
	private String state;
	
	private MultipartFile photo;
	@Lob
	private byte[] photoByte;
	
}

package com.usa.fedral.gov.ssa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import lombok.Data;
@Entity
@Table(name="SSN_GENERATION2")
@Data
public class SSNEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "SEQ_SSN")
	@Column(name="SSNNUMBER",length = 20)
	private Integer ssnNumber;
	@Column(name="FNAME",length = 20)
	private String  fname;
	@Column(name = "LNAME",length = 20)
	private String lname;
	@Column(name = "DOB",length = 20)
	private Date dob;
	@Column(name = "GENDER",length = 20)
	private String gender;
	@Column(name = "PHONE",length = 20)
	private Long phone;
	@Column(name = "STATE",length = 20)
	private String state;
	@Column(name = "PHOTO")
	@Lob
	private byte[] photo;
	
	
}

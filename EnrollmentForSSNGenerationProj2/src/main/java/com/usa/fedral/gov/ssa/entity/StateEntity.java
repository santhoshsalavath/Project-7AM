package com.usa.fedral.gov.ssa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="STATE_MASTER")
@Data
public class StateEntity implements Serializable {
	
    @Id
	private Integer state_id;
    
	private String state_cd;
	
	private String state_name;
}

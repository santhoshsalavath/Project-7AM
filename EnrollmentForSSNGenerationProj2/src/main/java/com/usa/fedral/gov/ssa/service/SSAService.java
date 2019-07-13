package com.usa.fedral.gov.ssa.service;

import java.io.IOException;
import java.util.List;

import com.usa.fedral.gov.ssa.dto.SSNDTO;

public interface SSAService {

	public String saveSSN(SSNDTO ssnDTO)throws IOException;
	public List<String> getStates();
	public List<SSNDTO> findAllSSN();
	public String validateSSN(Integer id);
	public SSNDTO findById(Integer id);
}

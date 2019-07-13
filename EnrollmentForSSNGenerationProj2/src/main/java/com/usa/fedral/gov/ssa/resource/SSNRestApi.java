package com.usa.fedral.gov.ssa.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.usa.fedral.gov.ssa.service.SSAService;
import com.usa.fedral.gov.ssa.util.Constant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 
 * @author Mohammed
 * Rest Api to validate the SSN 
 * 
 */
@RestController
@Api(value="Rest API to validate SSN ",description = "Rest API For Validate SSN ")
public class SSNRestApi {
	
	// configure service
	@Autowired
	private SSAService ssaService;
	//configure Constant
	@Autowired
	private Constant constant;
	
	/**
	 * 
	 * @param Id
	 * @return Message the ssn is validate or not
	 * and also if is belong to Washington state or not
	 */
		
		@GetMapping("/validateSSN/{id}")
		@ApiOperation(value = " validate SSN And State By Id" ,produces ="application/json")
		public String validateSSN(@PathVariable("id")Integer id) {
			//use service
			String msg=ssaService.validateSSN(id);
			return msg;
		}
	}
	

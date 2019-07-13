package com.usa.fedral.gov.ssa.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.usa.fedral.gov.ssa.dto.SSNDTO;
import com.usa.fedral.gov.ssa.service.SSAService;
import com.usa.fedral.gov.ssa.util.Constant;
/**
 * 
 * @author Mohammed
 * used to perform unit testing for our service class methods
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EnrollmentForSsnGenerationProjApplicationTests {

	/**
	 * configure service class
	 */
	@Autowired
	private  SSAService service;
	/**
	 * configure constant
	 */
	@Autowired
	private Constant constant;
	
	/**
	 * method to execute in the beginning of execution   
	 */
	@BeforeClass
	public static void beforeTest() {
		System.out.println("SSAServiceTest.beforeTest()");
	}

	/**
	 * method to test getAllState Of Service class
	 * 1- check if it is not null
	 * 2- check the size of list 
	 */
	@Test
	public void test_getStates() {
		List<String> states=service.getStates();
		assertNotNull(states);
		assertEquals(52, states.size());
	}	
	
	/**
	 * Method to test service class method findAllSSN
	 * 1- check if it is not null using assertNotNull
	 * 2- check how many time is take to execute if it is take more than 2 second display failure 
	 * 3- 
	 */
	@Test(timeout = 2000)
	public void test_findAllSSN() {
		List<SSNDTO> listDTO=service.findAllSSN();
		assertNotNull(listDTO);
	}
	
	/**
	 * Method to test service class method validate SSN by the id
	 * 1-check not null
	 * 1- check it with valid id to check the message
	 * 2- check it with not valid number and check the message
	 */
	@Test
	public void test_validateSSN() {
		String actualMsg=service.validateSSN(900000023);
		String expectedMsg= constant.properties.get("IdAndStateSuccess");
		assertNotNull(actualMsg);
		assertEquals(expectedMsg, actualMsg);
		
	}
	
}

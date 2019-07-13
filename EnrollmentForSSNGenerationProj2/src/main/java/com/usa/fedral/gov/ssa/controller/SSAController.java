package com.usa.fedral.gov.ssa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.usa.fedral.gov.ssa.dto.SSNDTO;
import com.usa.fedral.gov.ssa.model.SSNModel;
import com.usa.fedral.gov.ssa.service.SSAService;
import com.usa.fedral.gov.ssa.util.Constant;

/**
 * 
 * @author Mohammed
 * controller class to handle all the user request
 */
@Controller
public class SSAController {

	/**
	 * configure logger
	 */
	 private static final Logger LOGGER = LoggerFactory.getLogger(SSAController.class);
	
	
	/**
	 * configure service class
	 */
	@Autowired
	private SSAService ssaService;
	
	/**
	 * configure constant class
	 */
	@Autowired
	private Constant constant;
	
	/**
	 *  handling form displaying request method
	 */
	@GetMapping(value = "/showForm")
	public String showForm(Map map, @ModelAttribute("model")SSNModel model) {
	
		LOGGER.debug("/** add initial data into the form **/");
	       initialFormData(map);
	       LOGGER.debug("/** Displaing The FormRegistration Page **/");
		return "formRegister";
	}
	
	
	/**
	 * method : to handle form submittion and pass to service class  
	 * @throws IOException
	 * check the form data it is valid or not
	 * convert from model to DTO
	 * use service 
	 */
	@PostMapping(value = "/saveUser")
	public String formSudmit(Map map,RedirectAttributes attribute, @ModelAttribute("model") @Valid SSNModel model,BindingResult result) throws IOException {
	
		LOGGER.debug(" ** FormSubmit() is started ** ");
		if(result.hasErrors()) {
			LOGGER.info("** Their is Validation Error In The Form ** ");		
			 initialFormData(map);
			 LOGGER.info(" ** Displaing The FormRegistration Page With Erorr Validation ** ");
			return "formRegister";
		} 
		
		LOGGER.info(" ** Copy The Data From Model To DTO  And Call Service Class  ** ");
		SSNDTO ssnDTO=new SSNDTO();
		BeanUtils.copyProperties(model, ssnDTO);
		try {
		String msg=ssaService.saveSSN(ssnDTO);
		
		attribute.addFlashAttribute("resultMsg", msg);
		}
		catch(Exception e) {
		LOGGER.error(" ** The Form is Not Register  ** ");	
		}
		LOGGER.debug(" ** FormSubmit() is ended ** ");
		LOGGER.info(" ** Redirect  The Request To Get Request To Avoid Double Posting Problem **");
		return "redirect:/showForm";
	}
	/**
	 * method to get all the SSN 
	 */
	@GetMapping("/displayAllSSN")
	public String getAllSSN(Map map,HttpServletRequest req,HttpServletResponse res) {
		
		try {
			LOGGER.debug(" ** getAllSSN() is started ** ");
		LOGGER.info(" ** Use Service to Get All the Records From The Data Base ** ");
		List<SSNDTO> listDTO=ssaService.findAllSSN();
		List<SSNModel> listModel=new ArrayList<SSNModel>();
		listDTO.forEach(ssnDTO->{
			LOGGER.info(" ** Copy The Data From DTO into Model  ** ");
			SSNModel ssnModel=new SSNModel();
			BeanUtils.copyProperties(ssnDTO, ssnModel);
			ssnModel.setSsnNumber(ssnDTO.getSsnNumber().toString());
			listModel.add(ssnModel);
		});
		LOGGER.info(" ** Put The Result in The Model to be send to the browser **");
		map.put("SSNDetails",listModel);
		LOGGER.debug(" ** getAllSSN() is ended ** ");
		}
		catch(Exception e) {
			LOGGER.error(" ** Error While Retriving The All SSN From Service to Controller  ** ");	
		}
		return "displayAllSSN1";
	}
	
	/**
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws NullPointerException
	 * Method that is used to display the image 
	 */
	@GetMapping(value="/displayImage")
	public String displayImage(HttpServletResponse response,HttpServletRequest request) 
		       {
		
		       LOGGER.debug(" ** displayImage() is started ** ");	
		       LOGGER.info(" ** get the name of image using the id  ** ");
               final String ssnId=request.getParameter("id");
               final int  id=Integer.parseInt(ssnId);
               LOGGER.info(" ** get the complete file name **");
                String fileName=ssnId.toString()+".png";
                LOGGER.info(" ** create file pointing to the complete destination **");
                
                 File imageFile = new File(constant.properties.get("destDownloadFile")+"/"+fileName);
		        LOGGER.info("** Set the Response Type **");
		        response.setContentType("image/jpeg");
		        try {
		       LOGGER.debug("** create file input stream pointing to the file and copy from inputStram into outPutStream  **");	
		       if(imageFile!=null) { 
		      final InputStream inPut=new FileInputStream(imageFile);
		        // copy from input to output response
		        ServletOutputStream so= response.getOutputStream();
		        IOUtils.copy(inPut, so);
		        //close input stream
		        inPut.close();
		        response.flushBuffer();
		        so.close();
		        so.flush();
		        }
		       else 
		    	   LOGGER.warn(" ** File is not found ** ");  
		       
		        }
		        catch(IOException ie) {
		        	LOGGER.error(" ** IOException is rised in the method ** ");
		        }
		        LOGGER.debug(" ** displayImage() is ended ** ");
		        return "displayImage";
		       }
		       
	/**
	 * method to add initial data into the form
	 */
	public void initialFormData(Map map) {
		LOGGER.debug(" ** initialFormData() is started ** ");
		 String [] genders=new String[]{"male","female"};
		 LOGGER.info(" ** call service to get all states ** ");
		List<String> states=ssaService.getStates();
		if(states!=null) {
			LOGGER.info(" ** pass the result to browser by using  model ** ");
			map.put("genders",genders);
		    map.put("states", states);
		    LOGGER.debug(" ** getStates() is ended ** ");
		}
		else {
			LOGGER.warn(" ** states not found in data base ** ");
		}
		
		
	}
	@RequestMapping(value="/login")
	public String showLoginPage() {
		return "home";
	}
}

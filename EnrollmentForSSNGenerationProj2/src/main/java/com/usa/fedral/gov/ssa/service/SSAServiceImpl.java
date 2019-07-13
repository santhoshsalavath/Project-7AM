package com.usa.fedral.gov.ssa.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.usa.fedral.gov.ssa.dto.SSNDTO;
import com.usa.fedral.gov.ssa.entity.SSNEntity;
import com.usa.fedral.gov.ssa.exception.NoRecordsFoundException;
import com.usa.fedral.gov.ssa.exception.SSNNotFoundException;
import com.usa.fedral.gov.ssa.exception.StateNotFoundException;
import com.usa.fedral.gov.ssa.exception.UserNotStoreException;
import com.usa.fedral.gov.ssa.repository.SSNMasterRepository;
import com.usa.fedral.gov.ssa.repository.StateMasterRepository;
import com.usa.fedral.gov.ssa.util.Constant;
/**
 * 
 * @author Mohammed
 * service class to write and perform business logic
 *
 */
@Service
public class SSAServiceImpl implements SSAService {

	// configure SSN repository
	@Autowired
	private SSNMasterRepository SSNRepo;
	// configure constant to get the data from properties
	@Autowired
	private Constant constant;
	// configure state repository
	@Autowired
	private StateMasterRepository stateRepo;
	
	/**
	 * method : to save given SSN
	 * @throws IOException 
	 */
	@Override
	public String saveSSN(SSNDTO ssnDTO) throws IOException {
		SSNEntity ssnEntity=null;
		// convert from DTO to Entity
		ssnEntity=new SSNEntity();
		
		BeanUtils.copyProperties(ssnDTO, ssnEntity);
		// get the file uploaded 
		MultipartFile file=ssnDTO.getPhoto();
		// pass the file in the form of bytes into SSN Entity
          ssnEntity.setPhoto(file.getBytes());
          System.out.println(ssnEntity.getPhoto());
		// use Repository
		ssnEntity=SSNRepo.save(ssnEntity);
		System.out.println(ssnEntity);
		String msg=null;
		//return message based on operation
		if(ssnEntity !=null) {
			msg=constant.properties.get("successInsertion");
		}
		else {
			throw new UserNotStoreException(constant.properties.get("failureInsertion"));
			
		}
		
		return msg;
	}
	
	/**
	 * method : to get all the states Names from the data base
	 */
	@Override
	public List<String> getStates() {
		// use repository
		List<String[]> statesList=stateRepo.findStatesNames();
		List<String> states=new ArrayList<String>(statesList.size());
		//iterate the list
		statesList.forEach(row->{
			for(String state:row) {
		String stateName=state;
		states.add(stateName);	
			}
		});
	
		if(states.size()!=0) {
			return states;
			}
			else
			throw new StateNotFoundException(constant.properties.get("statesFaliure"));	
	}
	
	/**
	 *  mehtod to get all SSN from data base
	 *  
	 */
	@Override
	public List<SSNDTO> findAllSSN() {
		// use repository
		List<SSNEntity> listEntity=SSNRepo.findAll();
		System.out.println(listEntity);
		// convert from entity into DTO
		List<SSNDTO> listDTO=new ArrayList<SSNDTO>(listEntity.size());
		listEntity.forEach(ssnEntity->{
			SSNDTO ssnDTO=new SSNDTO();
		
			BeanUtils.copyProperties(ssnEntity, ssnDTO);
			ssnDTO.setPhotoByte(ssnEntity.getPhoto());
			if(ssnEntity.getPhoto()!=null) {
			/**
			 * get the bytes from data base and converted into file and store it into the file system
			 * by taking id as name of the file	
			 */
			try {
			// get destination
			String fileName=ssnEntity.getSsnNumber().toString();
			String fileDest=constant.properties.get("destDownloadFile");
			fileDest=fileDest+"/"+fileName+".png";
			System.out.println(fileDest);
			// create file having the destination
			File file=new File(fileDest);
			// pass the file into the output stream
			FileOutputStream os=new FileOutputStream(file);
			// get the bytes from the data base
			byte[] bytes=ssnEntity.getPhoto();
			// write the file into the file output stream
			os.write(bytes);
			os.flush();
			os.close();
			ssnDTO.setPhotoDis(os);
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			}//if
			listDTO.add(ssnDTO);
		});// for each
		if(listDTO.size()!=0)
		return listDTO;
		else
			throw new NoRecordsFoundException(constant.properties.get("tableEmpty"));
	}//method
	/**
	 * method to validate SSN and state
	 */
	@Override
	public String validateSSN(Integer id) {
		String msg=null;
		// use repository
		try {
		SSNEntity entity= SSNRepo.findById(id).get();
		// check the id 
		if(entity!=null) {
			// check the state 
			if(entity.getState().equalsIgnoreCase("Washington")) {
				 return constant.properties.get("IdAndStateSuccess");
			}
			else {
			   return constant.properties.get("stateFaliure");
			}
			}
		}
		catch(Exception e) {
			throw new SSNNotFoundException(constant.properties.get("ssnFailure"));
		}
		throw new SSNNotFoundException(constant.properties.get("ssnFailure"));

	}
	@Override
	public SSNDTO findById(Integer id) {
		
		// use repository
		SSNEntity entity=SSNRepo.findById(id).get();
		// convert from entity to dto
		SSNDTO dto=new SSNDTO();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}
} // class
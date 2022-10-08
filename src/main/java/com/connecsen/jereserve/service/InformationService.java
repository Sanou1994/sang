package com.connecsen.jereserve.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.connecsen.jereserve.domaine.Information;
import com.connecsen.jereserve.domaine.Reponse;
import com.connecsen.jereserve.repository.InformationRepository;
import com.connecsen.jereserve.utils.Utility;
@Service @Transactional
public class InformationService implements IInformationService{
	private InformationRepository informationRepository;

 	public InformationService(InformationRepository informationRepository) {
		super();
		this.informationRepository = informationRepository;
		
	}
	@Override
	public Reponse getInformationById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reponse getAllInformations() {
		Reponse response = new Reponse();

		try
		{
			List<Information> infos =informationRepository.findAll();
			response.setCode(200);
			response.setMessage(Utility.CodeAndMessage().get(200));
			response.setResult(infos);
		}
		catch(Exception e)
		{
	    	response.setCode(500);
			response.setMessage(Utility.CodeAndMessage().get(500));
			response.setResult(null);
		}
		// TODO Auto-generated method stub
		
		return response;
	}

	@Override
	public boolean changerStatusInformation(long id) {
		// TODO Auto-ngenerated method stub
		return false;
	}

	@Override
	public Reponse addInformation(Information information) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		Date date = new Date();  
		Information info =null;
		Reponse response  = new Reponse();
		information.setDateInformation(formatter.format(date).toString());
		information.setStatusOfInformation(false);
		try
		{
			info = informationRepository.save(information);
			
			if(!(info == null))
			{ 					
				response.setCode(201);
				response.setMessage(Utility.CodeAndMessage().get(201));
				response.setResult(info);			
				
			}	
			
		}
		catch( Exception ex)
		{
			
			response.setCode(500);
			response.setMessage(Utility.CodeAndMessage().get(500));
			response.setResult(null);
			
		
		}		
		return response;
	}

}

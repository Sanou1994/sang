package com.connecsen.jereserve.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.connecsen.jereserve.domaine.Partenaire;
import com.connecsen.jereserve.domaine.Reponse;
import com.connecsen.jereserve.repository.PartenaireRepository;
import com.connecsen.jereserve.utils.Utility;
@Service @Transactional
public class PartenaireService implements IPartenaireService{
	private PartenaireRepository partenaireRepository;
	

	public PartenaireService(PartenaireRepository partenaireRepository) {
		super();
		this.partenaireRepository = partenaireRepository;
	}

	@Override
	public Reponse addPartenaire(Partenaire partenaire) {
		// TODO Auto-generated method stub
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
				Date date = new Date();  
				Partenaire partenaireCreate =null;
				Reponse response = null;
				partenaire.setDate_creation(formatter.format(date).toString());
				partenaire.setStatus(true);
				try
				{
					if(partenaire.getPartenaireID() > 0)
					{
						Partenaire partenaireUpdate = partenaireRepository.findById(partenaire.getPartenaireID()).get();					
						partenaireUpdate.setAdresse(partenaire.getAdresse());
						partenaireUpdate.setEmail(partenaire.getEmail());
						partenaireUpdate.setName(partenaire.getName());
						partenaireUpdate.setPhone(partenaire.getPhone());
						partenaireUpdate.setStatus(true);
						partenaireCreate = partenaireRepository.save(partenaireUpdate);					
						response = new Reponse();
						response.setCode(200);
						response.setMessage(Utility.CodeAndMessage().get(200));
						response.setResult(partenaireCreate);	
						
					}
					else
					{
						partenaireCreate = partenaireRepository.save(partenaire);					
						response = new Reponse();
						response.setCode(200);
						response.setMessage(Utility.CodeAndMessage().get(200));
						response.setResult(partenaireCreate);	
					}
					
					
				}
				catch( Exception ex)
				{
					response = new Reponse();
					response.setCode(500);
					response.setMessage(Utility.CodeAndMessage().get(500));
					response.setResult(null);				
				}		
				return response;
	}

	@Override
	public Reponse getPartenaireById(long id) {
		// TODO Auto-generated method stub
		Reponse response = null;
		try
		{
			Partenaire part = partenaireRepository.findById(id).get();
			
			if(part!= null)
			{
				response = new Reponse();
				response.setCode(200);
				response.setMessage(Utility.CodeAndMessage().get(200));
				response.setResult(part);	
			}
			else
			{
				response = new Reponse();
				response.setCode(205);
				response.setMessage(Utility.CodeAndMessage().get(205));
				response.setResult(null);	
			}
			
			
			
		}
		catch(Exception ex )
		{
			response = new Reponse();
			response.setCode(500);
			response.setMessage(Utility.CodeAndMessage().get(500));
			response.setResult(null);
			
		}
		return response;
	}

	@Override
	public Reponse getAllPartenaires() {
		Reponse response = null;
		try
		{
			List<Partenaire> partenaires = partenaireRepository.findAll();					
			response = new Reponse();
			response.setCode(200);
			response.setMessage(Utility.CodeAndMessage().get(200));
			response.setResult(partenaires);	
		}
		catch(Exception ex )
		{
			response = new Reponse();
			response.setCode(500);
			response.setMessage(Utility.CodeAndMessage().get(500));
			response.setResult(null);
		}
		return response;
	}

	@Override
	public Reponse changerStatusPartenaire(long id) {
		// TODO Auto-generated method stub
				Reponse response = null;
				try
				{
					Partenaire part = partenaireRepository.findById(id).get();
					
					if(part!= null)
					{
						if(part.isStatus() == true)
						{
							part.setStatus(false);
	
						}
						else
						{
							part.setStatus(true);

						}
						Partenaire partUpdate = partenaireRepository.save(part);
						response = new Reponse();
						response.setCode(200);
						response.setMessage(Utility.CodeAndMessage().get(200));
						response.setResult(partUpdate);	
					}
					else
					{
						response = new Reponse();
						response.setCode(205);
						response.setMessage(Utility.CodeAndMessage().get(205));
						response.setResult(null);	
					}
					
					
					
				}
				catch(Exception ex )
				{
					response = new Reponse();
					response.setCode(500);
					response.setMessage(Utility.CodeAndMessage().get(500));
					response.setResult(null);
					
				}
				return response;
	}

}

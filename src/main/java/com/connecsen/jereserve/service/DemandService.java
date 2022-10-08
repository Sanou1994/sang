package com.connecsen.jereserve.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.connecsen.jereserve.domaine.Code;
import com.connecsen.jereserve.domaine.Demande;
import com.connecsen.jereserve.domaine.OutboundSMSMessageRequest;
import com.connecsen.jereserve.domaine.OutboundSMSTextMessage;
import com.connecsen.jereserve.domaine.Partenaire;
import com.connecsen.jereserve.domaine.PartenaireTempon;
import com.connecsen.jereserve.domaine.Reponse;
import com.connecsen.jereserve.domaine.SmsMessage;
import com.connecsen.jereserve.domaine.Utilisateur;
import com.connecsen.jereserve.repository.CodeRepository;
import com.connecsen.jereserve.repository.DemandeRepository;
import com.connecsen.jereserve.repository.PartenaireRepository;
import com.connecsen.jereserve.repository.UserRepository;
import com.connecsen.jereserve.utils.Utility;
@Service
@Transactional
public class DemandService implements IDemandeService{
	@Autowired
	private DemandeRepository demandRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private IAccountService iAccountService;
	@Autowired
	private CodeRepository codeRepository;
	@Autowired
	private PartenaireRepository partenaireRepository;

    private SmsService smsService = new SmsService();

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
 	public DemandService(DemandeRepository demandRepository,
 			IAccountService iAccountService,
 			PartenaireRepository partenaireRepository,
            UserRepository userRepository,
            CodeRepository codeRepository) {
		super();
		this.iAccountService=iAccountService;
		this.demandRepository = demandRepository;
		this.partenaireRepository = partenaireRepository;
		this.userRepository = userRepository;
		this.codeRepository = codeRepository;

	}

	@Override
	public Reponse lancerDemande(Demande demande) {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		Date date = new Date();  
		Demande demand =null;
		Reponse response = null;
		Utilisateur user =null;
		demande.setDate_demande(formatter.format(date).toString());
		demande.setStatus(false);
		try
		{
			demand = demandRepository.save(demande);
			
			if(!(demand == null))
			{ 
				
				Utilisateur userDeleted = userRepository.findByPhone(demand.getPhone());
				if( !(userDeleted == null) && userDeleted.isStatus() == false)
				{
					userRepository.delete(userDeleted);
				}
				
				Utilisateur userSave = new Utilisateur();
				userSave.setNom(demand.getName());
				userSave.setUsername(demand.getEmail());
				userSave.setPrenom(demand.getLastName());
				userSave.setPassword(bCryptPasswordEncoder.encode(Long.toString(demand.getPhone())));
				userSave.setPhone(demand.getPhone());
				userSave.setEmail(demand.getEmail());
				userSave.setPays(demand.getCountry());
				userSave.setVille(demand.getCity());
				userSave.setStatus(false);
				userSave.setRole( "ABONNE");	
				userSave.getDemandes().add(demand);
				user=userRepository.save(userSave);	
				
				if(!(user == null))
				{
					
					Code codeSave = new Code();
					codeSave.setCode(new Date().getTime()+user.getPhone());
					codeSave.setDemandID(demand.getDemandID());
					codeSave.setPhone(user.getPhone());
					codeSave.setStatus(true);
					codeSave.setUserID(user.getId());
					codeRepository.save(codeSave);
					user.getCodes().add(codeRepository.save(codeSave));
					Utilisateur userCreated=userRepository.save(user);
					String contentMessageClient="Bonjour Monsieur/Madame:"+userCreated.getNom()+"\n" + 
	                          "Type demande : "+demand.getTypeOfDemand()+"\n" + 
	                          "Groupe sanguin demandé/donné : "+demand.getGroupeSanguin()+"\n" + 
	                          "Téléphone: "+ userCreated.getPhone()+"\n" + 
	                          "Date: "+demand.getDate_demande()+"\n" + 
	                          "Veuillez saisir les identifiants ci-dessous pour activer votre demande "+"\n" + 
	                          "Code :"+codeSave.getCode()+"\n" + 
	                          "Téléphone :"+userCreated.getPhone()+"\n" + 
	                          "Lien d'activation : https://je-reserve.net/auth/activation";						
					OutboundSMSTextMessage outboundSMSTextMessageClient= new OutboundSMSTextMessage(contentMessageClient);
					OutboundSMSMessageRequest outboundSMSMessageRequestClient = new OutboundSMSMessageRequest("221" + userCreated.getPhone(),outboundSMSTextMessageClient,"221" + userCreated.getPhone(),"LAYDOU"); 
					SmsMessage smsMessageClient = new SmsMessage(outboundSMSMessageRequestClient);					
					Reponse smsReponse =smsService.sendMessage(smsMessageClient); 		
					response = new Reponse();
					if(smsReponse.getCode() == 200)
	                {
						response.setCode(200);
						response.setMessage(Utility.CodeAndMessage().get(200));
						response.setResult(demand);
	                }
	                else
	                {
	                	throw new Exception("Error to send sms ");
	                }	
	                
					
				}
			}	
			
		}
		catch( Exception ex)
		{
			response = new Reponse();
			response.setCode(500);
			response.setMessage(Utility.CodeAndMessage().get(500));
			response.setResult(null);
			
			if(!(demand == null))
			{
				if(user == null)
				{
					demandRepository.delete(demand);
					
				}
				else
				{
					demandRepository.delete(demand);
					userRepository.delete(user);
				}
			}
		
		}		
		return response;
	}

	@Override
	public Reponse getDemandeById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reponse getAllDemandes() {
		Reponse response = new Reponse();

		try
		{
			List<Demande> demandes=demandRepository.findAll();
			response.setCode(200);
			response.setMessage(Utility.CodeAndMessage().get(200));
			response.setResult(demandes);
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
	public boolean changerStatusDemande(long id) {
		// TODO Auto-ngenerated method stub
		return false;
	}

	@Override
	public Reponse addDemandToUser(long idUser, Demande demande) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		Date date = new Date();  
		Demande demand =null;
		Reponse response  = new Reponse();
		demande.setDate_demande(formatter.format(date).toString());
		demande.setStatus(true);
		try
		{

			demand = demandRepository.save(demande);			
			if(!(demand == null))
			{ 						
				Utilisateur	user = userRepository.findById(idUser).get();					
				user.getDemandes().add(demand);
				userRepository.save(user);	
				demand.setActivedJob(true);
				demand = demandRepository.save(demande);		
				response.setCode(201);
				response.setMessage(Utility.CodeAndMessage().get(201));
				response.setResult(demand);			
				
			}	
			
		}
		catch( Exception ex)
		{
			
			response.setCode(500);
			response.setMessage(Utility.CodeAndMessage().get(500));
			response.setResult(null);
			
			if(!(demand == null))
			{			
		    demandRepository.delete(demand);				
				
			}
		
		}		
		return response;
	}

	@Override
	public Reponse addDemandToPartenaire(PartenaireTempon partenaireTempon) {
		// TODO Auto-generated method stub
		Reponse response  = new Reponse();
		try
		{
			Partenaire	partenaireGot = partenaireRepository.findById(partenaireTempon.getIdPartenaire()).get();				
			if(!(partenaireGot == null))
			{ 
				
				Demande	demandeGot = demandRepository.findById(partenaireTempon.getIdDemande()).get();				

				if(!(demandeGot == null))
				{ 
					demandeGot.setPartenaireID(partenaireTempon.getIdPartenaire());
					demandeGot.setStatus(false);
					Demande de=demandRepository.save(demandeGot);
					partenaireGot.getDemandes().add(de);
					Partenaire np =partenaireRepository.save(partenaireGot);
					response.setCode(201);
					response.setMessage(Utility.CodeAndMessage().get(201));
					response.setResult(np);	
					
				}
				else
				{
					response.setCode(202);
					response.setMessage(Utility.CodeAndMessage().get(202));
					response.setResult(null);
				}
				
				
			}
			else
			{
				response.setCode(202);
				response.setMessage(Utility.CodeAndMessage().get(202));
				response.setResult(null);
			}
			
		}
		catch( Exception ex)
		{
			
			response.setCode(500);
			response.setMessage(Utility.CodeAndMessage().get(500));
			response.setResult(null);		
		
		
		}		
		return response;	}
	@Override
	public Reponse sendMessageForAllUsers(Demande demand)
	{		
		// TODO Auto-generated method stub
				Reponse response = new Reponse();
				int nbr_receiver=0;
				long total_users=0;
				boolean result =false;
				try
				{

					List<Utilisateur> users = iAccountService.getAllUsersByStatusAndLocked(true, false);
					total_users=users.size();
					if( total_users > 0)
					{ 

						for(int j=0;j<total_users;j++ )
						{
							String contentMessageClient="";
							Utilisateur userGot=users.get(j);
							switch(demand.getTypeOfDemand()) {
							  case "RECEVEUR":
								  contentMessageClient="Bonjour Monsieur/Madame:"+userGot.getNom()+"\n\n" + 
				                          "J'espère que vous vous portez bien ? \n" + 
				                          "SAUVE-MOI ! , j'ai besoin en URGENCE DE SANG : "+demand.getGroupeSanguin()+" à "+demand.getNameOfCenter()+"\n" + 
				                          "Votre contribution me permettra d'espérer améliorer ma santé."+"\n" +
				                          "Je suis joinganble sur le "+demand.getPhone()+"\n\n" + 
				                          "SAUVER UNE VIE EST MERVEILLEUX.DONNEZ DU SANG,DONNEZ LA VIE!\n";														
							    break;
							    
							    
							    
							  case "COLLECTE":
								  contentMessageClient="Bonjour Monsieur/Madame:"+userGot.getNom()+"\n\n" + 
				                          "J'espère que vous vous portez bien ? \n" + 
				                          "Nous vous informons que la structure "+demand.getName_of_structure()+" organise une activité de collecte de sang \n" + 
				                          "Votre participation , nous permettra, ensemble de sauver des vies .\n" +
				                          "Ci-dessous les informations supplémentaires : \n" + 
				                          "Contact :"+demand.getPhone()+" \n" + 
				                          "Date  : "+demand.getDate_debut()+" \t-\t "+demand.getDate_fint()+"\n" + 
				                          "Heure   :"+demand.getHeure_debut()+" \t-\t "+demand.getHeure_fin()+" \n" +
				                          "SAUVER UNE VIE EST MERVEILLEUX.DONNEZ DU SANG,DONNEZ LA VIE!\n";									
								    break;
							  
							}
							
							
							
							OutboundSMSTextMessage outboundSMSTextMessageClient= new OutboundSMSTextMessage(contentMessageClient);
							OutboundSMSMessageRequest outboundSMSMessageRequestClient = new OutboundSMSMessageRequest("221" + userGot.getPhone(),outboundSMSTextMessageClient,"221" + userGot.getPhone(),"LAYDOU"); 
							SmsMessage smsMessageClient = new SmsMessage(outboundSMSMessageRequestClient);					
							Reponse smsReponse =smsService.sendMessage(smsMessageClient); 	

							if(smsReponse.getCode() == 200)
			                {

								demand.setMessageSendState(true);
								demandRepository.save(demand);
								nbr_receiver++;
			                }
			                else
			                {

			                	continue;

			                }			                
							
						}
						result=true;
						
					}

					response.setCode(200);
					response.setMessage( (nbr_receiver*100)/total_users +"%"+ " de personnes ont reçu votre demande !");
					response.setResult(result);				
								
				}
				catch( Exception ex)
				{
					response.setCode(500);
					response.setMessage(Utility.CodeAndMessage().get(500));
					response.setResult(true);			
				
				}
				return response;
      
	}
						
	@Override
	public Reponse sendMessageForAllStructures(Demande demand) {
		// TODO Auto-generated method stub
		Reponse response = new Reponse();
		String  contentMessageClient="";
		long total_structures=0;
		boolean result =false;

		try
		{
			
			List<Partenaire> partenaires = partenaireRepository.findByStatusAndLocked(true,false);
			total_structures=partenaires.size();
			if( total_structures > 0)
			{ 
				for(int j=0;j<total_structures;j++ )
				{
					Partenaire partenaireGot=partenaires.get(j);					
				    contentMessageClient="Bonjour Monsieur/Madame \n\n" + 
		                          "J'espère que vous vous portez bien ? \n" + 
		                          "J'aimerais donner mon sang mon groupe sanguin est  : "+demand.getGroupeSanguin()+"\n" + 
		                          "Je suis joinganble sur le "+demand.getPhone()+"\n\n" + 
		                          "Merci de recevoir ma demande !\n";											
			
				    OutboundSMSTextMessage outboundSMSTextMessageClient= new OutboundSMSTextMessage(contentMessageClient);
					OutboundSMSMessageRequest outboundSMSMessageRequestClient = new OutboundSMSMessageRequest("221" + partenaireGot.getPhone(),outboundSMSTextMessageClient,"221" + partenaireGot.getPhone(),"LAYDOU"); 
					SmsMessage smsMessageClient = new SmsMessage(outboundSMSMessageRequestClient);					
					Reponse smsReponse =smsService.sendMessage(smsMessageClient);

					if(smsReponse.getCode() == 200)
	                {
						demand.setMessageSendState(true);
						demandRepository.save(demand);
	                }
	                else
	                {
	                	continue;

	                }					
					
					
			}
				result=true;
				
			}
			
			
			
			
			
			response.setCode(200);
			response.setMessage("votre  demande a été envoyée !");
			response.setResult(result);				
						
		}
		catch( Exception ex)
		{
			response.setCode(500);
			response.setMessage(Utility.CodeAndMessage().get(500));
			response.setResult(true);			
		
		
		}		
		return response;
	}


	@Override
	public List<Demande>  getDemandeByStatusAndLockedAndMessageSendStateAndActivedJob() {
		// TODO Auto-generated method stub
		
			List<Demande> demandes= demandRepository.findByStatusAndLockedAndMessageSendStateAndActivedJob(true, false, false, true);		
		return demandes;
	}
	
}




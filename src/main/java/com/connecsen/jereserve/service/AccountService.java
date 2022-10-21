package com.connecsen.jereserve.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.connecsen.jereserve.domaine.Code;
import com.connecsen.jereserve.domaine.Demande;
import com.connecsen.jereserve.domaine.Login;
import com.connecsen.jereserve.domaine.OutboundSMSMessageRequest;
import com.connecsen.jereserve.domaine.OutboundSMSTextMessage;
import com.connecsen.jereserve.domaine.Partenaire;
import com.connecsen.jereserve.domaine.Reponse;
import com.connecsen.jereserve.domaine.SmsMessage;
import com.connecsen.jereserve.domaine.Utilisateur;
import com.connecsen.jereserve.repository.CodeRepository;
import com.connecsen.jereserve.repository.DemandeRepository;
import com.connecsen.jereserve.repository.PartenaireRepository;
import com.connecsen.jereserve.repository.UserRepository;
import com.connecsen.jereserve.security.JwtTokenUtil;
import com.connecsen.jereserve.utils.Utility;

@Service @Transactional
public class AccountService implements IAccountService{
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;	
	@Autowired
	private UserDetailsService userDetailsService;
    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DemandeRepository demandRepository;
    @Autowired
    private PartenaireRepository partenaireRepository;
    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private SmsService smsService;
    
	@Override
	public Utilisateur login_up(Utilisateur user) {
		Utilisateur userGot = userRepository.findByPhone(user.getPhone());
		if(userGot == null) {
			String pwdCryp = bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(pwdCryp);
			 userRepository.save(user);
		} else 
		{
			
		}
		
		return userGot ;
	}
	@Override 
	public Reponse se_connecter(Login login ) {
		Reponse response = new Reponse();
		Code codeSaveOK=null;
		Utilisateur userConnected =null;
		try
		{
			Utilisateur user = userRepository.findByEmailAndPhone(login.getEmail(),login.getPhone());
			if((user != null)&&(user.isStatus())&&(bCryptPasswordEncoder.matches(Long.toString(login.getPhone()), user.getPassword())))
			{

				Code codeSave = new Code();
				codeSave.setCode(new Random().ints(1, 9999).findFirst().getAsInt());
				codeSave.setPhone(user.getPhone());
				codeSave.setStatus(true);
				codeSave.setUserID(user.getId());
				codeSaveOK=codeRepository.save(codeSave);		
				user.getCodes().add(codeRepository.save(codeSaveOK));			
				userConnected =userRepository.save(user);
				String contentMessageClient=" Bonjour Monsieur/Madame:"+userConnected.getNom()+"\n" + 
                        "Téléphone: "+userConnected.getPhone()+"\n" +
                        "Email: "+userConnected.getPhone()+"\n" + 
                        "Veuillez saisir le code  ci-dessous pour vous connectez "+"\n" + 
                        "Code :"+codeSave.getCode()+"\n" ;                   				
				OutboundSMSTextMessage outboundSMSTextMessageClient= new OutboundSMSTextMessage(contentMessageClient);
				OutboundSMSMessageRequest outboundSMSMessageRequestClient = new OutboundSMSMessageRequest("221" + login.getPhone(),outboundSMSTextMessageClient,"221" + login.getPhone(),"LAYDOU"); 
				SmsMessage smsMessageClient = new SmsMessage(outboundSMSMessageRequestClient);					
				Reponse smsReponse =smsService.sendMessage(smsMessageClient);   		

               if(smsReponse.getCode() == 200)
                {
    				user.setMonToken(this.getToken(login.getEmail(),Long.toString(login.getPhone()) ));
    				response.setCode(200);
    				response.setMessage(Utility.CodeAndMessage().get(200));
    				response.setResult(userConnected);		

                }
                else
                {
                	throw new Exception("Error to send sms ");
               }	
			
			}
			else
			{
			
				response.setCode(202);
				response.setMessage(Utility.CodeAndMessage().get(202));
				response.setResult(user);				
			}
		}
		
		catch(Exception e)
		{
			if(!(codeSaveOK == null))
			{
				 if(!(userConnected == null))
				{
					 userConnected.getCodes().remove(codeSaveOK);
					 userRepository.save(userConnected);
					 codeRepository.delete(codeSaveOK);
				}
				else
				{
					 codeRepository.delete(codeSaveOK);

				}
				
			} 
			response.setCode(500);
			response.setMessage(Utility.CodeAndMessage().get(500));
			response.setResult(null);
		}
		
		return response ; 
	}

	@Override
	public Utilisateur findUserByUsernameAndEmail(String username,String email) {
		return userRepository.findByUsernameAndEmail(username,email);
	}
	@Override
	public Utilisateur findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	    
	@Override
	public Reponse createOrUpdateUser(Utilisateur user) {
		Reponse response = new Reponse();	

		try
		{
			if(user.getId() != null)
			{
				Utilisateur userSave = userRepository.findById(user.getId()).get();	
				userSave.setAdresse(user.getAdresse());
				userSave.setEmail(user.getEmail());
				userSave.setGroupeSanguin(user.getGroupeSanguin());
				userSave.setNom(user.getNom());
				userSave.setPays(user.getPays());
				userSave.setPhone(user.getPhone());
				userSave.setPrenom(user.getPrenom());
				userSave.setUsername(user.getEmail());
				userSave.setStatus(true);
				userSave.setVille(user.getVille());	
				userSave.setPassword(bCryptPasswordEncoder.encode(Long.toString(user.getPhone())));
				Utilisateur userSaveAfter =userRepository.save(userSave);
				response.setCode(200);
				response.setMessage(Utility.CodeAndMessage().get(200));
				response.setResult(userSaveAfter);
			}
			else
			{
				user.setUsername(user.getEmail());
				user.setPassword(bCryptPasswordEncoder.encode(Long.toString(user.getPhone())));
				Utilisateur userSaveElse = userRepository.save(user);			
				response.setCode(200);
				response.setMessage(Utility.CodeAndMessage().get(200));
				response.setResult(userSaveElse);
			}
			
			
		}
		catch(Exception e)
		{
			response.setCode(500);
			response.setMessage(Utility.CodeAndMessage().get(500));
			response.setResult(null);				
		}
		return response;	
		
	}

	@Override
	public Reponse getUserById(long id) {
		Reponse response = new Reponse();
		try
		{
			Utilisateur userSave = userRepository.findById(id).get();
			if(userSave != null)
			{
				response.setCode(200);
				response.setMessage(Utility.CodeAndMessage().get(200));
				response.setResult(userSave);
			}
			else
			{
				response.setCode(202);
				response.setMessage(Utility.CodeAndMessage().get(202));
				response.setResult(null);
			}
			

		}
		catch(Exception ex)
		{
			response.setCode(500);
			response.setMessage(Utility.CodeAndMessage().get(500));
			response.setResult(null);
			
		}
		
		return response;
	}

	@Override
	public Reponse changerStatusUser(long id) {
		Reponse response = new Reponse();
		try
		{
			Utilisateur userSave = userRepository.findById(id).get();
			if(userSave != null)
			{
				if(userSave.isStatus() == true )
				{
					userSave.setStatus(false);
				}
				else
				{
					userSave.setStatus(true);
				}
				Utilisateur userUpdate = userRepository.save(userSave);
				response.setCode(200);
				response.setMessage(Utility.CodeAndMessage().get(200));
				response.setResult(userUpdate);
			}
			else
			{
				response.setCode(202);
				response.setMessage(Utility.CodeAndMessage().get(202));
				response.setResult(null);
			}
			

		}
		catch(Exception ex)
		{
			response.setCode(500);
			response.setMessage(Utility.CodeAndMessage().get(500));
			response.setResult(null);
			
		}
		
		return response;
		
		
	}

	@Override
	public String getToken(String username , String password)
	{
      try {
		authenticate(username,  password);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		final UserDetails userDetails = userDetailsService
		.loadUserByUsername(username);		
		final String token = jwtTokenUtil.generateToken(userDetails);
		return token;
		
	}
	
    public  void authenticate(String username, String password) throws Exception {
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
		throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
		throw new Exception("INVALID_CREDENTIALS", e);
		}
		}
	@Override
	public Utilisateur getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Reponse getUsersByStatus(boolean status) {
		Reponse response = new Reponse();

		try
		{
			List<Utilisateur> users =userRepository.findByStatus(status);
			response.setCode(200);
			response.setMessage(Utility.CodeAndMessage().get(200));
			response.setResult(users);
			
		}
		catch(Exception e)
		{
			response.setCode(500);
			response.setMessage(Utility.CodeAndMessage().get(500));
			response.setResult(null);		
			
		}
		return response;
	}
	@Override
	public Reponse activation(long code) {
		// TODO Auto-generated method stub
		Reponse response = new Reponse();	
		try
	   {
			Code codeSave=codeRepository.findByCode(code);
			if(codeSave.getCodeID() > 0 && codeSave.isStatus() == true )
			{
				Utilisateur userGot = userRepository.findById(codeSave.getUserID()).get();
				
				if(userGot != null  && userGot.getId()>0)
				{
					
					if(codeSave.getDemandID() >0 )
					{
						Demande demande = demandRepository.findById(codeSave.getDemandID()).get();							
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
						Date dateFormatter = new Date();					
	                    codeSave.setDate_activation(formatter.format(dateFormatter).toString());	
	                    codeSave.setStatus(false);
						codeRepository.save(codeSave); 
						demande.setActivedJob(true);
						demande.setStatus(true);
						demandRepository.save(demande);
						userGot.setMonToken(this.getToken(userGot.getEmail(),Long.toString(userGot.getPhone())));	
						userGot.setStatus(true);											
						Utilisateur userUpdate =userRepository.save(userGot);					
						response.setCode(200);
						response.setMessage(Utility.CodeAndMessage().get(200));
						response.setResult(userUpdate);						
					}
					else
					{
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
						Date dateFormatter = new Date();					
	                    codeSave.setDate_activation(formatter.format(dateFormatter).toString());	
	                    codeSave.setStatus(false);
						codeRepository.save(codeSave); 				
						userGot.setMonToken(this.getToken(userGot.getEmail(),Long.toString(userGot.getPhone())));	
						userGot.setStatus(true);											
						Utilisateur userUpdate =userRepository.save(userGot);					
						response.setCode(200);
						response.setMessage(Utility.CodeAndMessage().get(200));
						response.setResult(userUpdate);							
					}					
					
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
				
				response.setCode(203);
				response.setMessage(Utility.CodeAndMessage().get(203));
				response.setResult(null);
			}
			
			
		}
		catch(Exception e)
		{
			response.setCode(500);
		    response.setMessage(Utility.CodeAndMessage().get(500));
			response.setResult(null);		
			
		}
		return response;
	}
	@Override
	public Reponse getAllUsers() {
		// TODO Auto-generated method stub
		Reponse response = new Reponse();

		try
		{
			List<Utilisateur> users =userRepository.findAll();
			response.setCode(200);
			response.setMessage(Utility.CodeAndMessage().get(200));
			response.setResult(users);
			
		}
		catch(Exception e)
		{
			response.setCode(500);
			response.setMessage(Utility.CodeAndMessage().get(500));
			response.setResult(null);		
			
		}
		return response;
	}
	@Override
	public void initApp() {
		  
		Partenaire laydouGot = partenaireRepository.findByPhone(771992610);
		if( laydouGot == null)
		{
			    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
			    Date date = new Date();
				Partenaire laydou = new Partenaire();
				laydou.setAdresse("DAKAR,Sénégal");
				laydou.setDate_creation(formatter.format(date).toString());
				laydou.setEmail("contact@laydou.com");
				laydou.setName("LAYOU");
				laydou.setPhone(771992610);
				laydou.setStatus(true);
				Partenaire laydouCreated = partenaireRepository.save(laydou);			    
				if(laydouCreated.getPartenaireID() > 0)
				{
					Utilisateur superAdminLadou = userRepository.findByPhone(771992610);

					if(superAdminLadou == null)
					{
						Utilisateur superadmin =  new Utilisateur(); 
						superadmin.setAdresse("Dakar");
						superadmin.setEmail("contact@laydou.com");
						superadmin.setNom("Laydou");
						superadmin.setPays("Sénégal");
						superadmin.setPhone(771992610);
						superadmin.setPrenom("Laydou");
						superadmin.setStatus(true);
						superadmin.setIdPartenaire(laydouCreated.getPartenaireID());
						superadmin.setUsername("contact@laydou.com");
						superadmin.setRole("SUPERADMIN");
						superadmin.setVille("Dakar");	
						superadmin.setPassword(bCryptPasswordEncoder.encode(Long.toString(771992610)));
						Utilisateur superadminCreated = userRepository.save(superadmin);
						laydouCreated.getUtilisateurs().add(superadminCreated);						
						partenaireRepository.save(laydouCreated);												
					}		
					
				}			
				
		}	
		
		
	}
	@Override
	public List<Utilisateur> getAllUsersByStatusAndLocked(boolean status, boolean locked) {
		List<Utilisateur> users =null;

		try
		{
			users =userRepository.findByStatusAndLocked(status, locked);
			
			
		}
		catch(Exception e)
		{
			users= new ArrayList<Utilisateur>();		
			
		}
		return users;
	}
}


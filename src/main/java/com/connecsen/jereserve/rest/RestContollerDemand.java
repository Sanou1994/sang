package com.connecsen.jereserve.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.connecsen.jereserve.domaine.Demande;
import com.connecsen.jereserve.domaine.OutboundSMSMessageRequest;
import com.connecsen.jereserve.domaine.OutboundSMSTextMessage;
import com.connecsen.jereserve.domaine.PartenaireTempon;
import com.connecsen.jereserve.domaine.Reponse;
import com.connecsen.jereserve.domaine.SmsMessage;
import com.connecsen.jereserve.service.IDemandeService;
import com.connecsen.jereserve.service.SmsService;
import com.connecsen.jereserve.utils.Utility;
import com.fasterxml.jackson.core.JsonProcessingException;
@RestController 
public class RestContollerDemand {
	private IDemandeService iDemandeService;
	@Autowired
	private  SmsService smsService;
	public RestContollerDemand(IDemandeService iDemandeService) {
		super();
		this.iDemandeService = iDemandeService;
	}
	@PostMapping(Utility.ADD_DEMAND) 
	public Reponse addDemand( @RequestBody Demande demand) {
		Reponse demandCreated =iDemandeService.lancerDemande(demand);
		return demandCreated;
	}
	@PostMapping(Utility.ADD_DEMAND_TO_PARTENAIRE) 
	public Reponse addDemand( @RequestBody PartenaireTempon partenaireTempon) {
		Reponse demandCreated =iDemandeService.addDemandToPartenaire(partenaireTempon);
		return demandCreated;
	}
	@PostMapping(Utility.ADD_DEMAND_BY_ID) 
	public Reponse addDemandToUser(@PathVariable(value = "id") long userId, @RequestBody Demande demand) {		
		return iDemandeService.addDemandToUser(userId, demand);
	}
	
	@GetMapping(Utility.GET_ALL_DEMANDS)
	 public Reponse getAllDemandes()
	 {
		 return iDemandeService.getAllDemandes();
	 }
	@GetMapping(Utility.SMS) 
	public Reponse sms() throws JsonProcessingException {
		
		OutboundSMSTextMessage outboundSMSTextMessageClient= new OutboundSMSTextMessage(" FISRT SMS");
		OutboundSMSMessageRequest outboundSMSMessageRequestClient = new OutboundSMSMessageRequest("221772490304",outboundSMSTextMessageClient,"221772490304","LAYDOU"); 
		SmsMessage smsMessageClient = new SmsMessage(outboundSMSMessageRequestClient);	
		
			Reponse demandCreated =smsService.sendMessage(smsMessageClient);
		return demandCreated;
	}
	
}

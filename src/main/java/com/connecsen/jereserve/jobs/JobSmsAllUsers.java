package com.connecsen.jereserve.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.connecsen.jereserve.domaine.Demande;
import com.connecsen.jereserve.service.IDemandeService;

public class JobSmsAllUsers implements Runnable {	
	 @Autowired
	 private IDemandeService iDemandService;
	
	   public void run() {
		   
		   List<Demande> demandes = this.iDemandService.getDemandeByStatusAndLockedAndMessageSendStateAndActivedJob();
			for (int i = 0; i < demandes.size(); i++) {
		        switch (demandes.get(i).getTypeOfDemand()) {
				   case "RECEVEUR":
				    	 this.iDemandService.sendMessageForAllUsers(demandes.get(i)).getResult();
				    	 break;
				    case "COLLECTE":
				    	 this.iDemandService.sendMessageForAllUsers(demandes.get(i)).getResult();

					break;

				    case "DONNEUR":
				    	  this.iDemandService.sendMessageForAllStructures(demandes.get(i)).getResult();
					break;
				}
				
				
			}
		  
			   
			   
		  
		 }

	public JobSmsAllUsers(IDemandeService iDemandService) {
		super();
		this.iDemandService = iDemandService;
	}
	 
}

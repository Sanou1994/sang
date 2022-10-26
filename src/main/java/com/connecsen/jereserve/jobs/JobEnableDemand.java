package com.connecsen.jereserve.jobs;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.connecsen.jereserve.domaine.Demande;
import com.connecsen.jereserve.service.IDemandeService;

public class JobEnableDemand implements Runnable {	
	 @Autowired
	 private IDemandeService iDemandService;
	
	   public void run() {
		   
		   List<Demande> demandes = this.iDemandService.getDemandeByStatusAndLockedAndMessageSendStateAndActivedJob();
			for (int i = 0; i < demandes.size(); i++)
			{
			    Date firstDate = new Date();
			    long diff =firstDate.getTime()- demandes.get(i).getDate_demande_numerique()  ;
			    if(diff >= 259200000)
			    {
			    	iDemandService.changerStatusDemande(demandes.get(i).getDemandID());				
			    }			      				
			 } 
		 }

	public JobEnableDemand(IDemandeService iDemandService) {
		super();
		this.iDemandService = iDemandService;
	}
	 
}

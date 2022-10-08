package com.connecsen.jereserve.service;
import java.util.List;

import com.connecsen.jereserve.domaine.Demande;
import com.connecsen.jereserve.domaine.PartenaireTempon;
import com.connecsen.jereserve.domaine.Reponse;
public interface IDemandeService {
    //GESTION ROLE
	  public Reponse lancerDemande(Demande demande);
	  public Reponse addDemandToUser(long idUser,Demande demande);
	  public Reponse addDemandToPartenaire(PartenaireTempon partenaireTempon);
	  public Reponse getDemandeById(long id);
	  public Reponse getAllDemandes();	 
	  public Reponse sendMessageForAllUsers(Demande demand);	
	  public Reponse sendMessageForAllStructures(Demande demand);	 
	  public boolean changerStatusDemande(long id);	 
	  public List<Demande> getDemandeByStatusAndLockedAndMessageSendStateAndActivedJob();
}

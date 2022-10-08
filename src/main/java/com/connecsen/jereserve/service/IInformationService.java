package com.connecsen.jereserve.service;
import com.connecsen.jereserve.domaine.Information;
import com.connecsen.jereserve.domaine.Reponse;
public interface IInformationService {
    //GESTION ROLE
	  public Reponse addInformation(Information information);
	  public Reponse getInformationById(long id);
	  public Reponse getAllInformations();	 
	  public boolean changerStatusInformation(long id);	 
}

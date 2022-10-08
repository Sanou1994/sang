package com.connecsen.jereserve.service;

import com.connecsen.jereserve.domaine.Partenaire;
import com.connecsen.jereserve.domaine.Reponse;

public interface IPartenaireService {
	//GESTION STRUCTURE
	  public Reponse addPartenaire(Partenaire partenaire);
	  public Reponse getPartenaireById(long id);
	  public Reponse getAllPartenaires();	 
	  public Reponse changerStatusPartenaire(long id);	
}

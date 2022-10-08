package com.connecsen.jereserve.service;
import java.util.List;

import com.connecsen.jereserve.domaine.Login;
import com.connecsen.jereserve.domaine.Reponse;
import com.connecsen.jereserve.domaine.Utilisateur;

public interface IAccountService {
      // SERVICE UTILISATEUR
	 
	         //GESTION USER
	  public String getToken(String username , String password);
	  public Reponse createOrUpdateUser(Utilisateur user);
	  public Reponse getUserById(long id);
	  public Reponse activation(long phone,long code);
	  public Reponse se_connecter(Login login);
	  public Utilisateur login_up(Utilisateur user);
	  public Utilisateur findUserByUsername(String username);
	  public Utilisateur findUserByUsernameAndEmail(String username,String email);
	  public Utilisateur getUserByEmail(String email);
	  public Reponse getUsersByStatus(boolean status);
	  public Reponse changerStatusUser(long id); 
	  public Reponse getAllUsers();
	  public List<Utilisateur>  getAllUsersByStatusAndLocked(boolean status,boolean locked);
      public void initApp();
	
    }

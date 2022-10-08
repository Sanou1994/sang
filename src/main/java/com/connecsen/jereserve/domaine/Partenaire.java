package com.connecsen.jereserve.domaine;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity @NoArgsConstructor @AllArgsConstructor
@Data
public class Partenaire {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private long partenaireID;
     private boolean status;
     private String name ;
     private long phone ;
     private String email ;
     private boolean locked=false;
     private String adresse ;
     private String date_creation ;
 	@OneToMany(targetEntity=Demande.class)
 	@LazyCollection(LazyCollectionOption.FALSE)
     private List<Demande> demandes= new ArrayList<Demande>() ;
	@OneToMany(targetEntity=Code.class)
	@LazyCollection(LazyCollectionOption.FALSE)
     private List<Information> informations= new ArrayList<Information>() ;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(targetEntity=Utilisateur.class)
    private List<Utilisateur> utilisateurs= new ArrayList<Utilisateur>() ;
}



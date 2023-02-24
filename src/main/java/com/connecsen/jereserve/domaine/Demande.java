package com.connecsen.jereserve.domaine;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity  @NoArgsConstructor @AllArgsConstructor
@Data
public class Demande {
	  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
       private long demandID;
       private boolean status;
       private String name_of_structure ;        
       private String groupeSanguin ;        
       private String name ;
       private String lastName ;
       private long phone ;
       private String email ;
       private String nameOfCenter ;
       private String adress ;
       private String sexe ;
       private long age ;
       private String heardquarter ;
       private String country ;
       private String city ;
       private String typeOfDemand ;
       private String date_demande;
       private long date_demande_numerique ;
       private String date_debut;
       private String date_fint;
       private String heure_debut;
       private String heure_fin;
       private boolean locked=false;
   	   private boolean messageSendState=false;
   	   private boolean activedJob=false;
       private long partenaireID;
       @Lob
       private String raisonOfDemand ;  
       
}

package com.connecsen.jereserve.domaine;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
public class Utilisateur {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String prenom;
	private String username;
    private String groupeSanguin ;        
	private String nom;
	private String pays;
	private String ville;
	private String adresse;
	private boolean status ;
	private String email;
	private long phone;
	private String monToken;
	private String password;
	private String compteBancaire;
    private boolean locked=false;
	private String  role ; 
	private Long idPartenaire;
	@OneToMany(targetEntity=Code.class,cascade = CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Code> codes= new ArrayList<Code>() ;
	@OneToMany(targetEntity=Demande.class,cascade = CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Demande> demandes = new ArrayList<Demande>() ;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(targetEntity=Information.class,cascade = CascadeType.REMOVE)
	private List<Information> informations = new ArrayList<Information>() ;;	
}

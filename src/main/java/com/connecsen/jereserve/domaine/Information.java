package com.connecsen.jereserve.domaine;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @NoArgsConstructor @AllArgsConstructor @Data
public class Information {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long informationID;
	private String dateInformation;
	private String typeOfInformation;
	 private boolean locked=false;
     private boolean status;
     private long partenaireID;
	private String url_video;
	@Lob
	private String description;
	private boolean statusOfInformation;
	

}

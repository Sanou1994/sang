package com.connecsen.jereserve.domaine;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @NoArgsConstructor @AllArgsConstructor
@Data
public class Code {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codeID;
	private long code;
	private boolean status;
	private long phone;
	private long demandID;
	private long userID;
	private String date_activation ;
}

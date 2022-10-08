package com.connecsen.jereserve.domaine;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Login {	
	private long phone;
	private String email;

}

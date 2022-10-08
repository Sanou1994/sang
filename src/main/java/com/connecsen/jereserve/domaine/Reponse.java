package com.connecsen.jereserve.domaine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Reponse {
	private String message;
	private int code;
	private Object result;	
}

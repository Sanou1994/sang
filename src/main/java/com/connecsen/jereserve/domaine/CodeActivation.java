package com.connecsen.jereserve.domaine;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class CodeActivation {	
	private long phone;
	private long code;

}
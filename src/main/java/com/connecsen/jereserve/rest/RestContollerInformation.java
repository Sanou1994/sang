package com.connecsen.jereserve.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.connecsen.jereserve.domaine.Information;
import com.connecsen.jereserve.domaine.Reponse;
import com.connecsen.jereserve.service.IInformationService;
import com.connecsen.jereserve.utils.Utility;
@RestController 
public class RestContollerInformation {
	private IInformationService iInformationService;
	public RestContollerInformation(IInformationService iInformationService) {
		super();
		this.iInformationService = iInformationService;
	}
	
	@PostMapping(Utility.ADD_INFORMATION) 
	public Reponse addInformation(@RequestBody Information info) {		
		return iInformationService.addInformation( info);
	}
	@GetMapping(Utility.GET_ALL_INFORMATIONS)
	 public Reponse getAllInformations()
	 {
		 return iInformationService.getAllInformations();
	 }
	
}

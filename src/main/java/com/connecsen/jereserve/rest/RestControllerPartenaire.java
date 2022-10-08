package com.connecsen.jereserve.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.connecsen.jereserve.domaine.Partenaire;
import com.connecsen.jereserve.domaine.Reponse;
import com.connecsen.jereserve.service.IPartenaireService;
import com.connecsen.jereserve.utils.Utility;
@RestController
public class RestControllerPartenaire {
	private IPartenaireService iPartenaireService;
	public RestControllerPartenaire(IPartenaireService iPartenaireService) {
		super();
		this.iPartenaireService = iPartenaireService;
	}
	@PostMapping(Utility.ADD_STRUCTURE) 
	public Reponse addPartenaire( @RequestBody Partenaire partenaire) {
		Reponse partenaireCreated =iPartenaireService.addPartenaire(partenaire);
		return partenaireCreated;
	}
	@GetMapping(Utility.GET_STRUCTURE_BY_ID) 
	public Reponse addPartenaire(@PathVariable(value = "id") long userId) {		
		return iPartenaireService.getPartenaireById(userId);
	}
	@GetMapping(Utility.GET_ALL_STRUCTURES)
	 public Reponse getAllPartenaires()
	 {
		 return iPartenaireService.getAllPartenaires();
	 }
	@GetMapping(Utility.CHANGE_STATUS_STRUCTURE_BY_ID) 
	public Reponse changerStatus(@PathVariable(value = "id") long userId) {		
		return iPartenaireService.changerStatusPartenaire(userId);
	}

}

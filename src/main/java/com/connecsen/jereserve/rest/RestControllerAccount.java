package com.connecsen.jereserve.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.connecsen.jereserve.domaine.CodeActivation;
import com.connecsen.jereserve.domaine.Login;
import com.connecsen.jereserve.domaine.Reponse;
import com.connecsen.jereserve.domaine.Utilisateur;
import com.connecsen.jereserve.service.IAccountService;
import com.connecsen.jereserve.utils.Utility;

@RestController 
public class RestControllerAccount {
private IAccountService accountService;


public RestControllerAccount(IAccountService accountService) {
	super();
	this.accountService = accountService;
}

@PostMapping(Utility.DO_REGISTER)
public boolean register( @RequestBody Utilisateur user) {
	boolean reponse =false;
	Utilisateur userAdd =accountService.login_up(user);
	if(userAdd !=null) {
		reponse =true;
	} 
	return reponse;
}


@PostMapping(Utility.ADD_UPDATE_USER)
public Reponse update( @RequestBody Utilisateur user) {
	Reponse reponse =accountService.createOrUpdateUser(user);	
	return reponse;
}
@GetMapping(Utility.GET_ALL_USERS_ACTIVED)
public Reponse getUsersActived()
{
	return accountService.getUsersByStatus(true);
}
@GetMapping(Utility.GET_ALL_USERS)
public Reponse getUsers()
{
	return accountService.getAllUsers();
}
@GetMapping(Utility.DISABLE_USER_BY_ID)
public Reponse getDesableUser(@PathVariable(value = "id") long userId)
{
	return accountService.changerStatusUser(userId);
}
@GetMapping(Utility.GET_USER_BY_ID)
public Reponse getUsersById(@PathVariable(value = "id") long userId)
{
	return accountService.getUserById(userId);
}

@PostMapping(Utility.DO_LOGIN)
public Reponse verifiedAccount( @RequestBody Login  login) {	
	return accountService.se_connecter(login); 
}
@PostMapping(Utility.DO_ACTIVATION)
public Reponse activation( @RequestBody CodeActivation  code) {	
	return accountService.activation(code.getPhone(), code.getCode()); 
}


}

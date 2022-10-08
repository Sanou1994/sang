package com.connecsen.jereserve.exception.createexception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CreateRessourceException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public CreateRessourceException(String equipe) {
        super(String.format("terrain with name is '%s' not found", equipe));
    }
}

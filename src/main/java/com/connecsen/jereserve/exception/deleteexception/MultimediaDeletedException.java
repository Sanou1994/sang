package com.connecsen.jereserve.exception.deleteexception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class MultimediaDeletedException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public MultimediaDeletedException(Long id) {
        super(String.format("multimedia with id '%s' can't deleted", id));
    }
}

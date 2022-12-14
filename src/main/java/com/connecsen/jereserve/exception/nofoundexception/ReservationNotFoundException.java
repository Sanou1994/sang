package com.connecsen.jereserve.exception.nofoundexception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReservationNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ReservationNotFoundException(Long username) {
        super(String.format("reservation with id '%s' not found", username));
    }
}

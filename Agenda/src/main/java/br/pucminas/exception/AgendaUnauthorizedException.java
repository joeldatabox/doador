package br.pucminas.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by Joel Rodrigues on 05/08/2016.
 */
public class AgendaUnauthorizedException extends AgendaException {
    public AgendaUnauthorizedException() {
        super(401);
    }

    public AgendaUnauthorizedException(String message) {
        super(message);
        setHttpStatus(HttpStatus.UNAUTHORIZED);
    }
}

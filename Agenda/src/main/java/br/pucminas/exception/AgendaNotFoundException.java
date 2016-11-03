package br.pucminas.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by Joel Rodrigues on 05/08/2016.
 */
public class AgendaNotFoundException extends AgendaException {
    public AgendaNotFoundException() {
        super(404);
    }

    public AgendaNotFoundException(String message) {
        super(message);
        setHttpStatus(HttpStatus.NOT_FOUND);
    }
}

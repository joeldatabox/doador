package br.pucminas.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by Joel Rodrigues on 05/08/2016.
 */
public class AgendaNoContentException extends AgendaException {
    public AgendaNoContentException() {
        super(204);
    }

    public AgendaNoContentException(String message) {
        super(message);
        setHttpStatus(HttpStatus.NO_CONTENT);
    }
}

package br.pucminas.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by Joel Rodrigues on 05/08/2016.
 */
public class PacienteNotFoundException extends PacienteException {
    public PacienteNotFoundException() {
        super(404);
    }

    public PacienteNotFoundException(String message) {
        super(message);
        setHttpStatus(HttpStatus.NOT_FOUND);
    }
}

package br.pucminas.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by Joel Rodrigues on 05/08/2016.
 */
public class PacienteUnauthorizedException extends PacienteException {
    public PacienteUnauthorizedException() {
        super(401);
    }

    public PacienteUnauthorizedException(String message) {
        super(message);
        setHttpStatus(HttpStatus.UNAUTHORIZED);
    }
}

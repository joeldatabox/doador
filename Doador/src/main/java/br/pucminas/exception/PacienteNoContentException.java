package br.pucminas.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by Joel Rodrigues on 05/08/2016.
 */
public class PacienteNoContentException extends PacienteException {
    public PacienteNoContentException() {
        super(204);
    }

    public PacienteNoContentException(String message) {
        super(message);
        setHttpStatus(HttpStatus.NO_CONTENT);
    }
}

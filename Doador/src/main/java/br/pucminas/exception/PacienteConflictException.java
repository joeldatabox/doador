package br.pucminas.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by Joel Rodrigues on 05/08/2016.
 */
public class PacienteConflictException extends PacienteException {
    public PacienteConflictException() {
    }

    public PacienteConflictException(int httpCodeError) {
        super(httpCodeError);
    }

    public PacienteConflictException(String message) {
        super(message);
        setHttpStatus(HttpStatus.CONFLICT);
    }

    public PacienteConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public PacienteConflictException(Throwable cause) {
        super(cause);
    }

    public PacienteConflictException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

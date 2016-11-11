package br.pucminas.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by Joel Rodrigues on 05/08/2016.
 */
public class AgendaConflictException extends AgendaException {
    public AgendaConflictException() {
        setHttpStatus(HttpStatus.CONFLICT);
    }

    public AgendaConflictException(int httpCodeError) {
        super(httpCodeError);
    }

    public AgendaConflictException(String message) {
        super(message);
        setHttpStatus(HttpStatus.CONFLICT);
    }

    public AgendaConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public AgendaConflictException(Throwable cause) {
        super(cause);
    }

    public AgendaConflictException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

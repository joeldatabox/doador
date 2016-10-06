package br.pucminas.exception;

import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolationException;

/**
 * Created by Joel Rodrigues on 05/08/2016.
 */
public class PacienteException extends RuntimeException {
    private HttpStatus httpStatus;
    private String jsonMessage;

    public PacienteException() {
    }

    public PacienteException(int httpCodeError) {
        this.httpStatus = HttpStatus.valueOf(httpCodeError);
    }

    public PacienteException(String message) {
        super(message);
    }

    public PacienteException(String message, Throwable cause) {
        super(message, cause);
    }

    public PacienteException(Throwable cause) {
        super(cause);
    }

    public PacienteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public PacienteException(ConstraintViolationException ex) {

        StringBuilder sb = new StringBuilder();
        sb.append("{\"message\":\"Erro de validacao\",");
        sb.append("\"violations\":[");
        ex.getConstraintViolations().forEach(i -> {
            if(sb.indexOf("propertyPath") >= 0 ){
                sb.append(",");
            }
            sb.append("{\"propertyPath\":\"").append(i.getPropertyPath()).append("\",")
                    .append("\"messageTemplate\":\"").append(i.getMessageTemplate()).append("\"}");
        });
        sb.append("]");
        sb.append("}");
        jsonMessage = sb.toString();
        this.setHttpStatus(HttpStatus.BAD_REQUEST);

    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public void setHttpStatus(int httpCodeError) {
        this.httpStatus = HttpStatus.valueOf(httpCodeError);
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getJsonMessage() {
        if (jsonMessage == null) {
            jsonMessage = "{\"message\":\"" + getMessage() + "\"}";
        }
        return jsonMessage;

    }
}

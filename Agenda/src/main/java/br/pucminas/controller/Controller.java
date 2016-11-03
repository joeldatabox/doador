package br.pucminas.controller;

import br.pucminas.exception.AgendaException;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

/**
 * Created by master on 03/11/16.
 */
public class Controller<T extends Serializable> {
    ResponseEntity<T> processException(AgendaException ex) {
        return new ResponseEntity(ex.getJsonMessage(), ex.getHttpStatus());
    }
}

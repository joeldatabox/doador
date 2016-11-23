package br.pucminas.service;

import br.pucminas.exception.PacienteException;
import br.pucminas.model.User;

/**
 * Created by master on 11/11/16.
 */
public interface AuthService {
    public String auth(User user) throws PacienteException;
}

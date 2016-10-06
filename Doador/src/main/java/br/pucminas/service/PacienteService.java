package br.pucminas.service;

import br.pucminas.exception.PacienteException;
import br.pucminas.exception.PacienteNoContentException;
import br.pucminas.exception.PacienteNotFoundException;
import br.pucminas.model.Paciente;

import java.util.Collection;

/**
 * Created by Joel Rodrigues on 03/08/2016.
 */
public interface PacienteService {
    Paciente findById(Long id) throws PacienteNotFoundException;

    Paciente findByCpf(String cpf) throws PacienteNotFoundException;

    Collection<Paciente> findAll() throws PacienteNoContentException;

    Paciente create(Paciente paciente) throws PacienteException;

    Paciente update(Paciente paciente) throws PacienteException;

    void delete(Paciente paciente) throws PacienteException;

    void delete(Long id) throws PacienteException;

    Long count();
}

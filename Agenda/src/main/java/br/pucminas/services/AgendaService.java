package br.pucminas.services;

import br.pucminas.exception.AgendaException;
import br.pucminas.exception.AgendaNoContentException;
import br.pucminas.exception.AgendaNotFoundException;
import br.pucminas.model.Agenda;

import java.util.Collection;

/**
 * Created by master on 03/11/16.
 */
public interface AgendaService {
    Agenda findById(Long id) throws AgendaNotFoundException;

    Agenda findHemocentro(Long idHemocentro) throws AgendaNotFoundException;

    Collection<Agenda> findAll() throws AgendaNoContentException;

    Agenda create(Agenda Agenda) throws AgendaException;

    Agenda update(Agenda Agenda) throws AgendaException;

    void delete(Agenda Agenda) throws AgendaException;

    void delete(Long id) throws AgendaException;

    Long count();
}

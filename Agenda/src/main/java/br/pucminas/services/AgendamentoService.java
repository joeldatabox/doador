package br.pucminas.services;

import br.pucminas.exception.AgendaException;
import br.pucminas.exception.AgendaNoContentException;
import br.pucminas.model.Agenda;
import br.pucminas.model.Agendamento;

import java.util.Collection;

/**
 * Created by master on 03/11/16.
 */
public interface AgendamentoService {
    Agendamento create(Agendamento agendamento) throws AgendaException;

    Agendamento update(Agendamento agendamento) throws AgendaException;

    Collection<Agendamento> findAll() throws AgendaNoContentException;

    Collection<Agendamento> findByAgenda(Agenda agenda) throws AgendaNoContentException;

    Agendamento findOne(Long id, Agenda agenda) throws AgendaNoContentException;

    public void delete(Long id, Agenda agenda);
}

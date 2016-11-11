package br.pucminas.services;

import br.pucminas.exception.AgendaException;
import br.pucminas.exception.AgendaNoContentException;
import br.pucminas.exception.AgendaNotFoundException;
import br.pucminas.model.Agenda;
import br.pucminas.model.DiaAtendimento;

import java.util.Collection;

/**
 * Created by master on 03/11/16.
 */
public interface DiaAtendimentoService {

    void delete(Agenda Agenda) throws AgendaException;

    void delete(Long id) throws AgendaException;
}

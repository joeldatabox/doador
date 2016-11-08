package br.pucminas.services;

import br.pucminas.exception.AgendaException;
import br.pucminas.model.Agendamento;

/**
 * Created by master on 03/11/16.
 */
public interface AgendamentoService {
    Agendamento create(Agendamento agendamento) throws AgendaException;
}

package br.pucminas.controller;

import br.pucminas.exception.AgendaException;
import br.pucminas.model.Agenda;
import br.pucminas.model.Agendamento;
import br.pucminas.services.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by master on 07/11/16.
 */
@RestController
public class AgendamentoController extends Controller<Agendamento> {
    @Autowired
    private AgendamentoService service;

    @RequestMapping(value = "/api/agendas/{idAgenda}/agendamentos", method = RequestMethod.POST)
    public ResponseEntity createAgendamento(@PathVariable Long idAgenda, @RequestBody Agendamento agendamento) {
        try {
            Agenda agenda = new Agenda();
            agenda.setId(idAgenda);
            agendamento.setAgenda(agenda);
            return new ResponseEntity(service.create(agendamento), HttpStatus.CREATED);
        } catch (AgendaException ex) {
            return processException(ex);
        }
    }

    @RequestMapping(value = "/api/agendas/{idAgenda}/agendamentos", method = RequestMethod.PUT)
    public ResponseEntity updateAgendamento(@PathVariable Long idAgenda, @RequestBody Agendamento agendamento) {
        try {
            Agenda agenda = new Agenda();
            agenda.setId(idAgenda);
            agendamento.setAgenda(agenda);
            return new ResponseEntity(service.update(agendamento), HttpStatus.OK);
        } catch (AgendaException ex) {
            return processException(ex);
        }
    }

    @RequestMapping(value = "/api/agendas/{idAgenda}/agendamentos", method = RequestMethod.GET)
    public ResponseEntity getAll(@PathVariable Long idAgenda) {
        try {
            return new ResponseEntity(service.findByAgenda(new Agenda(idAgenda)), HttpStatus.OK);
        } catch (AgendaException ex) {
            return processException(ex);
        }
    }

    @RequestMapping(value = "/api/agendas/{idAgenda}/agendamentos/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable Long idAgenda, @PathVariable Long id) {
        try {
            return new ResponseEntity(service.findOne(id, new Agenda(idAgenda)), HttpStatus.OK);
        } catch (AgendaException ex) {
            return processException(ex);
        }
    }

    @RequestMapping(value = "/api/agendas/{idAgenda}/agendamentos/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long idAgenda, @PathVariable Long id) {
        try {
            service.delete(id, new Agenda(idAgenda));
            return new ResponseEntity(HttpStatus.OK);
        } catch (AgendaException ex) {
            return processException(ex);
        }
    }
}
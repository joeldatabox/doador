package br.pucminas.controller;

import br.pucminas.exception.AgendaException;
import br.pucminas.model.Agenda;
import br.pucminas.services.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by master on 07/11/16.
 */
@RestController
public class AgendamentoController extends Controller<Agenda> {
    @Autowired
    private AgendaService service;

    @RequestMapping(value = "/api/agendas/{id}/diaAtendimento", method = RequestMethod.GET)
    public ResponseEntity<Agenda> getDiaAtendimento(@PathVariable Long id, HttpServletResponse response) {
        try {
            return new ResponseEntity(service.findDiaAtendimento(id), HttpStatus.OK);
        } catch (AgendaException ex) {
            return processException(ex);
        }
    }

    @RequestMapping(value = "/api/agendas/{id}/", method = RequestMethod.GET)
    public ResponseEntity<Agenda> getDiaAtendimento1(@PathVariable Long id, HttpServletResponse response) {
        try {
            return new ResponseEntity(service.findDiaAtendimento(id), HttpStatus.OK);
        } catch (AgendaException ex) {
            return processException(ex);
        }
    }

}
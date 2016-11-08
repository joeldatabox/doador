package br.pucminas.controller;

import br.pucminas.exception.AgendaException;
import br.pucminas.model.Agenda;
import br.pucminas.services.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by master on 03/11/16.
 */
@RestController
public class AgendaController extends Controller<Agenda> {
    @Autowired
    private AgendaService service;

    @RequestMapping(value = "/api/agendas/{id}", method = RequestMethod.GET)
    public ResponseEntity<Agenda> get(@PathVariable Long id) {
        try {
            Agenda agenda = service.findById(id);
            return new ResponseEntity<Agenda>(agenda, HttpStatus.OK);
        } catch (AgendaException ex) {
            return processException(ex);
        }
    }

    @RequestMapping(value = "/api/agendas/hemocentros/{id}", method = RequestMethod.GET)
    public ResponseEntity<Agenda> getByHemocentro(@PathVariable Long id) {
        try {
            Agenda agenda = service.findHemocentro(id);
            return new ResponseEntity<Agenda>(agenda, HttpStatus.OK);
        } catch (AgendaException ex) {
            return processException(ex);
        }
    }


    @RequestMapping(value = "/api/agendas", method = RequestMethod.GET)
    public ResponseEntity getAll(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                 @RequestParam(value = "size", defaultValue = "100", required = false) Integer size) {
        try {
            return new ResponseEntity(service.findAll(), HttpStatus.OK);
        } catch (AgendaException ex) {
            return processException(ex);
        }
    }

    @RequestMapping(value = "/api/agendas", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody Agenda agenda) {
        try {
            return new ResponseEntity(service.create(agenda), HttpStatus.CREATED);
        } catch (AgendaException ex) {
            return processException(ex);
        }
    }

    @RequestMapping(value = "/api/agendas", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody Agenda agenda) {
        try {
            return new ResponseEntity(service.update(agenda), HttpStatus.OK);
        } catch (AgendaException ex) {
            return processException(ex);
        }
    }

    @RequestMapping(value = "/api/agendas/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (AgendaException ex) {
            return processException(ex);
        }
    }

}

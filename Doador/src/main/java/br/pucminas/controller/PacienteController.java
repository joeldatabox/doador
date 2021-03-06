package br.pucminas.controller;

import br.pucminas.exception.PacienteException;
import br.pucminas.exception.PacienteNotFoundException;
import br.pucminas.model.Paciente;
import br.pucminas.service.PacienteService;
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
 * Created by Joel Rodrigues on 03/08/2016.
 */
@RestController
@RequestMapping(value = "/api/pacientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PacienteController extends Controller<Paciente> {
    @Autowired
    private PacienteService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Paciente> get(@PathVariable Long id) {
        try {
            Paciente paciente = service.findById(id);
            return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
        } catch (PacienteException ex) {
            return processException(ex);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAll(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                 @RequestParam(value = "size", defaultValue = "100", required = false) Integer size) {
        try {
            return new ResponseEntity(service.findAll(), HttpStatus.OK);
        } catch (PacienteException ex) {
            return processException(ex);
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody Paciente paciente) {
        try {
            return new ResponseEntity(service.create(paciente), HttpStatus.CREATED);
        } catch (PacienteException ex) {
            return processException(ex);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody Paciente paciente) {
        try {
            return new ResponseEntity(service.update(paciente), HttpStatus.OK);
        } catch (PacienteException ex) {
            return processException(ex);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            service.delete(service.findById(id));
            return new ResponseEntity(HttpStatus.OK);
        } catch (PacienteException ex) {
            return new ResponseEntity(ex.getJsonMessage(), ex.getHttpStatus());
        }
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResponseEntity count() {
        try {
            return new ResponseEntity(service.count(), HttpStatus.OK);
        } catch (PacienteException ex) {
            return processException(ex);
        }
    }

    @RequestMapping(value = "/cpf/{cpf}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable String cpf) {
        Paciente paciente = null;
        try {
            return new ResponseEntity<Paciente>(service.findByCpf(cpf), HttpStatus.OK);
        } catch (PacienteNotFoundException ex) {
            return processException(ex);
        }

    }
}

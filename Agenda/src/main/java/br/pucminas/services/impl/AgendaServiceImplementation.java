package br.pucminas.services.impl;

import br.pucminas.exception.AgendaConflictException;
import br.pucminas.exception.AgendaException;
import br.pucminas.exception.AgendaNoContentException;
import br.pucminas.exception.AgendaNotFoundException;
import br.pucminas.model.Agenda;
import br.pucminas.repository.AgendaRepository;
import br.pucminas.services.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.Collection;

/**
 * Created by master on 03/11/16.
 */
@Service
public class AgendaServiceImplementation implements AgendaService {
    @Autowired
    private AgendaRepository repository;

    @Override
    public Agenda findById(Long id) throws AgendaNotFoundException {
        Agenda agenda = repository.findOne(id);
        if (agenda == null) {
            throw new AgendaNotFoundException("Registro não encontrado");
        }
        return agenda;
    }

    @Override
    public Agenda findHemocentro(Long idHemocentro) throws AgendaNotFoundException {
        Agenda agenda = repository.findByIdHemocentro(idHemocentro);
        if (agenda == null) {
            throw new AgendaNotFoundException("Registro não encontrado");
        }
        return agenda;
    }

    @Override
    public Collection<Agenda> findAll() throws AgendaNoContentException {
        Collection<Agenda> agendas = (Collection<Agenda>) repository.findAll();
        if (agendas == null || agendas.isEmpty()) {
            throw new AgendaNoContentException();
        }
        return agendas;
    }

    private Agenda merge(Agenda agenda) {
        if (agenda.getId() != null) {
            Agenda record = repository.findOne(agenda.getId());
            if (record == null) {
                throw new AgendaException("registro não encontrado");
            }
            return repository.save(agenda);
        } else {
            if (repository.findByIdHemocentro(agenda.getIdHemocentro()) != null) {
                throw new AgendaConflictException("Hemocentro já cadastrado");
            }
            try {
                return repository.save(agenda);
            } catch (ConstraintViolationException ex) {
                throw new AgendaException(ex);
            }
        }

    }

    @Override
    public Agenda create(Agenda agenda) throws AgendaException {
        return merge(agenda);
    }

    @Override
    public Agenda update(Agenda agenda) throws AgendaException {
        return merge(agenda);
    }

    @Override
    public void delete(Agenda agenda) throws AgendaException {
        repository.delete(agenda);
    }

    @Override
    public void delete(Long id) throws AgendaException {
        Agenda agenda = this.findById(id);
        delete(agenda);
    }

    @Override
    public Long count() {
        return repository.count();
    }
}

package br.pucminas.service.impl;

import br.pucminas.exception.PacienteConflictException;
import br.pucminas.exception.PacienteException;
import br.pucminas.exception.PacienteNoContentException;
import br.pucminas.exception.PacienteNotFoundException;
import br.pucminas.model.Paciente;
import br.pucminas.repository.PacienteRepository;
import br.pucminas.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.Collection;

/**
 * Created by Joel Rodrigues on 03/08/2016.
 */
@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository repository;
    @PersistenceContext
    private EntityManager em;

    @Override
    public Paciente findById(Long id) throws PacienteNotFoundException {
        Paciente paciente = repository.findOne(id);
        if (paciente == null) {
            throw new PacienteNotFoundException("Registro não encontrado");
        }
        return paciente;
    }

    @Override
    public Paciente findByCpf(String cpf) throws PacienteNotFoundException {
        Paciente doador = repository.findByCpf(cpf);
        if (doador == null) {
            throw new PacienteNotFoundException();
        }
        return doador;
    }

    @Override
    public Collection<Paciente> findAll() {
        Collection<Paciente> pacientes = (Collection<Paciente>) repository.findAll();
        if (pacientes == null || pacientes.isEmpty()) {
            throw new PacienteNoContentException();
        }
        return pacientes;
    }

    private Paciente merge(Paciente paciente) {
        if (paciente.getId() != null) {
            Paciente record = repository.findOne(paciente.getId());
            if (record == null) {
                throw new PacienteException("registro não encontrado");
            }
            if (!record.getCpf().equals(paciente.getCpf())) {
                throw new PacienteException("não pode alterar o cpf");
            }
            return repository.save(paciente);
        } else {
            if (repository.findByCpf(paciente.getCpf()) != null) {
                throw new PacienteConflictException("CPF já cadastrado");
            }
            try {
                return repository.save(paciente);
            } catch (ConstraintViolationException ex) {
                throw new PacienteException(ex);
            }
        }

    }

    @Override
    public Paciente create(Paciente paciente) {
        return merge(paciente);
    }

    @Override
    public Paciente update(Paciente paciente) {
        return merge(paciente);
    }

    @Transactional
    @Override
    public void delete(Paciente paciente) {
        em.flush();
        em.createQuery("delete from Endereco where paciente = :paciente")
                .setParameter("paciente", paciente)
                .executeUpdate();
        em.createQuery("delete from Paciente where id = :id")
                .setParameter("id", paciente.getId())
                .executeUpdate();
        repository.delete(paciente);
    }

    @Override
    public void delete(Long id) throws PacienteException {
        Paciente paciente = this.findById(id);
        delete(paciente);
    }

    @Override
    public Long count() {
        return repository.count();
    }


}

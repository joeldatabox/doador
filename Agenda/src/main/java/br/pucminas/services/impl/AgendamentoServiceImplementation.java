package br.pucminas.services.impl;

import br.pucminas.exception.AgendaConflictException;
import br.pucminas.exception.AgendaException;
import br.pucminas.exception.AgendaNoContentException;
import br.pucminas.exception.AgendaNotFoundException;
import br.pucminas.model.Agenda;
import br.pucminas.model.Agendamento;
import br.pucminas.model.enumeration.Dia;
import br.pucminas.repository.AgendamentoRepository;
import br.pucminas.services.AgendaService;
import br.pucminas.services.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Joel Rodrigues on 07/11/2016.
 */
@Service
public class AgendamentoServiceImplementation implements AgendamentoService {
    @Autowired
    private AgendamentoRepository repository;
    @Autowired
    private AgendaService agendaService;
    @PersistenceContext
    private EntityManager em;

    @Override
    public Agendamento create(Agendamento agendamento) throws AgendaException {
        return merge(agendamento);
    }

    @Override
    public Agendamento update(Agendamento agendamento) throws AgendaException {
        return merge(agendamento);
    }

    @Override
    public Collection<Agendamento> findAll() throws AgendaNoContentException {
        Collection<Agendamento> agendamentos = (Collection<Agendamento>) repository.findAll();
        if (agendamentos == null || agendamentos.isEmpty()) {
            throw new AgendaNoContentException();
        }
        return agendamentos;
    }

    @Override
    public Collection<Agendamento> findByAgenda(Agenda agenda) throws AgendaNoContentException {
        Collection<Agendamento> agendamentos = (Collection<Agendamento>) repository.findAll(agenda);
        if (agendamentos == null || agendamentos.isEmpty()) {
            throw new AgendaNoContentException();
        }
        return agendamentos;
    }

    @Override
    public Agendamento findOne(Long id, Agenda agenda) throws AgendaNoContentException {
        Agendamento agendamento = repository.findOne(id, agenda);
        if (agendamento == null) {
            throw new AgendaNotFoundException();
        }
        return agendamento;
    }

    @Override
    public void delete(Agendamento agendamento) {
        delete(agendamento.getId(), agendamento.getAgenda());
    }


    @Override
    public void delete(Long id, Agenda agenda) {
        repository.delete(findOne(id, agenda));
    }

    private Agendamento merge(Agendamento agendamento) {
        if (agendamento.getAgenda() == null || agendamento.getAgenda().getId() == null || agendamento.getAgenda().getId() == 0L) {
            throw new AgendaException("O agendamento deve está ligado a uma agenda!");
        } else {
            try {
                agendamento.setAgenda(agendaService.findById(agendamento.getAgenda().getId()));
            } catch (AgendaNotFoundException ex) {
                new AgendaNotFoundException("registro não encontrado");
            }
        }
        Date dtAgendamento = agendamento.getDtAgendamento();
        if (dtAgendamento != null) {
            final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String horario = sdf.format(dtAgendamento);
            LocalDateTime ldt = LocalDateTime.ofInstant(dtAgendamento.toInstant(), ZoneId.systemDefault());
            //verifica se tem esse horario na agenda
            List list = (List) repository.containsDiaAtendimento(Dia.getDiaOfWeek(ldt.getDayOfWeek()), horario);
            if (list != null && list.isEmpty()) {
                throw new AgendaException("Horario indisponível");
            }
            //verificando se o horario já não foi agendado
            List<Agendamento> agendamentos = (List<Agendamento>) repository.findByDtAgendamento(dtAgendamento);
            agendamentos = agendamentos.stream().filter(a -> !a.getId().equals(agendamento.getId())).collect(Collectors.toList());
            if (agendamentos != null && !agendamentos.isEmpty()) {
                throw new AgendaConflictException("Horário já foi reservado!");
            }
        }


        if (agendamento.getId() != null) {
            Agendamento record = repository.findOne(agendamento.getId());
            if (record == null) {
                throw new AgendaException("registro não encontrado");
            }
            return repository.save(agendamento);
        } else {
            try {
                return repository.save(agendamento);
            } catch (ConstraintViolationException ex) {
                throw new AgendaException(ex);
            }
        }
    }
}

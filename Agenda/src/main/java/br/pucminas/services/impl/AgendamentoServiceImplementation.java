package br.pucminas.services.impl;

import br.pucminas.exception.AgendaException;
import br.pucminas.exception.AgendaNotFoundException;
import br.pucminas.model.Agendamento;
import br.pucminas.model.enumeration.Dia;
import br.pucminas.repository.AgendamentoRepository;
import br.pucminas.services.AgendaService;
import br.pucminas.services.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * Created by Joel Rodrigues on 07/11/2016.
 */
@Service
public class AgendamentoServiceImplementation implements AgendamentoService {
    @Autowired
    private AgendamentoRepository repository;
    @Autowired
    private AgendaService agendaService;

    @Override
    public Agendamento create(Agendamento agendamento) throws AgendaException {
        return merge(agendamento);
    }

    private Agendamento merge(Agendamento agendamento) {
        if (agendamento.getAgenda() == null || agendamento.getAgenda().getId() == null || agendamento.getAgenda().getId() == 0L) {
            throw new AgendaException("O agendamento deve está ligado a uma agenda!");
        } else {
            try {
                agendamento.setAgenda(agendaService.findById(agendamento.getAgenda().getId()));
            } catch (AgendaNotFoundException ex) {
                new AgendaNotFoundException("O agendamento deve está ligado a uma agenda!");
            }
        }
        Date dtAgendamento = agendamento.getDtAgendamento();
        if (dtAgendamento != null) {
            final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String horario = sdf.format(dtAgendamento);
            LocalDateTime ldt = LocalDateTime.ofInstant(dtAgendamento.toInstant(), ZoneId.systemDefault());
            //verifica se tem esse horario na agenda
            if(repository.containsDiaAtendimento(Dia.getDiaOfWeek(ldt.getDayOfWeek()), horario) == null){
                throw new AgendaException("Horario indisponível");
            }
            //verificando se o horario já não foi agendado
            List<Agendamento> agendamentos = (List<Agendamento>) repository.findByDtAgendamento(dtAgendamento);
            if(agendamentos != null && !agendamentos.isEmpty()){
                throw new AgendaException("Horário já foi reservado!");
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

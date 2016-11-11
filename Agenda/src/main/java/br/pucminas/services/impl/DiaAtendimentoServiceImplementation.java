package br.pucminas.services.impl;

import br.pucminas.exception.AgendaException;
import br.pucminas.model.Agenda;
import br.pucminas.model.DiaAtendimento;
import br.pucminas.repository.DiaAtendimentoRepository;
import br.pucminas.services.DiaAtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by master on 11/11/16.
 */
@Service
public class DiaAtendimentoServiceImplementation implements DiaAtendimentoService {
    @Autowired
    private DiaAtendimentoRepository repository;

    @Override
    public void delete(Agenda agenda) throws AgendaException {
        List<DiaAtendimento> dias = agenda.getDiasAtendimento();
        agenda.setDiasAtendimento(null);

        dias.forEach(d -> {
            repository.delete(d);
        });
    }

    @Override
    public void delete(Long id) throws AgendaException {
        repository.delete(id);
    }
}

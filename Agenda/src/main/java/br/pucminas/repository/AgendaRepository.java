package br.pucminas.repository;

import br.pucminas.model.Agenda;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by Joel Rodrigues on 03/08/2016.
 */
@Repository
public interface AgendaRepository extends CrudRepository<Agenda, Long>, QueryByExampleExecutor<Agenda> {
    Agenda findByIdHemocentro(Long idHemocentro);
}

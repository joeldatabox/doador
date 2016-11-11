package br.pucminas.repository;

import br.pucminas.model.Agenda;
import br.pucminas.model.Agendamento;
import br.pucminas.model.DiaAtendimento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by master on 11/11/16.
 */
@Repository
public interface DiaAtendimentoRepository extends CrudRepository<DiaAtendimento, Long>, QueryByExampleExecutor<DiaAtendimento> {
    //@Query("delete from DiaAtendimento AS d where d.agenda = :agenda")
    void deleteByAgenda(Agenda agenda);

}

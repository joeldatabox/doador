package br.pucminas.repository;

import br.pucminas.model.Agenda;
import br.pucminas.model.DiaAtendimento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by Joel Rodrigues on 03/08/2016.
 */
@Repository
public interface AgendaRepository extends CrudRepository<Agenda, Long>, QueryByExampleExecutor<Agenda> {
    Agenda findByIdHemocentro(Long idHemocentro);

    @Query("SELECT d FROM DiaAtendimento AS d LEFT JOIN d.agenda AS a WHERE a.id = :agenda")
    Iterable<DiaAtendimento> getDiaAtendimento(@Param("agenda") Long id);

    @Query("SELECT d FROM DiaAtendimento AS d LEFT JOIN d.agenda AS a WHERE a = :agenda")
    Iterable<DiaAtendimento> getDiaAtendimento(@Param("agenda") Agenda agenda);
}

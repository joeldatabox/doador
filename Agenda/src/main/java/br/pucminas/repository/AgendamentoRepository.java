package br.pucminas.repository;

import br.pucminas.model.Agenda;
import br.pucminas.model.Agendamento;
import br.pucminas.model.DiaAtendimento;
import br.pucminas.model.enumeration.Dia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by Joel Rodrigues on 03/08/2016.
 */
@Repository
public interface AgendamentoRepository extends CrudRepository<Agendamento, Long>, QueryByExampleExecutor<Agendamento> {
    @Query("SELECT d FROM DiaAtendimento AS d WHERE d.dia = :dia AND d.hrInicioAtendimento = :hrInicioAtendimento")
    Iterable<DiaAtendimento> containsDiaAtendimento(@Param("dia") Dia dia, @Param("hrInicioAtendimento") String hrInicial);

    Iterable<Agendamento> findByDtAgendamento(Date dtAgendamento);

    @Query("SELECT a FROM Agendamento AS a WHERE a.agenda = :agenda")
    Iterable<Agendamento> findAll(@Param("agenda") Agenda agenda);

    @Query("SELECT a FROM Agendamento AS a WHERE a.agenda = :agenda AND a.id = :id")
    Agendamento findOne(@Param("id") Long id, @Param("agenda") Agenda agenda);


}

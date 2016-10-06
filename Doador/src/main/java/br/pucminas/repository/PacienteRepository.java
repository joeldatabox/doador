package br.pucminas.repository;

import br.pucminas.model.Paciente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by Joel Rodrigues on 03/08/2016.
 */
@Repository
public interface PacienteRepository extends CrudRepository<Paciente, Long>, QueryByExampleExecutor<Paciente> {
    Paciente findByCpf(String cpf);
}

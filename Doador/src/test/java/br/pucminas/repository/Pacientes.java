package br.pucminas.repository;

import br.pucminas.model.Endereco;
import br.pucminas.model.Paciente;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by master on 10/11/16.
 */
public class Pacientes {
    private static final String CPF1 = "707.555.251-16";
    private static final String CPF2 = "677.841.517-72";
    private static final Date DT_NASC_VALID = Date.from(LocalDateTime.now().minusYears(18L).atZone(ZoneId.systemDefault()).toInstant());


    public static List<Paciente> getPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
            pacientes = new ArrayList<>();
            Paciente paciente = new Paciente();
            paciente.setCpf(CPF1);
            paciente.setDtNascimento(DT_NASC_VALID);
            paciente.setObservacao("paciente teste");
            paciente.setNome("paciente teste");
            paciente.setNomeMae("mãe teste");
            paciente.setNomePai("pai teste");
            paciente.setRg("0303063");
            Endereco end = new Endereco();
            end.setCep("75.900-000");
            end.setCidade("Cidade");
            end.setBairro("Centro");
            end.setDescricao("endereco teste");
            end.setObservacao("Observacao teste");
            end.setPaciente(paciente);
            paciente.addEndereco(end);

            pacientes.add(paciente);

            paciente = new Paciente();
            paciente.setCpf(CPF2);
            paciente.setDtNascimento(DT_NASC_VALID);
            paciente.setObservacao("paciente teste");
            paciente.setNome("paciente teste");
            paciente.setNomeMae("mãe teste");
            paciente.setNomePai("pai teste");
            paciente.setRg("0303063");
            end = new Endereco();
            end.setCep("75.900-000");
            end.setCidade("Cidade");
            end.setBairro("Centro");
            end.setDescricao("endereco teste");
            end.setObservacao("Observacao teste");
            end.setPaciente(paciente);
            paciente.addEndereco(end);

            pacientes.add(paciente);


        return Collections.unmodifiableList(pacientes);
    }
}

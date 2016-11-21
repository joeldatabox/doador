package br.pucminas;

import br.pucminas.controller.PacienteController;
import br.pucminas.model.Endereco;
import br.pucminas.model.Paciente;
import br.pucminas.service.PacienteService;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DoadorApplicationTests {
    private static final String CPF = "707.555.251-16";
    private static final Date DT_NASC_VALID = Date.from(LocalDateTime.now().minusYears(18L).atZone(ZoneId.systemDefault()).toInstant());
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PacienteController controller;

    @Test
    public void testFindById() {
        ResponseEntity<Paciente> response = controller.get(-0L);
        assertEquals("Esperava-se não ter encontrado nenhum registro", response.getStatusCode(), HttpStatus.NOT_FOUND);

        Paciente paciente = new Paciente();
        paciente.setCpf(CPF);
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
        response = controller.create(paciente);
        assertEquals("Esperava-se ter criado um doador teste", response.getStatusCode(), HttpStatus.CREATED);
        ResponseEntity response2 = controller.delete(response.getBody().getId());
        assertEquals("Esperava-se ter deletado um doador teste", response2.getStatusCode(), HttpStatus.OK);
    }

    public void testListaTodos() {

        ResponseEntity<List<Paciente>> response = controller.getAll(0, 10000);
        assertEquals("Esperava-se ", response.getStatusCode(), HttpStatus.NO_CONTENT);
        assert
    }
}

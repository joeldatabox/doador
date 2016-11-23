package br.pucminas;

import br.pucminas.controller.PacienteController;
import br.pucminas.exception.PacienteException;
import br.pucminas.exception.PacienteUnauthorizedException;
import br.pucminas.model.Paciente;
import br.pucminas.model.User;
import br.pucminas.repository.Pacientes;
import br.pucminas.service.AuthService;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DoadorApplicationTests {
    private static final String CPF = "707.555.251-16";
    private static final Date DT_NASC_VALID = Date.from(LocalDateTime.now().minusYears(18L).atZone(ZoneId.systemDefault()).toInstant());
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PacienteController controller;
    @Autowired
    private AuthService authService;

    @Test
    public void testAuthUserInvalid() {
        try {
            User user = new User();
            user.setUserName("us1");
            user.setPassWord("123");
            authService.auth(user);
        } catch (PacienteException ex) {
            assertEquals("esperava-se um erro de autenticação", new PacienteUnauthorizedException().getHttpStatus().value(), ex.getHttpStatus().value());
        }
    }

    @Test
    public void testAuthUserValid() {
        User user = new User();
        user.setUserName("user1");
        user.setPassWord("123");
        String token = null;
        try {
            token = authService.auth(user);
        } catch (PacienteException ex) {
            //ex.printStackTrace();
            token = null;
        }
        assertNotNull("esperava-se um token JWT", token);
    }


    @Test
    public void testFindById() {
        ResponseEntity<Paciente> response = controller.get(-0L);
        assertEquals("Esperava-se não ter encontrado nenhum registro", response.getStatusCode(), HttpStatus.NOT_FOUND);

        Paciente paciente = Pacientes.getPacientes().get(0);
        response = controller.create(paciente);
        assertEquals("Esperava-se ter criado um paciente teste", response.getStatusCode(), HttpStatus.CREATED);
        ResponseEntity response2 = controller.delete(response.getBody().getId());
        assertEquals("Esperava-se ter deletado um paciente teste", response2.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testListaTodos() {

        ResponseEntity<List<Paciente>> responseListagem = controller.getAll(0, 10000);
        assertEquals("Esperava-se que a respostas não contivesse intens", responseListagem.getStatusCode(), HttpStatus.NO_CONTENT);
        //adicionando pacientes para testes de listagens
        ResponseEntity<Paciente> respPaciente1 = controller.create(Pacientes.getPacientes().get(0));
        assertEquals("Esperava-se ter criado um paciente teste", respPaciente1.getStatusCode(), HttpStatus.CREATED);
        ResponseEntity<Paciente> respPaciente2 = controller.create(Pacientes.getPacientes().get(1));
        assertEquals("Esperava-se ter criado um paciente teste", respPaciente2.getStatusCode(), HttpStatus.CREATED);

        responseListagem = controller.getAll(0, 10000);
        assertEquals("Esperava-se que a resposta fosse 200", responseListagem.getStatusCode(), HttpStatus.OK);
        assertEquals("Esperava-se que a resposta contivesse 2 pacientes", responseListagem.getBody().size(), 2);

        ResponseEntity responseDelete = controller.delete(respPaciente1.getBody().getId());
        assertEquals("Esperava-se ter deletado um paciente teste", responseDelete.getStatusCode(), HttpStatus.OK);

        responseDelete = controller.delete(respPaciente2.getBody().getId());
        assertEquals("Esperava-se ter deletado um paciente teste", responseDelete.getStatusCode(), HttpStatus.OK);
        //assert
    }

    @Test
    public void testCreatePaciente() {
        ResponseEntity<Paciente> respPaciente1 = controller.create(Pacientes.getPacientes().get(0));
        assertEquals("Esperava-se ter criado um paciente teste", respPaciente1.getStatusCode(), HttpStatus.CREATED);

        ResponseEntity<Paciente> respPaciente2 = controller.create(Pacientes.getPacientes().get(0));
        assertEquals("Esperava-se ter dado um erro de conflito", respPaciente2.getStatusCode(), HttpStatus.CONFLICT);

        ResponseEntity responseDelete = controller.delete(respPaciente1.getBody().getId());
        assertEquals("Esperava-se ter deletado um paciente teste", responseDelete.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testUpdatePaciente() {
        ResponseEntity<Paciente> respCreate = controller.create(Pacientes.getPacientes().get(0));
        assertEquals("Esperava-se ter criado um paciente teste", respCreate.getStatusCode(), HttpStatus.CREATED);


        respCreate.getBody().setCpf("000000000");
        ResponseEntity<Paciente> respUpdate = controller.update(respCreate.getBody());
        assertEquals("Esperava-se ter um erro ao tentar atualizar cpf", respUpdate.getStatusCode(), HttpStatus.BAD_REQUEST);

        respCreate.getBody().setCpf(Pacientes.getPacientes().get(0).getCpf());
        respCreate.getBody().setNome("nome alterado");
        respUpdate = controller.update(respCreate.getBody());
        assertEquals("Esperava-se ter atualizado um registro com sucesso", respUpdate.getStatusCode(), HttpStatus.OK);

        ResponseEntity responseDelete = controller.delete(respCreate.getBody().getId());
        assertEquals("Esperava-se ter deletado um paciente teste", responseDelete.getStatusCode(), HttpStatus.OK);

        respUpdate = controller.update(respCreate.getBody());
        assertEquals("Esperava-se ter um erro de registro não encontrado", respUpdate.getStatusCode(), HttpStatus.BAD_REQUEST);

    }

    public void testCountPaciente() {
        ResponseEntity respCount = controller.count();
        assertEquals("Esperava-se ter executado uma requisição com sucesso", respCount.getStatusCode(), HttpStatus.OK);
        assertEquals("Esperava-se ter nenhum registro na base de dados", (Integer) respCount.getBody(), new Integer(0));

        ResponseEntity<Paciente> respCreate = controller.create(Pacientes.getPacientes().get(0));
        assertEquals("Esperava-se ter criado um paciente teste", respCreate.getStatusCode(), HttpStatus.CREATED);

        respCount = controller.count();
        assertEquals("Esperava-se ter executado uma requisição com sucesso", respCount.getStatusCode(), HttpStatus.OK);
        assertEquals("Esperava-se ter 1 registro na base de dados", (Integer) respCount.getBody(), new Integer(1));

        ResponseEntity responseDelete = controller.delete(respCreate.getBody().getId());
        assertEquals("Esperava-se ter deletado um paciente teste", responseDelete.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testFindByCpf() {
        ResponseEntity<Paciente> respCreate = controller.create(Pacientes.getPacientes().get(0));
        assertEquals("Esperava-se ter criado um paciente teste", respCreate.getStatusCode(), HttpStatus.CREATED);

        ResponseEntity respFind = controller.get(respCreate.getBody().getCpf());
        assertEquals("Esperava-se ter encontrado um resgistro", respFind.getStatusCode(), HttpStatus.OK);

        respFind = controller.get("054686205");
        assertEquals("Esperava-se não ter encontrado nenhum registro", respFind.getStatusCode(), HttpStatus.NOT_FOUND);

        ResponseEntity responseDelete = controller.delete(respCreate.getBody().getId());
        assertEquals("Esperava-se ter deletado um paciente teste", responseDelete.getStatusCode(), HttpStatus.OK);
    }
}

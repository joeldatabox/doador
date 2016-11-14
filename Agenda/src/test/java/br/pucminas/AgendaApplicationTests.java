package br.pucminas;


import br.pucminas.exception.AgendaConflictException;
import br.pucminas.exception.AgendaException;
import br.pucminas.exception.AgendaUnauthorizedException;
import br.pucminas.model.Agenda;
import br.pucminas.model.Agendamento;
import br.pucminas.model.User;
import br.pucminas.repository.Agendas;
import br.pucminas.services.AgendaService;
import br.pucminas.services.AgendamentoService;
import br.pucminas.services.AuthService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AgendaApplicationTests {
    @Autowired
    private AuthService authService;
    @Autowired
    AgendaService agendaService;
    @Autowired
    AgendamentoService agendamentoService;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testAuthUserInvalid() {
        try {
            User user = new User();
            user.setUserName("us1");
            user.setPassWord("123");
            authService.auth(user);
        } catch (AgendaException ex) {
            assertEquals("esperava-se um erro de autenticação", new AgendaUnauthorizedException().getHttpStatus().value(), ex.getHttpStatus().value());
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
        } catch (AgendaException ex) {
            //ex.printStackTrace();
            token = null;
        }
        assertNotNull("esperava-se um token JWT", token);
    }

    @Test
    public void testCreateAgenda() {
        Agenda agenda = null;
        try {
            agenda = Agendas.getAgendas().get(0);
            agenda.setQtdeLeito(null);
            agenda = agendaService.create(agenda);
            agendaService.delete(agenda);
            assertNull("esperava-se um erro ao salvar agenda. A mesma está com quantidade nula", agenda);
        } catch (AgendaException ex) {
            assertEquals("esperava-se um erro ao salvar agenda. A mesma está com quantidade nula", new AgendaException().getHttpStatus(), ex.getHttpStatus());
        }
        agenda = null;
        try {
            agenda = Agendas.agendaBuilder(new Agenda());
            agenda = agendaService.create(agenda);
            assertNotNull("esperava-se ter salvo um registro no banco", agenda.getId());
            assertNotEquals("esperava-se ter salvo um registro no banco", agenda.getId(), new Long(0));
            agendaService.delete(agenda);
        } catch (AgendaException ex) {
            assertNull("esperava-se ter deletado um registro do banco", ex);
        }
        agenda = null;
        Agenda agenda2;
        try {
            agenda = Agendas.agendaBuilder(new Agenda());
            agenda = agendaService.create(agenda);
            agenda2 = Agendas.agendaBuilder(new Agenda());
            agenda2 = agendaService.create(agenda2);
        } catch (AgendaException ex) {
            assertEquals("esperava-se ter um conflito pois o hemoecentro ja foi inserido", new AgendaConflictException().getHttpStatus(), ex.getHttpStatus());
            agendaService.delete(agenda);
        }
    }

    @Test
    public void testUpdateAgenda() {
        Agenda agenda = null;
        try {
            agenda = Agendas.agendaBuilder(new Agenda());
            agenda = agendaService.create(agenda);
            assertNotNull("esperava-se ter salvo um registro no banco", agenda.getId());
            assertNotEquals("esperava-se ter salvo um registro no banco", agenda.getId(), new Long(0));
            agenda.setObservacao("laskdjfçalsdkfjasd");
            agenda = agendaService.update(agenda);
        } catch (AgendaException ex) {
            assertNull("esperava-se ter atualizado um registro no banco de dados", ex);
        } finally {
            agendaService.delete(agenda);
        }
    }

    @Test
    public void testAgendamento() {
        Agenda agenda = Agendas.agendaBuilder(new Agenda());
        agenda = agendaService.create(agenda);
        assertNotNull("esperava-se ter salvo uma agenda no banco de dados", agenda.getId());
        assertNotEquals("esperava-se ter salvo uma agenda no banco de dados", agenda.getId(), new Long(0));
        //criando um novo agendamento
        Agendamento agendamento = new Agendamento();
        agendamento.setAgenda(agenda);
        agendamento.setDtAgendamento(new Date());
        agendamento.setIdPaciente(1L);

        agendamento = agendamentoService.create(agendamento);
        assertNotNull("esperava-se ter salvo um agendamento no banco de dados", agendamento.getId());
        assertNotEquals("esperava-se ter salvo um agendamento no banco de dados", agendamento.getId(), new Long(0));
        agendamentoService.delete(agendamento);

        //Regra um agendamento não pode existir sem uma agenda
        try {
            agendamento = new Agendamento();
            agendamento.setDtAgendamento(new Date());
            agendamento.setIdPaciente(1L);
            agendamento = agendamentoService.create(agendamento);
            assertNull("esperava-se um erro ao tentar salvar um agendamento sem uma agenda!", agendamento.getId());
            agendamentoService.delete(agendamento);
        } catch (AgendaException ex) {
            assertEquals("esperava-se um erro ao tentar salvar um agendamento sem uma agenda!", new AgendaException().getHttpStatus(), ex.getHttpStatus());
        }
        agendaService.delete(agenda);
    }

    @Test
    public void testAgendamentoForaDoHorario() {
        Agenda agenda = Agendas.agendaBuilder(new Agenda());
        agenda = agendaService.create(agenda);
        //Regra um agendamento não pode existir sem uma agenda
        try {
            Agendamento agendamento = new Agendamento();
            agendamento.setAgenda(agenda);
            agendamento.setDtAgendamento(getInvalidDate());
            agendamento.setIdPaciente(1L);
            agendamento = agendamentoService.create(agendamento);
            assertNull("esperava-se um erro ao tentar salvar um agendamento em um horario invalido", agendamento.getId());
            agendamentoService.delete(agendamento);
        } catch (AgendaException ex) {
            assertEquals("esperava-se um erro ao tentar salvar um agendamento sem uma agenda!", new AgendaException().getHttpStatus(), ex.getHttpStatus());
        }
        agendaService.delete(agenda);
    }

    private Date getInvalidDate() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2016-11-11T01:37:56");
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}

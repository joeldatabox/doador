package br.pucminas;


import br.pucminas.exception.AgendaConflictException;
import br.pucminas.exception.AgendaException;
import br.pucminas.exception.AgendaUnauthorizedException;
import br.pucminas.model.Agenda;
import br.pucminas.model.User;
import br.pucminas.repository.Agendas;
import br.pucminas.services.AgendaService;
import br.pucminas.services.AuthService;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AgendaApplicationTests {
    /* private
     @Before
     public void setup() throws Exception {
         this.mockMvc = webAppContextSetup(webApplicationContext).build();
     }

     @Test
     public void testSecurity() throws JsonProcessingException {
         //criando objeto json para a requisição
         Map<String, Object> body = new HashMap();
         body.put("userName", "user2");
         body.put("passWord", "1478");
         //criando cabeçalho da requisição
         HttpHeaders requestHeaders = new HttpHeaders();
         requestHeaders.setContentType(MediaType.APPLICATION_JSON);

         HttpEntity<String> httpEntity = new HttpEntity(OBJECT_MAPPER.writeValueAsString(body), requestHeaders);

         ResponseEntity<String> apiResponse = restTemplate.postForObject("https://127.0.01:5000/security/auth", httpEntity, ResponseEntity.class);
         apiResponse.getStatusCode();

     }

     public void testCreateAgenda() throws JsonProcessingException {
         Map<String, Object> body = new HashMap();
     }*/
    @Autowired
    private AuthService authService;
    @Autowired
    AgendaService agendaService;
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
    public void testUpdateAgenda(){
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
        }finally {
            agendaService.delete(agenda);
        }
    }
}

package br.pucminas;


import br.pucminas.repository.AgendaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AgendaApplicationTests {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Autowired
    private AgendaRepository repository;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private RestTemplate restTemplate = new RestTemplate();
    private MockMvc mockMvc;

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
    }
}

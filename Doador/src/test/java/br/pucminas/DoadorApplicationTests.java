package br.pucminas;

import br.pucminas.exception.PacienteConflictException;
import br.pucminas.exception.PacienteException;
import br.pucminas.model.Endereco;
import br.pucminas.model.Paciente;
import br.pucminas.service.PacienteService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DoadorApplicationTests {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PacienteService service;

    @Test
    public void contextLoads() {
    }

    private Paciente createPaciente() {
        logger.info("INSTANCIANDO UM DOADOR");
        Paciente entity = new Paciente();
        entity.setNome("Joel");
        entity.addEndereco(new Endereco(entity, "R. Padre Mariano", "75.904-439", "Primavera", "Rio Verde", "Observacao teste"));
        entity.setCpf("039.186.551-05");
        entity.setDtNascimento(new Date());
        entity.setNomeMae("Edivanete Rodrigues Moreira");
        entity.setRg("656555");
        return entity;
    }

    @Test
    public void testCreate() {
        logger.info("INICIANDO TESTE DE INSERÇÃO");

        Exception exception = null;

        Paciente entity = createPaciente();

        logger.info("TESTANDO INSERÇÃO DE REGISTRO");
        Paciente createdEntity = service.create(entity);

        Assert.assertNotNull("FALHA - ESPERAVA-SE UM OBJETO NÃO NULLO", createdEntity);
        Assert.assertNotNull("FALHA - ESPERAVA-SE UM ID NÃO NULO", createdEntity.getId());
        Assert.assertEquals("FALHA - O ATRIBUTO NOME COM VALOR INESPERADO", "Joel", createdEntity.getNome());

        logger.info("TESTANDO CONFLITO DE REGISTROS");
        entity.setId(null);
        try {
            service.create(entity);
        } catch (Exception e) {
            exception = e;
        }

        Assert.assertNotNull("FALHA - ESPERAVA-SE UMA EXCESSÃO", exception);
        Assert.assertTrue("FALHA - ESPERAVA-SE UM CONFLITO DE REGISTRO", exception instanceof PacienteConflictException);
    }

    @Test
    public void testFindAll() {
        logger.info("TESTANDO BUSCA DE REGISTRO DE DOADOR");
        logger.info("INSERINDO REGISTROS");
        testCreate();
        //Collection<Doador> list = service.findAll();

        // Assert.assertNotNull("FALHA - ESPERAVA-SE REGISTRO VINDOS DO BANCO DE DADOS", list);
        // Assert.assertEquals("FALHA - ESPERAVA-SE AO MENOS UM REGISTRO NA LISTA DE RESULTADOS", 1, list.size());
    }

    @Test
    public void testUpdateNotFound() {

        Exception exception = null;

        //Doador entity = createPaciente();

        logger.info("INSERINDO REGISTRO PARA TESTE");
        //entity = service.create(entity);

        logger.info("ATUALIZANDO CPF");
        //entity.setCpf("656565659");
        try {
            logger.info("ATUALIZANDO REGISTRO");
            //      service.update(entity);
        } catch (PacienteException e) {
            exception = e;
        }

        Assert.assertNotNull("FALHA - ESPERAVA-SE UMA EXCEPTION", exception);
        Assert.assertTrue("FALHA - ESPERAVA-SE UMA EXCEPTION 'PacienteException'", exception instanceof PacienteException);
    }

    @Test
    public void testDelete() {
        // service.create(createPaciente());

        Long id = new Long(1);

        // Doador entity = service.findById(id);

        //  Assert.assertNotNull("failure - expected not null", entity);

        //service.delete(entity);

        //  Collection<Doador> list = service.findAll();

        // Assert.assertEquals("failure - expected size", 0, list.size());

        // Doador deletedEntity = service.findById(id);

        // Assert.assertNull("failure - expected null", deletedEntity);

    }

}

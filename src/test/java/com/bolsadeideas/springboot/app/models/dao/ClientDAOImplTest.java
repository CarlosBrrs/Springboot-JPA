package com.bolsadeideas.springboot.app.models.dao;
/*
import com.bolsadeideas.springboot.app.models.entity.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class ClientDAOImplTest {

    @InjectMocks
    private ClientDAOImpl clientDAO;

    @Mock
    private EntityManager entityManager;


    @BeforeEach
    public void init() {
        clientDAO = new ClientDAOImpl();
    }

    @Test
    public void shouldFindAllClients() {
        //Give none
        List<Client> clientDaoImpl = List.of(new Client());

        //When
        when(entityManager.createQuery("from Client").getResultList()).thenReturn(clientDaoImpl);
        List<Client> clientDaoImpl1 = clientDAO.findAll();

        //Then
        assertNotNull(clientDaoImpl1);
    }

}

 */
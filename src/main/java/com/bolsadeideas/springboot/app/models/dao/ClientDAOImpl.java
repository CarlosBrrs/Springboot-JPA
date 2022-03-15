package com.bolsadeideas.springboot.app.models.dao;

import com.bolsadeideas.springboot.app.models.entity.Client;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ClientDAOImpl implements ClientDAO {

    @PersistenceContext
    private EntityManager entityManager;

    //El entity manager identifica la tabla que tiene mapeada la clase a la que hace referencia la operación
    @Override
    @Transactional
    public List<Client> findAll() {
        return entityManager.createQuery("from Client").getResultList();
    }

    @Override
    @Transactional
    public void save(Client client) {

        //El cliente no es null, y su id es mayor que 0 ?
        if (client != null && client.getId() > 0) {
            //actualiza con merge (cuando al método lo llama el edit) :
            entityManager.merge(client);
            // persiste el nuevo cliente
        } else {
            entityManager.persist(client);
        }
    }

    @Override
    @Transactional
    public Client findById(Long id) {
        return entityManager.find(Client.class, id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }
}

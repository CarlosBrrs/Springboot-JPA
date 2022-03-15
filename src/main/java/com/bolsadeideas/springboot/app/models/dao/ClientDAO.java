package com.bolsadeideas.springboot.app.models.dao;

import com.bolsadeideas.springboot.app.models.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientDAO extends JpaRepository<Client, Long> {

    /*Usados con el entityManager, si se va a extender de JpaRepository no se necesitan
    List<Client> findAll();

    void save(Client client);

    Client findById(Long id);

    void delete(Long id);*/
}

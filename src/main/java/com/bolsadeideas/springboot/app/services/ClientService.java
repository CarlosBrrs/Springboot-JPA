package com.bolsadeideas.springboot.app.services;

import com.bolsadeideas.springboot.app.models.entity.Client;

import java.util.List;

public interface ClientService {

    List<Client> findAll();

    void save(Client client);

    Client findById(Long id);

    void delete(Long id);
}

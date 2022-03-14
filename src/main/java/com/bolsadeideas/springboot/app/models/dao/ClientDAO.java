package com.bolsadeideas.springboot.app.models.dao;

import com.bolsadeideas.springboot.app.models.entity.Client;

import java.util.List;

public interface ClientDAO {

    List<Client> findAll();
}

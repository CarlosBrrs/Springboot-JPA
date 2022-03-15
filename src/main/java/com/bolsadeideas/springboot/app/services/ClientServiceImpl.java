package com.bolsadeideas.springboot.app.services;

import com.bolsadeideas.springboot.app.models.dao.ClientDAO;
import com.bolsadeideas.springboot.app.models.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDAO clientDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return clientDAO.findAll();
    }

    @Override
    @Transactional
    public void save(Client client) {
        clientDAO.save(client);
    }

    @Override
    @Transactional(readOnly = true)
    public Client findById(Long id) {
        return clientDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clientDAO.deleteById(id);
    }
    /*Para implementacion de ClientDAO con entityManager
    @Override
    @Transactional(readOnly = true)
    public Client findById(Long id) {
        return clientDAO.findById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clientDAO.delete(id);
    }*/
}

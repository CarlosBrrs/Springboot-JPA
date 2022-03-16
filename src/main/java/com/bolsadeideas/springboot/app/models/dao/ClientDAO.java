package com.bolsadeideas.springboot.app.models.dao;

import com.bolsadeideas.springboot.app.models.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

//public interface ClientDAO extends JpaRepository<Client, Long> {
public interface ClientDAO extends PagingAndSortingRepository<Client, Long> { //Hereda de JPA, asi que tengo todos los métodos disponibles, adicional a los de paginación

    /*Usados con el entityManager, si se va a extender de JpaRepository no se necesitan
    List<Client> findAll();

    void save(Client client);

    Client findById(Long id);

    void delete(Long id);*/
}

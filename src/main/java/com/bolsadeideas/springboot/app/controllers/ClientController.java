package com.bolsadeideas.springboot.app.controllers;

import com.bolsadeideas.springboot.app.models.dao.ClientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClientController {

    @Autowired
    private ClientDAO clientDAO;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public String getClients(Model model) {
        model.addAttribute("title", "Clients list");
        model.addAttribute("clients", clientDAO.findAll());
        return "clients";
    }
}

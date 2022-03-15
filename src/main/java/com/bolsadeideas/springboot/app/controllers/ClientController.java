package com.bolsadeideas.springboot.app.controllers;

import com.bolsadeideas.springboot.app.models.dao.ClientDAO;
import com.bolsadeideas.springboot.app.models.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Map;

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

    @GetMapping("/form")
    public String createClient(Map<String, Object> model) {
        model.put("title", "Form to create a new client");
        Client client = new Client();
        model.put("client", client);
        return "form";
    }

    @PostMapping("/form")
    public String saveClient(@Valid Client client, BindingResult result, Model model) {


        if (result.hasErrors()){
            //El cliente se pasa de forma automática si tiene el mismo nombre en la Clase y en el key
            model.addAttribute("title", "Please refill the form correctly");
            return "form";
        }
        clientDAO.save(client);
        return "redirect:/clients";
    }
}

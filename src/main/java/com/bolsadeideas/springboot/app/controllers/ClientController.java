package com.bolsadeideas.springboot.app.controllers;

import com.bolsadeideas.springboot.app.models.dao.ClientDAO;
import com.bolsadeideas.springboot.app.models.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes("client")
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
    public String saveClient(@Valid Client client, BindingResult result, Model model, SessionStatus status) {

        if (result.hasErrors()) {
            //El cliente se pasa de forma automática si tiene el mismo nombre en la Clase y en el key
            model.addAttribute("title", "Please refill the form correctly");
            return "form";
        }
        //El client me lo trae del metodo createClient con un client null o del metodo findClientById con un client lleno
        //El save implementa el crear nuevo o actualizar dependiendo de donde venga el client
        //Se necesita el SessionAtributtes ya que al momento de enviar el formulario, con los datos, el id va a ser null, no vacio ni lleno, y lanzará error
        clientDAO.save(client);
        status.setComplete();
        return "redirect:/clients";
    }

    @RequestMapping("/form/{id}")
    public String findClientById(@PathVariable Long id, Map<String, Object> model) {

        Client client = null;
        if (id > 0) {
            client = clientDAO.findById(id);
        } else return "redirect:/clients";

        model.put("title", "Edit client");
        model.put("client", client);
        return "form";
    }

    @RequestMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        if (id > 0) {
            clientDAO.delete(id);
        }
        return "redirect:/clients";
    }
}

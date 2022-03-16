package com.bolsadeideas.springboot.app.controllers;

import com.bolsadeideas.springboot.app.models.dao.ClientDAO;
import com.bolsadeideas.springboot.app.models.entity.Client;
import com.bolsadeideas.springboot.app.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes("client")
public class ClientController {

    /*Se inyecta el servicio, no el DAO
    @Autowired
    private ClientDAO clientDAO;*/

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public String getClients(@RequestParam(defaultValue = "0") int page, Model model) {

        Pageable pageRequest = PageRequest.of(page, 4);
        Page<Client> clientsPage = clientService.findAll(pageRequest);

        model.addAttribute("title", "Clients list");
        //model.addAttribute("clients", clientService.findAll()); Me muestra todos los clientes en una pagina

        //Me pagina la lista de e clientes
        model.addAttribute("clients", clientsPage);
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
    public String saveClient(@Valid Client client, BindingResult result, Model model, SessionStatus status, RedirectAttributes flashMessages) {

        if (result.hasErrors()) {
            //El cliente se pasa de forma automática si tiene el mismo nombre en la Clase y en el key
            model.addAttribute("title", "Please refill the form correctly");
            return "form";
        }
        String message = client.getId() != null ? "Client edited successfully" : "Client created successfully";
        //El client me lo trae del metodo createClient con un client null o del metodo findClientById con un client lleno
        //El save implementa el crear nuevo o actualizar dependiendo de donde venga el client
        //Se necesita el SessionAtributtes ya que al momento de enviar el formulario, con los datos, el id va a ser null, no vacio ni lleno, y lanzará error
        clientService.save(client);
        status.setComplete();
        flashMessages.addFlashAttribute("success", message);
        return "redirect:/clients";
    }

    @RequestMapping("/form/{id}")
    public String findClientById(@PathVariable Long id, Map<String, Object> model, RedirectAttributes flashMessages) {

        Client client = null;

        if (id > 0) {
            client = clientService.findById(id);
            if (client == null){ //Si el id no es cero pero es muy grande y no está en BD traerá un client null
                flashMessages.addFlashAttribute("error", "Client does not exist in our DB");
                return "redirect:/clients";
            }
        } else{
            flashMessages.addFlashAttribute("error", "Client ID cannot be zero");
            return "redirect:/clients";
        }

        model.put("title", "Edit client");
        model.put("client", client);
        return "form";
    }

    @RequestMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id, RedirectAttributes flashMessages) {
        if (id > 0) {
            clientService.delete(id);
            flashMessages.addFlashAttribute("success", "Client deleted successfully");
        }
        return "redirect:/clients";
    }
}

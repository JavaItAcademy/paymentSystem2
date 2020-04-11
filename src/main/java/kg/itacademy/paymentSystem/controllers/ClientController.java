package kg.itacademy.paymentSystem.controllers;

import kg.itacademy.paymentSystem.enttities.Client;
import kg.itacademy.paymentSystem.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getAll() {
        return clientService.getAll();
    }

    @PostMapping
    public Client save(@RequestBody Client payment) {
        return clientService.save(payment);
    }
    
    @GetMapping("/{id}")
    public Client getById(@PathVariable("id") Long id) {
        return clientService.getById(id);
    }
}

package com.dev.crm.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crm.contracts.services.ClientServiceContract;
import com.dev.crm.entities.Client;
import com.dev.crm.resterrorconfig.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientServiceContract clientService;

    public ClientController(ClientServiceContract clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/")
    public List<Client> findAll() {
        return this.clientService.findAll();
    }

    @PostMapping("/create")
    public Long create(@RequestBody Client client) {
        return this.clientService.create(client);
    }

    @PostMapping("/update")
    public Long update(@RequestBody Client client) {
        return this.clientService.update(client);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return this.clientService.delete(id);
    }

    @GetMapping("/{id}")
    public Client findById(@PathVariable Long id) {
        return this.clientService.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Не найден клиент с ID: " + id);
        });
    }

    @PostMapping("/make-editing-on")
    public Boolean makeEditingOn(@RequestParam Long id) {
        return this.clientService.makeEditingOn(id);
    }

    @PostMapping("/make-editing-off")
    public Boolean makeEditingOff(@RequestParam Long id) {
        return this.clientService.makeEditingOff(id);
    }

    @GetMapping("/search")
    public List<Client> search(@RequestParam String search) {
        return this.clientService.search(search);
    }
}

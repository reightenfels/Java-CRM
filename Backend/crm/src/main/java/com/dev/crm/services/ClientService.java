package com.dev.crm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dev.crm.contracts.services.ClientServiceContract;
import com.dev.crm.entities.Client;
import com.dev.crm.repositories.ClientRepository;

@Service
public class ClientService implements ClientServiceContract {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
        return this.clientRepository.findAll();
    }

    @Override
    public Long create(Client client) {
        Long clientId = this.clientRepository.save(client).getId();
        client.setId(clientId);
        return clientId;
    }

    @Override
    public Long update(Client client) {
        return this.clientRepository.save(client).getId();
    }

    @Override
    public Long delete(Long clientId) {
        this.clientRepository.deleteById(clientId);
        return clientId;
    }

    @Override
    public Optional<Client> findById(Long clientId) {
        return this.clientRepository.findById(clientId);
    }

    @Override
    public Boolean makeEditingOn(Long clientId) {
        int updatedCount = this.clientRepository.makeEditingOn(clientId);

        return updatedCount > 0;
    }

    @Override
    public Boolean makeEditingOff(Long clientId) {
        int updatedCount = this.clientRepository.makeEditingOff(clientId);

        return updatedCount > 0;
    }

    public List<Client> search(String search) {
        return this.clientRepository.search("%" + search + "%");
    }
}

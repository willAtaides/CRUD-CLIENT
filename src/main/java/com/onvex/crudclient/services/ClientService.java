package com.onvex.crudclient.services;

import com.onvex.crudclient.dto.ClientDTO;
import com.onvex.crudclient.entities.Client;
import com.onvex.crudclient.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO findByid(Long id){
        Client client = repository.findById(id).get();
        return new ClientDTO(client);
    }

}

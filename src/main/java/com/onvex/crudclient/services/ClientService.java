package com.onvex.crudclient.services;

import com.onvex.crudclient.dto.ClientDTO;
import com.onvex.crudclient.entities.Client;
import com.onvex.crudclient.repositories.ClientRepository;
import com.onvex.crudclient.services.exceptions.DatabaseException;
import com.onvex.crudclient.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO findByid(Long id){
        Client client = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Erro: cliente inexistente para o ID fornecido."));
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> result = repository.findAll(pageable);
        return result.map(x -> new ClientDTO(x));
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto){

        try {
            Client entity = new Client();
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ClientDTO(entity);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Não foi possível adicionar: O CPF informado já está cadastrado para outro cliente.");
        }
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public ClientDTO update(Long id, ClientDTO dto){
        try {
            Client entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ClientDTO(entity);
        }
        catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Não foi possível atualizar: cliente com o ID informado não existe.");
        }
    }

    private void copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Não foi possível deletar: cliente com o ID informado não existe.");
        }
            repository.deleteById(id);
    }

}

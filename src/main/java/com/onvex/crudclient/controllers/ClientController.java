package com.onvex.crudclient.controllers;

import com.onvex.crudclient.dto.ClientDTO;
import com.onvex.crudclient.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
    @Autowired
    private ClientService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
        ClientDTO dto= service.findByid(id);
        return ResponseEntity.ok(dto);
    }
    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable){
        Page<ClientDTO> dto= service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> insert(@Valid @RequestBody ClientDTO dto){
        dto= service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id, @Valid @RequestBody ClientDTO dto ){
        dto= service.update(id, dto);
        return ResponseEntity.ok(dto);
    }
    @DeleteMapping (value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

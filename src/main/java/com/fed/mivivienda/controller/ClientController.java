package com.fed.mivivienda.controller;

import com.fed.mivivienda.entity.Client;
import com.fed.mivivienda.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service){ this.service = service; }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client c){
        return ResponseEntity.ok(service.create(c));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client c){
        return ResponseEntity.ok(service.update(id, c));
    }

    @GetMapping
    public ResponseEntity<List<Client>> list(){
        return ResponseEntity.ok(service.list());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}

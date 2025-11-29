package com.fed.mivivienda.service;

import com.fed.mivivienda.entity.Client;
import com.fed.mivivienda.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository repo;

    public ClientService(ClientRepository repo){ this.repo = repo; }

    public Client create(Client c){ return repo.save(c); }
    public Client update(Long id, Client c){
        Client db = repo.findById(id).orElseThrow();
        db.setNombre(c.getNombre());
        db.setApellido(c.getApellido());
        db.setDni(c.getDni());
        db.setDireccion(c.getDireccion());
        db.setTelefono(c.getTelefono());
        db.setEmail(c.getEmail());
        db.setIngresosMensuales(c.getIngresosMensuales());
        db.setEstadoCivil(c.getEstadoCivil());
        db.setOcupacion(c.getOcupacion());
        return repo.save(db);
    }
    public List<Client> list(){ return repo.findAll(); }
    public void delete(Long id){ repo.deleteById(id); }
}

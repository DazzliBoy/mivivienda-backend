package com.fed.mivivienda.service;

import com.fed.mivivienda.entity.Property;
import com.fed.mivivienda.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {
    private final PropertyRepository repo;

    public PropertyService(PropertyRepository repo){ this.repo = repo; }

    public Property create(Property p){ return repo.save(p); }
    public Property update(Long id, Property p){
        Property db = repo.findById(id).orElseThrow();
        db.setDireccion(p.getDireccion());
        db.setTipo(p.getTipo());
        db.setArea(p.getArea());
        db.setPrecio(p.getPrecio());
        db.setDescripcion(p.getDescripcion());
        return repo.save(db);
    }
    public List<Property> list(){ return repo.findAll(); }
    public void delete(Long id){ repo.deleteById(id); }
}

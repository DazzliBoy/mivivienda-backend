package com.fed.mivivienda.controller;

import com.fed.mivivienda.entity.Property;
import com.fed.mivivienda.service.PropertyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService service;
    public PropertyController(PropertyService service){ this.service = service; }

    @PostMapping
    public ResponseEntity<Property> create(@RequestBody Property p){
        return ResponseEntity.ok(service.create(p));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Property> update(@PathVariable Long id, @RequestBody Property p){
        return ResponseEntity.ok(service.update(id, p));
    }

    @GetMapping
    public ResponseEntity<List<Property>> list(){
        return ResponseEntity.ok(service.list());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}

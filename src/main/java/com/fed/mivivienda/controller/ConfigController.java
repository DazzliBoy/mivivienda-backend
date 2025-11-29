package com.fed.mivivienda.controller;

import com.fed.mivivienda.entity.AppConfigEntity;
import com.fed.mivivienda.repository.OperationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/config")
public class ConfigController {

    // Esta clase puede ampliarse; aquí solo mostramos un endpoint estático de ejemplo

    @GetMapping("/ping")
    public ResponseEntity<String> ping(){
        return ResponseEntity.ok("config-ok");
    }
}

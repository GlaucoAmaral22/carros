package com.carros.controller;


import com.carros.entity.CarroEntity;
import com.carros.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {

    @Autowired
    private CarroService service;

    @GetMapping("/tipo/{tipo}")
    public Iterable<CarroEntity> get(@PathVariable("tipo") String tipo) {
        return service.getCarroByTipo(tipo);
    }

    @GetMapping("/{id}")
    public Optional<CarroEntity> get(@PathVariable("id") Long id) {
        return service.getCarroById(id);
    }

    @GetMapping()
    public Iterable<CarroEntity> get() {
        return service.getCarros();
    }







}

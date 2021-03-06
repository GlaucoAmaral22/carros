package com.carros.controller;


import com.carros.domain.CarroDomain;
import com.carros.entity.CarroEntity;
import com.carros.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {

    @Autowired
    private CarroService service;

    @GetMapping()
    @Secured({"ROLE_USER"})
    public ResponseEntity<List<CarroDomain>> getAllCarros() {
        return ResponseEntity.ok(service.getCarros());
        //return new ResponseEntity(service.getCarros(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_USER"})
    public ResponseEntity<CarroDomain> getCarroById(@PathVariable("id") Long id) {
        CarroDomain carroDomain = service.getCarroById(id);
        return ResponseEntity.ok(carroDomain);

    }

    @GetMapping("/tipo/{tipo}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<CarroDomain>> getCarrosByTipo(@PathVariable("tipo") String tipo) {
        List<CarroDomain> carroDomains = service.getCarroByTipo(tipo);
        return carroDomains.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carroDomains);
    }

    @PostMapping
    @Secured({"ROLE_USER"})
    public ResponseEntity post(@RequestBody CarroEntity carro) {
        CarroDomain c = service.insert(carro);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @Secured({"ROLE_USER"})
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody CarroEntity carro) {
        System.out.printf("valor id: " + id);
        CarroDomain c = service.update(carro, id);

        return !Objects.isNull(c) ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_USER"})
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    //Anotações para Cache:
    // @Cacheable
    // @CacheEvict
    // @CachePut.
}

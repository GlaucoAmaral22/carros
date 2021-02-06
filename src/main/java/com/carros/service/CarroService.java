package com.carros.service;

import com.carros.entity.CarroEntity;
import com.carros.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public Optional<CarroEntity> getCarroById(Long id){
        return carroRepository.findById(id);
    }

    public Iterable<CarroEntity> getCarros(){
        return carroRepository.findAll();
    }


    public Iterable<CarroEntity> getCarroByTipo(String tipo) {
        return carroRepository.findByTipo(tipo);
    }
}

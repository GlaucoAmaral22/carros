package com.carros.repository;

import com.carros.entity.CarroEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CarroRepository extends CrudRepository<CarroEntity, Long> {
    public Iterable<CarroEntity> findByTipo(String tipo);
}

package com.carros.repository;

import com.carros.entity.CarroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarroRepository extends JpaRepository<CarroEntity, Long> {
    public List<CarroEntity> findByTipo(String tipo);
}

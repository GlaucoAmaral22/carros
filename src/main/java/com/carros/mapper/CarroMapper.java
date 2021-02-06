package com.carros.mapper;

import com.carros.domain.CarroDomain;
import com.carros.entity.CarroEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CarroMapper {

    List<CarroDomain> carrosEntityToCarro(List<CarroEntity> carros);

    CarroDomain carroEntityToCarro(CarroEntity carro);

}

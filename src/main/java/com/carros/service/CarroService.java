package com.carros.service;

import com.carros.mapper.CarroMapper;
import com.carros.domain.CarroDomain;
import com.carros.entity.CarroEntity;
import com.carros.repository.CarroRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository rep;

    public CarroDomain getCarroById(Long id){
        Optional<CarroEntity> carroEntityOpt = rep.findById(id);
        if(carroEntityOpt.isPresent())
            return Mappers.getMapper(CarroMapper.class).carroEntityToCarro(carroEntityOpt.get());
        return null;
    }

    public List<CarroDomain> getCarros(){
        List<CarroEntity> carroEntities = rep.findAll();

        List<CarroDomain> carrosDomain = Mappers.getMapper(CarroMapper.class).carrosEntityToCarro(carroEntities);

        return carrosDomain;
    }


    public List<CarroDomain> getCarroByTipo(String tipo) {

        List<CarroEntity> carroEntities = rep.findByTipo(tipo);

        List<CarroDomain> carrosDomain = Mappers.getMapper(CarroMapper.class).carrosEntityToCarro(carroEntities);

        return carrosDomain;
    }

    public CarroDomain insert(CarroEntity carro) {
        Assert.isNull(carro.getId(), "Não foi possível inserir registro");

        return Mappers.getMapper(CarroMapper.class).carroEntityToCarro(rep.save(carro));
    }

    public CarroDomain update(CarroEntity carro, Long id) {
        Assert.notNull(id, "Não foi possível atualizar registro");

        Optional<CarroEntity> optional = rep.findById(id);

        if(optional.isPresent()){
            CarroEntity db = optional.get();
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            return Mappers.getMapper(CarroMapper.class).carroEntityToCarro(rep.save(db));
        }
        return null;
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }

}

package com.carros.service;

import com.carros.mapper.CarroMapper;
import com.carros.domain.CarroDomain;
import com.carros.entity.CarroEntity;
import com.carros.repository.CarroRepository;
import com.carros.exception.ObjectNotFoundException;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository rep;

    @Cacheable(cacheNames = "CarroById", key = "#id")
    public CarroDomain getCarroById(Long id) throws ObjectNotFoundException {
        Optional<CarroEntity> carroEntityOpt = rep.findById(id);
        if (carroEntityOpt.isPresent())
            return Mappers.getMapper(CarroMapper.class).carroEntityToCarro(carroEntityOpt.get());
        throw new ObjectNotFoundException("Carro não encontrado.");
    }

    @Cacheable(cacheNames = "Carros", key = "")
    public List<CarroDomain> getCarros() {
        System.out.println("Chamada no lista carros.");
        List<CarroEntity> carroEntities = rep.findAll();

        List<CarroDomain> carrosDomain = Mappers.getMapper(CarroMapper.class).carrosEntityToCarro(carroEntities);

        return carrosDomain;
    }

    @Cacheable(cacheNames = "CarrosTipo", key = "#tipo")
    public List<CarroDomain> getCarroByTipo(String tipo) {

        List<CarroEntity> carroEntities = rep.findByTipo(tipo);

        List<CarroDomain> carrosDomain = Mappers.getMapper(CarroMapper.class).carrosEntityToCarro(carroEntities);

        return carrosDomain;
    }

    @CacheEvict(cacheNames = {"Carro", "CarroTipo"}, allEntries = true)
    public CarroDomain insert(CarroEntity carro) {
        Assert.isNull(carro.getId(), "Não foi possível inserir registro");

        return Mappers.getMapper(CarroMapper.class).carroEntityToCarro(rep.save(carro));
    }

    @CachePut(cacheNames = {"CarroById"}, key = "#id")
    public CarroDomain update(CarroEntity carro, Long id) {
        Assert.notNull(id, "Não foi possível atualizar registro");

        Optional<CarroEntity> optional = rep.findById(id);

        if (optional.isPresent()) {
            CarroEntity db = optional.get();
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            return Mappers.getMapper(CarroMapper.class).carroEntityToCarro(rep.save(db));
        }
        return null;
    }

    @CacheEvict(cacheNames = "CarroById", key = "#id")
    public void delete(Long id) {
        rep.deleteById(id);
    }

}

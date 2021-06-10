package com.gerarifa.persistence.entity.factory;

import com.gerarifa.domain.model.Sorteio;
import com.gerarifa.persistence.entity.SorteioEntity;
import com.gerarifa.persistence.repository.SorteioJpaRepository;
import org.mapstruct.ObjectFactory;
import org.springframework.stereotype.Component;

@Component
public class SorteioEntityFactory {

    private final SorteioJpaRepository repository;

    public SorteioEntityFactory(SorteioJpaRepository repository) {
        this.repository = repository;
    }

    @ObjectFactory
    public SorteioEntity create(Sorteio sorteio){
        if(repository != null && sorteio.getId() != null){
            return repository.findById(sorteio.getId()).orElse(new SorteioEntity());
        }
        return new SorteioEntity();
    }
}

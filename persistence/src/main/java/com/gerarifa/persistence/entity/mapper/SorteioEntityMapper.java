package com.gerarifa.persistence.entity.mapper;

import com.gerarifa.domain.model.Sorteio;
import com.gerarifa.persistence.entity.SorteioEntity;
import com.gerarifa.persistence.entity.factory.SorteioEntityFactory;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(builder = @Builder(disableBuilder = true), uses = {SorteioEntityFactory.class},
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SorteioEntityMapper {

    SorteioEntity fromModel(Sorteio sorteio);

    Sorteio toModel(SorteioEntity sorteioEntity);

}

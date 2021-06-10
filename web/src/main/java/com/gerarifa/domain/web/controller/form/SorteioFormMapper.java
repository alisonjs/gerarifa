package com.gerarifa.domain.web.controller.form;

import com.gerarifa.domain.model.Sorteio;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(builder = @Builder(disableBuilder = true), nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface SorteioFormMapper {

    SorteioForm fromModel(Sorteio sorteio);

    Sorteio toModel(SorteioForm sorteioForm);

}

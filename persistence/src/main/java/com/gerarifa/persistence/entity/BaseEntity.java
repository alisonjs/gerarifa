package com.gerarifa.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Version
    private long version;

}

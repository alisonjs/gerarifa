package com.gerarifa.persistence.repository;

import com.gerarifa.domain.model.status.StatusSorteio;
import com.gerarifa.persistence.entity.SorteioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface SorteioJpaRepository extends JpaRepository<SorteioEntity, String> {

    @Modifying
    @Query(value = "update SorteioEntity sorteio set sorteio.status = :status where sorteio.id = :id")
    void updateStatus(@Param("id") String id, @Param("status") StatusSorteio status);

    @Modifying
    @Query(value = "update SorteioEntity sorteio set sorteio.dataRealizacaoSorteio = :novaData where sorteio.id = :id")
    void updateDataRealizacaSorteio(@Param("id") String id, @Param("novaData") LocalDate novaData);
}

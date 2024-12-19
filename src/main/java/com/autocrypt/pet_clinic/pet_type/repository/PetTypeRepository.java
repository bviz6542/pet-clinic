package com.autocrypt.pet_clinic.pet_type.repository;

import com.autocrypt.pet_clinic.pet_type.domain.PetType;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PetTypeRepository extends Repository<PetType, Long> {

    @Query("select id, name from pet_type where id in (:typeIdList)")
    List<PetType> findByIds(@Param("typeIdList") List<Long> typeIdList);
}
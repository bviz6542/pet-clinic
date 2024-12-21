package com.autocrypt.pet_clinic.pet_type.repository;

import com.autocrypt.pet_clinic.pet_type.domain.PetType;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PetTypeRepository extends ListCrudRepository<PetType, Long> {

    @Query("select pet_type_id, name from pet_type where pet_type.pet_type_id in (:typeIdList)")
    List<PetType> findByIds(@Param("typeIdList") List<Long> typeIdList);
}

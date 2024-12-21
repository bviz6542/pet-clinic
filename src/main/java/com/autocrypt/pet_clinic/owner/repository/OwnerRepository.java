package com.autocrypt.pet_clinic.owner.repository;

import com.autocrypt.pet_clinic.owner.domain.Owner;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OwnerRepository extends ListCrudRepository<Owner, Long> {

    @Query("""
        select o.owner_id, o.first_name, o.last_name, o.address, o.city, o.telephone,
           p.pet_id as pet_id, p.name as pet_name, p.birth_date as pet_birth_date, p.pet_type_id as pet_type_id
        from owner o
        left join pet p on o.owner_id = p.owner_id
        where o.last_name like concat(:lastName,'%')
    """)
    List<OwnerWithPetRaw> findOwnersWithPetsByLastNameRaw(@Param("lastName") String lastName);
}

package com.autocrypt.pet_clinic.owner.repository;

import com.autocrypt.pet_clinic.owner.domain.Owner;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OwnerRepository extends Repository<Owner, Long> {

    @Query("""
        select o.id, o.first_name, o.last_name, o.address, o.city, o.telephone,
           p.id as pet_id, p.name as pet_name, p.birth_date as pet_birth_date, p.type_id as pet_type_id
        from owner o
        left join pet p on o.id = p.owner_id
    """)
    List<OwnerWithPetRaw> findAllWithPetsRaw();

    @Query("""
        select o.id, o.first_name, o.last_name, o.address, o.city, o.telephone,
           p.id as pet_id, p.name as pet_name, p.birth_date as pet_birth_date, p.type_id as pet_type_id
        from owner o
        left join pet p on o.id = p.owner_id
        where o.last_name like :lastName
    """)
    List<OwnerWithPetRaw> findOwnersWithPetsByLastNameRaw(@Param("lastName") String lastName);
}

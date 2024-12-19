package com.autocrypt.pet_clinic.vet.repository;

import com.autocrypt.pet_clinic.vet.domain.Vet;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface VetRepository extends Repository<Vet, Long> {

    @Query("""
        select v.id as vet_id, v.first_name, v.last_name,
           s.id as specialty_id, s.name as specialty_name
        from vet v
        left join vet_specialty vs on v.id = vs.vet
        left join specialty s on s.id = vs.specialty
    """)
    List<VetWithSpecialtiesRaw> findAllWithSpecialtiesRaw();
}
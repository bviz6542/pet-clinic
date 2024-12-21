package com.autocrypt.pet_clinic.vet.repository;

import com.autocrypt.pet_clinic.vet.domain.Vet;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface VetRepository extends Repository<Vet, Long> {

    @Query("""
        select v.vet_id, v.first_name, v.last_name,
           s.specialty_id, s.name as specialty_name
        from vet v
        left join vet_specialty vs on v.vet_id = vs.vet_id
        left join specialty s on s.specialty_id = vs.specialty_id
    """)
    List<VetWithSpecialtiesRaw> findAllWithSpecialtiesRaw();
}

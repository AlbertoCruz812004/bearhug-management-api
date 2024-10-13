package com.bearhug.persistence.repository;

import com.bearhug.persistence.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>{

    boolean existsByFolio(UUID folio);

    Optional<EmployeeEntity> findEmployeeEntitiesByFolio(UUID folio);

    void deleteByFolio(UUID folio);
}

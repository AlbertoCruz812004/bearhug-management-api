package com.bearhug.persistence.repository;

import com.bearhug.persistence.entity.EmployeeEntity;
import com.bearhug.persistence.repository.custom.repository.CustomEmployeeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>, CustomEmployeeRepository {

    Optional<EmployeeEntity> findEmployeeEntitiesByFolio(UUID folio);
    void deleteEmployeeEntitiesByFolio(UUID folio);

    boolean existsEmployeeEntityByFolio(UUID folio);

    @Procedure
    boolean verifyExistsEmployeeByFolio(String folio);
}

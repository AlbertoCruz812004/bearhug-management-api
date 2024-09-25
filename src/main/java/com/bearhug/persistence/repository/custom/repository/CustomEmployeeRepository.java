package com.bearhug.persistence.repository.custom.repository;

import com.bearhug.persistence.entity.EmployeeEntity;

import java.util.UUID;

public interface CustomEmployeeRepository {

    boolean existsEmployeeByFolio(UUID folio);

    EmployeeEntity findEmployeeByFolio(UUID folio);
}

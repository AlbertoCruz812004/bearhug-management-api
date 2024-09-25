package com.bearhug.persistence.repository.custom.implementation;

import com.bearhug.persistence.entity.EmployeeEntity;
import com.bearhug.persistence.repository.custom.repository.CustomEmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EmployeeCriteriaRepository implements CustomEmployeeRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public boolean
    existsEmployeeByFolio(UUID folio) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);

        Root<EmployeeEntity> root = query.from(EmployeeEntity.class);

        Predicate equalFolio = criteriaBuilder.equal(root.get("folio"), folio);

        query.select(criteriaBuilder.count(root)).where(equalFolio);
        Long count = entityManager.createQuery(query).getSingleResult();

        return count > 0;
    }

    @Override
    public EmployeeEntity findEmployeeByFolio(UUID folio) {

        return null;
    }
}


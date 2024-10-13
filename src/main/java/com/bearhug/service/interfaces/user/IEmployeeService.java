package com.bearhug.service.interfaces.user;

import com.bearhug.persistence.entity.EmployeeEntity;
import com.bearhug.presentation.dto.SimpleResponse;
import com.bearhug.presentation.dto.user.EmployeeListPageable;
import com.bearhug.presentation.dto.user.EmployeePageableResponse;
import com.bearhug.presentation.dto.user.EmployeeRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import java.util.UUID;

public interface IEmployeeService {

    //PagedModel<EntityModel<EmployeePageableResponse>> showAllEmployees(Pageable pageable);
    SimpleResponse<?> searchEmployee(UUID folio);

    EmployeeListPageable findAllEmployees(Pageable pageable);

    SimpleResponse<Boolean> deleteEmployee(UUID folio);

    EmployeeEntity addEmployee(EmployeeRequest employeeRequest);
}

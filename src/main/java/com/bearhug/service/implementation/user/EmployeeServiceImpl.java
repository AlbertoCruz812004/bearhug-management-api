package com.bearhug.service.implementation.user;

import com.bearhug.persistence.entity.EmployeeEntity;
import com.bearhug.persistence.entity.PersonEntity;
import com.bearhug.persistence.entity.UserEntity;
import com.bearhug.persistence.repository.EmployeeRepository;
import com.bearhug.presentation.dto.SimpleResponse;
import com.bearhug.presentation.dto.user.EmployeePageableResponse;
import com.bearhug.presentation.dto.user.EmployeeRequest;
import com.bearhug.service.interfaces.user.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PagedResourcesAssembler<EmployeePageableResponse> pagedResourcesAssembler;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PagedResourcesAssembler<EmployeePageableResponse> pagedResourcesAssembler) {
        this.employeeRepository = employeeRepository;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @Override
    public EmployeeEntity addEmployee(EmployeeRequest employeeRequest) {
        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .folio(UUID.randomUUID())
                .person(PersonEntity.builder()
                        .name(employeeRequest.name())
                        .lastname(employeeRequest.lastname())
                        .age(employeeRequest.age())
                        .curp(employeeRequest.curp())
                        .user(UserEntity.builder()
                                .id(employeeRequest.idUser())
                                .build()
                        )
                        .build()
                )
                .build();
        return employeeRepository.save(employeeEntity);
    }

    @Override
    public SimpleResponse<Boolean> deleteEmployee(UUID folio) {
        if (employeeRepository.existsEmployeeByFolio(folio)) {
            employeeRepository.deleteEmployeeEntitiesByFolio(folio);
            return new SimpleResponse<>("Employee successfully eliminated", true);
        }
        return new SimpleResponse<>("It was not possible to delete this employee", false);
    }

    @Override
    public PagedModel<EntityModel<EmployeePageableResponse>> showAllEmployees(Pageable pageable) {
        Page<EmployeeEntity> employeeEntities = employeeRepository.findAll(pageable);
        List<EmployeePageableResponse> employeeResponses = employeeEntities.stream()
                .map(employee -> new EmployeePageableResponse(
                        employee.getFolio(),
                        employee.getPerson().getName(),
                        employee.getPerson().getLastname(),
                        employee.getPerson().getCurp(),
                        employee.getPerson().getAge()
                )).toList();

        Page<EmployeePageableResponse> pageResponse = new PageImpl<>(employeeResponses, pageable, employeeResponses.size());

        return pagedResourcesAssembler.toModel(pageResponse);
    }

    @Override
    public SimpleResponse<?> searchEmployee(UUID folio) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findEmployeeEntitiesByFolio(folio);
        if (employeeEntity.isPresent())
            return new SimpleResponse<>("Employee successfully found", employeeEntity.get());
        return new SimpleResponse<>("The employee with this folio cannot be found.", folio);
    }
}

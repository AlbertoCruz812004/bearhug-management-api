package com.bearhug.service.implementation.user;

import com.bearhug.persistence.entity.EmployeeEntity;
import com.bearhug.persistence.entity.PersonEntity;
import com.bearhug.persistence.entity.UserEntity;
import com.bearhug.persistence.repository.EmployeeRepository;
import com.bearhug.presentation.dto.SimpleResponse;
import com.bearhug.presentation.dto.user.EmployeeListPageable;
import com.bearhug.presentation.dto.user.EmployeePageableResponse;
import com.bearhug.presentation.dto.user.EmployeeRequest;
import com.bearhug.presentation.dto.user.EmployeeResponse;
import com.bearhug.service.interfaces.user.IEmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public SimpleResponse<?> searchEmployee(UUID folio) {
        Optional<EmployeeEntity> employee = employeeRepository.findEmployeeEntitiesByFolio(folio);
        if (employee.isPresent()) {
            return new SimpleResponse<EmployeeResponse>("success",
                    new EmployeeResponse(
                            employee.get().getFolio(),
                            employee.get().getPerson().getName(),
                            employee.get().getPerson().getLastname(),
                            employee.get().getPerson().getAge(),
                            employee.get().getPerson().getCurp()
                    ));
        }
        return new SimpleResponse<Boolean>("No employee with this folio was found.", false);
    }

    @Override
    public EmployeeListPageable findAllEmployees(Pageable pageable) {
        Page<EmployeeEntity> page = employeeRepository.findAll(pageable);
        List<EmployeePageableResponse> employees = page.getContent().stream()
                .map(employee -> {
                    return EmployeePageableResponse.builder()
                            .name(employee.getPerson().getName())
                            .lastname(employee.getPerson().getLastname())
                            .age(employee.getPerson().getAge())
                            .folio(employee.getFolio())
                            .build();
                }).toList();
        Long size = page.getTotalElements();
        Long totalPages = (long) page.getTotalPages();
        return new EmployeeListPageable(employees, size, totalPages);
    }

    @Transactional
    @Override
    public SimpleResponse<Boolean> deleteEmployee(UUID folio) {
        if (employeeRepository.existsByFolio(folio)) {
            employeeRepository.deleteByFolio(folio);
            return new SimpleResponse<Boolean>("success", true);
        }
        return new SimpleResponse<>("The employee could not be deleted", false);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public EmployeeEntity addEmployee(EmployeeRequest employeeRequest) {
        System.out.println(employeeRequest.toString());
        return employeeRepository.save(EmployeeEntity.builder()
                .folio(UUID.randomUUID())
                .person(PersonEntity.builder()
                        .name(employeeRequest.name())
                        .curp(employeeRequest.curp())
                        .age(employeeRequest.age())
                        .lastname(employeeRequest.lastname())
                        .user(new UserEntity(employeeRequest.id()))
                        .build())
                .build());
    }
}

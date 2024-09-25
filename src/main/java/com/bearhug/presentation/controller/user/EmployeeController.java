package com.bearhug.presentation.controller.user;

import com.bearhug.presentation.dto.user.EmployeePageableResponse;
import com.bearhug.presentation.dto.user.EmployeeRequest;
import com.bearhug.service.interfaces.user.IEmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final IEmployeeService employeeService;

    @Autowired
    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<EmployeePageableResponse>>> getAllEmployees(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(employeeService.showAllEmployees(pageable));
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employeeService.addEmployee(employeeRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findEmployee(@PathVariable(name = "id") UUID folio) {
        return ResponseEntity.ok(employeeService.searchEmployee(folio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(name = "id") UUID folio) {
        return ResponseEntity.ok(employeeService.deleteEmployee(folio));
    }
}

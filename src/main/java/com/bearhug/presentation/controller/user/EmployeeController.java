package com.bearhug.presentation.controller.user;

import com.bearhug.presentation.dto.SimpleResponse;
import com.bearhug.presentation.dto.user.EmployeeListPageable;
import com.bearhug.presentation.dto.user.EmployeeRequest;
import com.bearhug.service.interfaces.user.IEmployeeService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final IEmployeeService employeeService;

    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllEmployees(Pageable pageable) {
        return ResponseEntity.ok(employeeService.findAllEmployees(pageable));
    }

    @GetMapping("/{folio}")
    public ResponseEntity<?> findEmployee(@PathVariable UUID folio) {
        var response = employeeService.searchEmployee(folio);
        if (!response.message().equals("success")) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> createNewEmployee(@RequestBody EmployeeRequest employeeRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addEmployee(employeeRequest));
    }

    @DeleteMapping("/{folio}")
    public ResponseEntity<?> deleteEmployee(@PathVariable UUID folio){
        return ResponseEntity.ok(employeeService.deleteEmployee(folio));
    }
}

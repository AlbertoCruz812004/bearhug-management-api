package com.bearhug;

import com.bearhug.persistence.entity.EmployeeEntity;
import com.bearhug.persistence.repository.EmployeeRepository;
import com.bearhug.presentation.dto.SimpleResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class BearhugManagementApplicationTests {

	@Mock
    private EmployeeRepository employeeRepository;


    private UUID folio;

	@Test
    void contextLoads() {
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        folio = UUID.randomUUID();
    }

	@Test
	void testExistsEmployeeCriteria(){
        System.out.println(employeeRepository.existsById(2L));
	}
}
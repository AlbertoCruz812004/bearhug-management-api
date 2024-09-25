package com.bearhug;

import com.bearhug.persistence.entity.EmployeeEntity;
import com.bearhug.persistence.repository.custom.implementation.EmployeeCriteriaRepository;
import com.bearhug.persistence.repository.EmployeeRepository;
import com.bearhug.presentation.dto.SimpleResponse;
import com.bearhug.service.implementation.user.EmployeeServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class BearhugManagementApplicationTests {

	@Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl service;

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
    void testDeleteEmployee_Success() {
        when(employeeRepository.existsEmployeeByFolio(UUID.fromString("f0228567-b6b9-46bc-9be0-a62e650efacd"))).thenReturn(true);
        SimpleResponse<Boolean> response = service.deleteEmployee(folio);

        verify(employeeRepository, times(1)).existsEmployeeByFolio(folio);
		assertTrue(response.data());
		assertEquals("Employee successfully eliminated", response.message());
    }

	@Test
	void testExistsEmployeeCriteria(){
		System.out.println(employeeRepository.verifyExistsEmployeeByFolio(
				"f0228567-b6b9-46bc-9be0-a62e650efacd"
		));
        System.out.println(employeeRepository.existsEmployeeEntityByFolio(
                UUID.fromString("f0228567-b6b9-46bc-9be0-a62e650efacd")
        ));
	}
}
package com.smjagxhiu.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.smjagxhiu.model.Employee;
import com.smjagxhiu.repositories.EmployeeRepository;

@ExtendWith(SpringExtension.class)
class EmployeeServiceImplIntegrationTest {

	/**
	 * During component scanning, we might find components or configurations created 
	 * only for specific tests that accidentally get picked up everywhere. 
	 * To help prevent that, Spring Boot provides @TestConfiguration annotation 
	 * that can be used on classes in src/test/java to indicate that 
	 * they should not be picked up by scanning.
	 *
	 */
	@TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration { 
        @Bean
        public EmployeeService employeeService() {
            return new EmployeeServiceImpl();
        }
    }
	
	@Autowired
    private EmployeeService employeeService;
 
    @MockBean
    private EmployeeRepository employeeRepository;
    
    @BeforeEach
    public void setUp() {
        Employee alex = new Employee("alex");
        Mockito.when(employeeRepository.findByName(alex.getName()))
          .thenReturn(alex);
    }
    
    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        String name = "alex";
        Employee found = employeeService.getEmployeeByName(name);   
        assertThat(found.getName()).isEqualTo(name);
     }

}

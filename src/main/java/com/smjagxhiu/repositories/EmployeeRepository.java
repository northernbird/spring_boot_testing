package com.smjagxhiu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smjagxhiu.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	public Employee findByName(String name);

}

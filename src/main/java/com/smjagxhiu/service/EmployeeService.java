package com.smjagxhiu.service;

import java.util.List;

import com.smjagxhiu.model.Employee;

public interface EmployeeService {
	
	public Employee getEmployeeByName(String name);
	
	public List<Employee> getAllEmployees();

	public Employee save(Employee employee);

}

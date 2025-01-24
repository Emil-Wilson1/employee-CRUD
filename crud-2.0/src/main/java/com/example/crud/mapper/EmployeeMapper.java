package com.example.crud.mapper;

import com.example.crud.dto.EmployeeDto;
import com.example.crud.entity.Employee;

public class EmployeeMapper {

	public static EmployeeDto mapToEmployeeDto(Employee employee) {
		return new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail());
	}

	public static Employee mapToEmployee(EmployeeDto employee) {
		return new Employee(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail());
	}

}

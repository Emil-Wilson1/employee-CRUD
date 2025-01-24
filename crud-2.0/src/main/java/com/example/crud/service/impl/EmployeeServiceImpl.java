package com.example.crud.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.crud.dto.EmployeeDto;
import com.example.crud.entity.Employee;
import com.example.crud.exception.ResourceNotFoundException;
import com.example.crud.mapper.EmployeeMapper;
import com.example.crud.repository.EmployeeRepository;
import com.example.crud.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		Employee savedEmployee = employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}

	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
		Employee employee=employeeRepository.findById(employeeId)
				.orElseThrow(()->
				   new ResourceNotFoundException("Employee is not exists with given id:"+employeeId)
				);
		return EmployeeMapper.mapToEmployeeDto(employee);
	}
	
	@Override
	public List<EmployeeDto> getAllEmployees(){
		List<Employee> employees=employeeRepository.findAll();
		return employees.stream().map((employee)-> EmployeeMapper.mapToEmployeeDto(employee))
				.collect(Collectors.toList());
	}
	
	@Override
	public EmployeeDto updateEmployee(Long employeeId,EmployeeDto updatedEmployeeDto) {
		Employee employee=employeeRepository.findById(employeeId)
				.orElseThrow(()->
				   new ResourceNotFoundException("Employee is not exists with given id:"+employeeId)
				);
		employee.setFirstName(updatedEmployeeDto.getFirstName());
		employee.setLastName(updatedEmployeeDto.getLastName());
		employee.setEmail(updatedEmployeeDto.getEmail());
		
		
		Employee updatedEmployee=employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
	}
	@Override
	public void deleteEmployee(Long employeeId) {
	    if (employeeId == null) {
	        throw new IllegalArgumentException("The employee ID must not be null");
	    }

	    Employee employee = employeeRepository.findById(employeeId)
	            .orElseThrow(() -> 
	                new ResourceNotFoundException("Employee does not exist with the given ID: " + employeeId)
	            );

	    employeeRepository.deleteById(employeeId);
	}
	


}

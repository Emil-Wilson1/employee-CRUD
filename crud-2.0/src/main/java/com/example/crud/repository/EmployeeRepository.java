package com.example.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
package org.example.spring.service.impl;

import org.example.spring.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    Page<Employee> findAll(Pageable pageable);

    void save(Employee employee);

    void update(Employee employee);

    void delete(Employee employee);

    Optional<Employee> findById(int id);

    Page<Employee> findAllByName(Pageable pageable ,String keyword);

    Page<Employee> salaryAsc(Pageable pageable);

    Page<Employee> salaryDesc(Pageable pageable);

    List<Employee> findAllSalaryBetween(int salaryOne, int salaryTwo);

}

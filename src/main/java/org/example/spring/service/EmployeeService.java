package org.example.spring.service;

import org.example.spring.model.Employee;
import org.example.spring.repository.IEmployeeRepository;
import org.example.spring.service.impl.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    IEmployeeRepository iEmployeeRepository;

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return iEmployeeRepository.findAll(pageable);
    }

    @Override
    public void save(Employee employee) {
        iEmployeeRepository.save(employee);
    }

    @Override
    public void update(Employee employee) {
        iEmployeeRepository.save(employee);
    }

    @Override
    public void delete(Employee employee) {
        iEmployeeRepository.delete(employee);
    }

    @Override
    public Optional<Employee> findById(int id) {
        return iEmployeeRepository.findById(id);
    }

    @Override
    public Page<Employee> findAllByName(Pageable pageable, String keyword) {
        return iEmployeeRepository.findAllByNameContains(pageable,keyword);
    }

    @Override
    public Page<Employee> salaryAsc(Pageable pageable) {
        return iEmployeeRepository.findAllByOrderBySalaryAsc(pageable);
    }

    @Override
    public Page<Employee> salaryDesc(Pageable pageable) {
        return iEmployeeRepository.findAllByOrderBySalaryDesc(pageable);
    }

    @Override
    public List<Employee> findAllSalaryBetween(int salaryOne, int salaryTwo) {
        return iEmployeeRepository.findAllBySalaryBetween(salaryOne,salaryTwo);
    }

}

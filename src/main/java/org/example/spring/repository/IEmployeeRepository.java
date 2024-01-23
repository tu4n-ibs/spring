package org.example.spring.repository;

import org.example.spring.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IEmployeeRepository extends PagingAndSortingRepository<Employee,Integer> {
    Page<Employee> findAllByNameContains(Pageable pageable,String keyword);
    Page<Employee> findAllByOrderBySalaryAsc(Pageable pageable);
    Page<Employee> findAllByOrderBySalaryDesc(Pageable pageable);
    List<Employee> findAllBySalaryBetween(int salaryOne, int salaryTwo);
}

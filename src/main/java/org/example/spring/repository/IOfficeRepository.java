package org.example.spring.repository;

import org.example.spring.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOfficeRepository extends JpaRepository<Office,Integer> {
}

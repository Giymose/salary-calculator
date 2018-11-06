package com.kuznetsov.salarycalculator.repository;

import com.kuznetsov.salarycalculator.model.Salary;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SalaryRepository extends CrudRepository<Salary, Long> {

   List<Salary> findAll();
   Salary findSalaryById(Long id);

   @Modifying
   @Transactional
    void deleteSalaryById(Long id);
}

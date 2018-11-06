package com.kuznetsov.salarycalculator.repository;

import com.kuznetsov.salarycalculator.model.Salary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepository extends CrudRepository<Salary, Long> {

   List<Salary> findAll();
   List<Salary> findSalaryById(Long id);

}

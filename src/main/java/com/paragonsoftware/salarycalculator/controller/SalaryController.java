package com.paragonsoftware.salarycalculator.controller;

import com.paragonsoftware.salarycalculator.model.Salary;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salary")
public class SalaryController {

    @PostMapping("/v1/create")
    public Salary create(@RequestBody Salary salary) {
        return salary;
    }
}

package com.kuznetsov.salarycalculator.controller;

import com.kuznetsov.salarycalculator.model.Salary;
import com.kuznetsov.salarycalculator.report.SalaryRequestStatus;
import com.kuznetsov.salarycalculator.repository.SalaryRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/salary")
public class SalaryController {

    @Autowired
    private SalaryRepository salaryRepository;

    @PostMapping("/v1/create")
    @ApiOperation(
            value = "Add information about salary into DB.",
            response = SalaryRequestStatus.class
    )
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Information about salary was added into DB!", response = SalaryRequestStatus.class)}
    )
    public ResponseEntity<SalaryRequestStatus> create(@RequestBody Salary salary) {
        log.info("Adding information about salary.....");
        BigDecimal balance = salary.getAmount().subtract(salary.getCredit().add(salary.getReserve()));
        salary.setBalance(balance);
        salaryRepository.save(salary);
        return ResponseEntity.ok(new SalaryRequestStatus("SUCCESS", "Information about salary was added into DB!"));
    }

    @ResponseBody
    @GetMapping("/v1/get")
    public ResponseEntity<List<Salary>> get() {
        log.info("Getting information about salary.....");
        List<Salary> all = salaryRepository.findAll();
        return ResponseEntity.ok(all);
    }

    private Date getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, Calendar.AUGUST, 1, 23, 23, 23);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-u H:m:s");
        return calendar.getTime();
    }

}

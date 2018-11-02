package com.kuznetsov.salarycalculator.controller;

import com.kuznetsov.salarycalculator.model.Salary;
import com.kuznetsov.salarycalculator.report.SalaryRequestStatus;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/salary")
public class SalaryController {

    private BigDecimal balance;

    @PostMapping("/v1/create")
    @ApiOperation(
            value = "Add information about salary",
            response = SalaryRequestStatus.class
    )
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Add information about salary", response = SalaryRequestStatus.class)}
    )
    public ResponseEntity<SalaryRequestStatus> create(@RequestBody Salary salary) {
        log.info("Adding information about salary.....");
        balance = salary.getAmount().subtract(salary.getCredit().add(salary.getReserve()));
        salary.setBalance(balance);
        return ResponseEntity.ok(new SalaryRequestStatus("SUCCESS", "Information about salary was added"));
    }
}

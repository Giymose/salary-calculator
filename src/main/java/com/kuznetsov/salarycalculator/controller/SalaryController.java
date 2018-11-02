package com.kuznetsov.salarycalculator.controller;

import com.kuznetsov.salarycalculator.model.Salary;
import com.kuznetsov.salarycalculator.report.SalaryRequestStatus;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

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

    @GetMapping("/v1/get")
    public ResponseEntity<SalaryRequestStatus> get() {
        log.info("Getting information about salary.....");
        Salary salary = new Salary();
        salary.setAmount(BigDecimal.valueOf(46000));
        salary.setCredit(BigDecimal.valueOf(46000));
        salary.setReserve(BigDecimal.valueOf(46000));
        balance = salary.getAmount().subtract(salary.getCredit().add(salary.getReserve()));
        salary.setBalance(balance);
        salary.setDate(getDate());
        return ResponseEntity.ok(new SalaryRequestStatus("SUCCESS", "Information about salary was added"));
    }

    private Date getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, Calendar.AUGUST, 1, 23, 23, 23);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-u H:m:s");
        return calendar.getTime();
    }

}
